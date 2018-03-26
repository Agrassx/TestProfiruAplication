package ru.profi.test.myapplication.model.asynctasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

import ru.profi.test.myapplication.R;
import ru.profi.test.myapplication.model.cache.CacheUtil;

public class ImageLoaderTask extends AsyncTask<String, Void, Bitmap> {

    private final WeakReference<ImageView> imageViewReference;
    private final WeakReference<TextView> textViewWeakReference;
    private String id;


    public ImageLoaderTask(ImageView imageView, TextView textView) {
        textViewWeakReference = new WeakReference<>(textView);
        textViewWeakReference.get().setVisibility(View.VISIBLE);
        imageViewReference = new WeakReference<>(imageView);
    }

    public ImageLoaderTask(ImageView imageView, String id) {
        textViewWeakReference = new WeakReference<>(null);
        imageViewReference = new WeakReference<>(imageView);
        this.id = id;
    }

    public ImageLoaderTask(ImageView imageView) {
        textViewWeakReference = new WeakReference<>(null);
        imageViewReference = new WeakReference<>(imageView);
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        return downloadBitmap(strings[0]);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        ImageView imageView = imageViewReference.get();
        if (imageView != null) {
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
                if (id != null) {
                    CacheUtil.addBitmapToMemoryCache(id, bitmap);
                }
            } else {
                Drawable placeholder = imageView.getContext().getResources().getDrawable(R.drawable.ic_ab_app);
                imageView.setImageDrawable(placeholder);
            }
        }
        TextView textView = textViewWeakReference.get();
        if (textView == null) return;
        textView.setVisibility(View.GONE);

    }

    private Bitmap downloadBitmap(String url) {
        HttpURLConnection urlConnection = null;
        try {
            URL uri = new URL(url);
            urlConnection = (HttpURLConnection) uri.openConnection();
            int statusCode = urlConnection.getResponseCode();
            if (statusCode != HttpURLConnection.HTTP_OK) {
                return null;
            }

            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream != null) {
                return BitmapFactory.decodeStream(inputStream);
            }
        } catch (Exception e) {
//            urlConnection.disconnect();
            Log.e(ImageLoaderTask.class.getName(), "Error downloading image from " + url);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return null;
    }
}
