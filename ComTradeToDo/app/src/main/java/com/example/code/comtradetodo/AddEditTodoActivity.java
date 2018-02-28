package com.example.code.comtradetodo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddEditTodoActivity extends AppCompatActivity implements TimePickerFragment.TimeSelectedListener {

    private static final String TAG = AddEditTodoActivity.class.getSimpleName();
    public static final String TODO_INTENT_KEY = "todo_intent_key";

    private static final int PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE = 7;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    String mCurrentPhotoPath;

    private int hour;
    private int min;
    TextView alarmTextView;
    ImageView imageView;
    ConstraintLayout layout;
    ProgressBar progressBar;

    //TODO obezbediti da se hour i min sacuvaju kada dodje do configuration changa 2 poena

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_todo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        final TextView textView = findViewById(R.id.edit_text_add_edit_activity_title_text_view);
        alarmTextView = findViewById(R.id.alarm_time_edit_text);
        alarmTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerFragment();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence todoTitle = textView.getText();
                if (todoTitle == null) {
                    Snackbar.make(view, "Nisi nista ni uneo", Snackbar.LENGTH_SHORT).show();
                } else if (todoTitle.length() == 0) {
                    Snackbar.make(view, "Prazan text", Snackbar.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent();
                    Todo todo = new Todo(todoTitle.toString());
                    todo.setDescription(""); //TODO dodati description polje sve sto treba
                    todo.setAlarmHour(hour);
                    todo.setAlarmMin(min);
                    todo.setPictureFileUri(mCurrentPhotoPath);
                    intent.putExtra(TODO_INTENT_KEY, todo);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

        layout = findViewById(R.id.add_edit_constraint_layout);
        imageView = findViewById(R.id.imageView);
        progressBar = findViewById(R.id.progressBar);
        ImageButton imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkForPermission();
            }
        });

        Todo todo = getIntent().getParcelableExtra("todo_to_edit");
        if (todo != null) {
            hour = todo.getAlarmHour();
            min = todo.getAlarmMin();
            textView.setText(todo.getTitle());
            setAlarmTextViewTime();
            if (todo.getPictureFileUri() != null) {
                new DecodePictureAsyncTask(imageView, progressBar).execute(todo.getPictureFileUri() );
            }
        } else {
            hour = -1;
            min = -1;
        }

    }

    private void checkForPermission() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            takeAPicture();
        } else {
            requestPermission();
        }
    }

    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Snackbar.make(layout, R.string.obavestenje,
                    Snackbar.LENGTH_INDEFINITE).setAction(R.string.ok, new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    ActivityCompat.requestPermissions(AddEditTodoActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE);
                }
            }).show();
        } else {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE);
        }
    }

    private void takeAPicture() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.code.comtradetodo.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private File createImageFile() throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void showTimePickerFragment() {
        TimePickerFragment timePickerFragment = TimePickerFragment.getInstance(hour, min);
        timePickerFragment.show(getFragmentManager(), null);
    }

    @Override
    public void onTimeSelected(int hour, int min) {
        this.hour = hour;
        this.min = min;
        Log.d(TAG, "Izabrao vreme: " + hour + " " + min);
        setAlarmTextViewTime();
    }

    public void setAlarmTextViewTime() {
        String text = getString(R.string.alarm_time, hour, min);
        alarmTextView.setText(text);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            //Bundle extras = data.getExtras();
            //Bitmap imageBitmap = (Bitmap) extras.get("data");
            //imageView.setImageBitmap(imageBitmap);
            new DecodePictureAsyncTask(imageView, progressBar).execute(mCurrentPhotoPath);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //writeToFile();
                takeAPicture();
            }
        } else {
            Snackbar.make(layout, "Write permission request was denied.",
                    Snackbar.LENGTH_SHORT).
                    show();
        }
    }

    private static class DecodePictureAsyncTask extends AsyncTask<String, Void, Bitmap> {

        ImageView imageView;
        ProgressBar progressBar;

        public DecodePictureAsyncTask(ImageView imageView, ProgressBar progressBar) {
            this.imageView = imageView;
            this.progressBar = progressBar;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.INVISIBLE);
        }

        protected Bitmap doInBackground(String... urls) {
            String fileName = urls[0];
            int targetW = imageView.getWidth();
            int targetH = imageView.getHeight();
            targetW = targetW == 0 ? 1 : targetW;
            targetH = targetH == 0 ? 1 : targetH;

            // Get the dimensions of the bitmap
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(fileName, bmOptions);
            int photoW = bmOptions.outWidth;
            int photoH = bmOptions.outHeight;

            // Determine how much to scale down the image
            int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

            // Decode the image file into a Bitmap sized to fill the View
            bmOptions.inJustDecodeBounds = false;
            bmOptions.inSampleSize = scaleFactor;
            bmOptions.inPurgeable = true;

            Bitmap bitmap = BitmapFactory.decodeFile(fileName, bmOptions);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap bitmap) {
            progressBar.setVisibility(View.INVISIBLE);
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageBitmap(bitmap);
        }
    }
}