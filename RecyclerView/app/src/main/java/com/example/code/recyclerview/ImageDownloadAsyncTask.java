package com.example.code.recyclerview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageDownloadAsyncTask extends AsyncTask<String, Void, Bitmap> {

    private static final String TAG = ImageDownloadAsyncTask.class.getSimpleName();

    private ImageView imageView;

    public ImageDownloadAsyncTask(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        try {
            String imageString = strings[0];
            URL imageUrl = new URL(imageString);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            InputStream inputStream = imageUrl.openConnection().getInputStream();
            BitmapFactory.decodeStream(inputStream, null, options);
            inputStream.close();
            int imageHeight = options.outHeight;
            int imageWidth = options.outWidth;
            String imageType = options.outMimeType;
            Log.d(TAG,"imageWidth: " + imageWidth + " imageHeight: " + imageHeight + " imageType: " + imageType);
            Log.d(TAG, "imageViewWidth: " + imageView.getWidth() + " imageHeight: " + imageView.getHeight());
            options.inSampleSize = calculateInSampleSize(options, imageView.getWidth(), imageView.getHeight());
            options.inJustDecodeBounds = false;
            Bitmap bitmap =  BitmapFactory.decodeStream(imageUrl.openConnection().getInputStream(), null, options);
            Log.d(TAG,"imageHeight: " + bitmap.getWidth() + " imageWidth: " + bitmap.getHeight());
            return bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}
