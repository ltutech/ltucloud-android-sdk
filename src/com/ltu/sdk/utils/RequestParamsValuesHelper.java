package com.ltu.sdk.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.util.Log;
import android.util.Pair;

import com.ltu.sdk.client.RequestParamsBuilder;
import com.ltu.sdk.resources.ApiResourceData;
import com.ltu.sdk.resources.MetaData;

/**
 * Helper class to easily create values for the request parameters.
 *
 */
public class RequestParamsValuesHelper {

    private static final String TAG = RequestParamsValuesHelper.class
            .getCanonicalName();

    /**
     * Compression quality
     */
    private static final int IMAGE_COMPRESSION = 70;

    /**
     * Returns an InputStream adapted to be used in {@link RequestParamsBuilder
     * .addParam(String key, InputStream value)}
     *
     * @param image
     *            A bitmap retrive from the Camera from example
     * @return the same image compressed to JPEG format as an input stream
     */
    public static ByteArrayInputStream getStreamFromBitmap(Bitmap image) {
        return RequestParamsValuesHelper.getStreamFromBitmap(image,
                IMAGE_COMPRESSION);
    }

    /**
     * Returns an InputStream adapted to be used in {@link RequestParamsBuilder
     * .addParam(String key, InputStream value)}
     *
     * @param image
     *            a {@link Bitmap} image
     * @param compressionQuality
     *            JPEG compression quality
     * @return the same image compressed to JPEG format as an input stream
     */
    public static ByteArrayInputStream getStreamFromBitmap(Bitmap image,
            int compressionQuality) {
        byte[] rawImage = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(CompressFormat.JPEG, compressionQuality, baos);
        try {
            rawImage = baos.toByteArray();
        } finally {
            try {
                // We close the ByteArrayOutputStream even though the doc says
                // this is not required and the method throws an IOException
                // only because the class implement Closeable- http://ltu.fr/Nc.
                // On the other hand the Android doc says that this release the
                // system resources - http://ltu.fr/Oc.
                baos.close();
            } catch (IOException e) {
                // This should not happen since the close method is a no-op for
                // ByteArrayOutputStream so we only log the exception.
                Log.w(TAG,
                        "Not able to close stream to read bitmap: "
                                + e.getLocalizedMessage());
            }
        }
        return new ByteArrayInputStream(rawImage);
    }

    /**
     * Retrieve the list of {@link ApiResource} ids adapted to be used in
     * {@link RequestParamsBuilder .addParam(String key, ArrayList<String>
     * value)}
     *
     * @param resources
     *            list of {@link ApiResource}
     * @return a list of {@link ApiResource} ids as String
     */
    public static <T extends ApiResourceData> ArrayList<String> getResourceIds(
            List<T> resources) {
        ArrayList<String> resourceIds = new ArrayList<String>(resources.size());
        for (T r : resources) {
            resourceIds.add(String.valueOf(r.getId()));
        }
        return resourceIds;
    }

    /**
     * Add metadata list to the existing request parameters.
     *
     * Note: Calling this method several time on the same
     * {@link RequestParamsBuilder} will override existing metadata.
     *
     * @param builder
     *            a {@link RequestParamsBuilder} with all the parameters you
     *            want to pass in the request
     * @param metadataList
     *            a {@link List} of {@link Pair} of key/value that will be
     *            interpreted as {@link MetaData}
     */
    public static void addMetaDataToParams(
            RequestParamsBuilder builder,
            List<Pair<String, String>> metadataList) {
        if (metadataList == null) {
            metadataList = new ArrayList<Pair<String, String>>();
        }
        ListIterator<Pair<String, String>> iterator = metadataList
                .listIterator();
        while (iterator.hasNext()) {
            int index = iterator.nextIndex();
            Pair<String, String> metadataPair = iterator.next();
            builder.addParam("metadata-" + index + "-key", metadataPair.first)
                    .addParam("metadata-" + index + "-value",
                            metadataPair.second);
        }
    }

}
