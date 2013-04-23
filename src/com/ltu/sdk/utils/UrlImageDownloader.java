package com.ltu.sdk.utils;

import java.io.ByteArrayInputStream;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BinaryHttpResponseHandler;

/**
 * Will download an image at a given URL and set it as the {@link Drawable} for
 * the {@link ImageView} it is linked to.
 *
 */
public class UrlImageDownloader {

    private static final String TAG = UrlImageDownloader.class
            .getCanonicalName();

    /**
     * Image types accepted
     */
    private static final String[] ALLOWED_CONTENT_TYPES = new String[] {
            "image/png", "image/jpeg" };

    /**
     * {@link ImageView} which content will be updated
     */
    private final ImageView imageHolder;

    /**
     * {@link AsyncHttpClient} to get the image
     */
    private final AsyncHttpClient client = new AsyncHttpClient();

    public UrlImageDownloader(ImageView imageHolder) {
        this.imageHolder = imageHolder;
    }

    /**
     * Asynchronously download the image at the given URL and set it as the new
     * content of the {@link ImageView}.
     *
     * @param imageUrl
     *            URL of an image
     */
    public void setImage(final String imageUrl) {
        this.client.get(imageUrl, new BinaryHttpResponseHandler(
                ALLOWED_CONTENT_TYPES) {

            @Override
            public void onFailure(Throwable error, byte[] binaryData) {
                Log.e(TAG, "Couldn't download image '" + imageUrl
                        + "' error is " + error.getLocalizedMessage());
            }

            @Override
            public void onSuccess(byte[] rawData) {
                Log.d(TAG, "Succesfully downloaded image " + imageUrl);
                UrlImageDownloader.this.imageHolder.setImageDrawable(Drawable
                        .createFromStream(new ByteArrayInputStream(rawData),
                                "image_from_url"));
            }
        });
    }
}
