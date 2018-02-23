package com.example.code.comtradetodo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AddEditTodoActivity extends AppCompatActivity implements TimePickerFragment.TimeSelectedListener {


    private static final String TAG = AddEditTodoActivity.class.getSimpleName();
    public static final String TODO_INTENT_KEY = "todo_intent_key";

    public TextView opisTextView;
    public TextView textView;
    public TextView vremeTextView;
    private int sati;
    private int minuti;
    public final int REQUEST_IMAGE_CAPTURE = 3;
    private static final int PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE = 7;

    String mCurrentPhotoPath;
    ConstraintLayout constraintLayout;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_todo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sati = -1;
        minuti = -1;

        imageView = findViewById(R.id.toDoImageVju);
        ImageButton imageButton = findViewById(R.id.setPicBaton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkForPermission();
            }
        });
        constraintLayout = findViewById(R.id.blabla);
        textView = findViewById(R.id.edit_text_add_edit_activity_title_text_view);
        opisTextView = findViewById(R.id.edit_text_add_edit_activity_opis_text_view);
        vremeTextView = findViewById(R.id.time_edit_text_add_edit_activity_opis_text_view);
        vremeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                izaberiAlarm();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence todoTitle = textView.getText();
                CharSequence todoOpis = opisTextView.getText();
                if (todoTitle == null) {
                    Snackbar.make(view, "Nisi nista ni uneo", Snackbar.LENGTH_SHORT).show();
                } else if (todoTitle.length() == 0) {
                    Snackbar.make(view, "Prazan text", Snackbar.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent();

                    Todo todo = new Todo(todoTitle.toString());
                    todo.setDescription(todoOpis.toString());
                    todo.setAlarmHour(sati);
                    todo.setAlarmMin(minuti);
                    todo.setPictureFileUri(mCurrentPhotoPath);
                    intent.putExtra(TODO_INTENT_KEY, todo);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

    }




    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String titleString = textView.getText().toString();


    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        textView.setText(savedInstanceState.getString("Title"));
    }


    public void izaberiAlarm() {
        TimePickerFragment timePickerFragment = TimePickerFragment.getInstance(sati, minuti);

        timePickerFragment.show(getFragmentManager(), null);
    }

    @Override
    public void onTimeSelected(int sati, int minuti) {
        this.sati = sati;
        this.minuti = minuti;
        String text = getString(R.string.alarm_format, sati, minuti);
        vremeTextView.setText(text);
    }

    private void checkForPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            takeApicture();
        } else {
            requestPermision();
        }
    }

    private void requestPermision() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE);

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Snackbar.make(constraintLayout, R.string.write_access_needed, Snackbar.LENGTH_INDEFINITE).setAction(R.string.ok, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ActivityCompat.requestPermissions(AddEditTodoActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE);
                }
            }).show();
        }
    }

    private void takeApicture() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.comtradetodo",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void setPic() {
        // Get the dimensions of the View

        int targetW = imageView.getWidth();
        int targetH = imageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        imageView.setImageBitmap(bitmap);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            //   Bundle extras = data.getExtras();
            // Bitmap imageBitmap = (Bitmap) extras.get("data");
            // Slika.setImageBitmap(imageBitmap);
        }
        setPic();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                takeApicture();
            } else {
                Snackbar.make(constraintLayout, "Nemam dozvolu da pisem", Snackbar.LENGTH_SHORT)
                        .show();
            }
        }
    }
}






