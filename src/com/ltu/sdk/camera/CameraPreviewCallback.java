package com.ltu.sdk.camera;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.util.Log;

/**
 * This callback receives a copy of preview frames as they are displayed.
 *
 */
class CameraPreviewCallback implements PreviewCallback {

    private static final String TAG = CameraPreviewCallback.class
            .getCanonicalName();

    /**
     * The default jpeg compression factor
     */
    private static final int DEFAULT_JPEG_COMPRESSION = 75;

    /**
     * The CameraActivity that has been launched to take a picture
     */
    private CameraActivity cameraActivity;

    /**
     * The required jpeg compression factor
     */
    private int jpegCompression;

    CameraPreviewCallback(CameraActivity cameraActivity) {
        this(cameraActivity, DEFAULT_JPEG_COMPRESSION);
    }

    public CameraPreviewCallback(CameraActivity cameraActivity,
            int jpegCompression) {
        super();
        this.cameraActivity = cameraActivity;
        this.jpegCompression = jpegCompression;
    }

    /**
     * Called when the layout attached displaying the {@link CameraPreview} is
     * clicked.
     *
     * Note: This will call the method {@link CameraActivity#setResult(byte[])}
     * which in turn will send the image back to the initial Activity that
     * started it. If the preview image format is not supported or the preview
     * buffer cannot be decoded the image returned to the caller will be null.
     *
     * @param data
     *            the contents of the preview frame
     * @param the
     *            Camera service object
     */
    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        byte[] jpegImage = null;
        try {
            jpegImage = this.getImageData(data, camera.getParameters());
        } catch (PreviewImageFormatException e) {
            Log.e(TAG,
                    "Cannot handle the preview image format returning null image");
        } catch (BitmapDecodingException e) {
            Log.e(TAG,
                    "Impossible to decode preview image returning null image");
        }
        this.cameraActivity.setResult(jpegImage);
    }

    /**
     * Retrieve the raw image data from the Camera preview data
     *
     * @param data
     *            the image data from the Camera preview
     * @param previewSize
     *            the size of the Camera preview
     * @return the raw image data
     *
     * @throws PreviewImageFormatException
     *             if the preview image format cannot be handled
     * @throws BitmapDecodingException
     *             if the byte array cannot be decoded to {@link Bitmap}
     */
    private byte[] getImageData(byte[] data, Camera.Parameters params)
            throws PreviewImageFormatException, BitmapDecodingException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Size previewSize = params.getPreviewSize();
        byte[] jpegImage = null;
        int previewImageFormat = params.getPreviewFormat();
        try {
            // Decode data to correct format
            if (previewImageFormat == ImageFormat.JPEG) {
                jpegImage = data;
            } else if (previewImageFormat == ImageFormat.NV21) {
                YuvImage yuv = new YuvImage(data, ImageFormat.NV21,
                        previewSize.width, previewSize.height, null);
                yuv.compressToJpeg(new Rect(0, 0, previewSize.width,
                        previewSize.height), this.jpegCompression, baos);
                jpegImage = baos.toByteArray();
                // Resets this stream to the beginning of the underlying byte
                // array. All subsequent writes will overwrite any bytes
                // previously stored in this stream.
                baos.reset();
            } else {
                throw new PreviewImageFormatException(
                        "Unable to handle preview image format "
                                + previewImageFormat);
            }
            Bitmap bitmap = BitmapFactory.decodeByteArray(jpegImage, 0,
                    jpegImage.length);
            if (bitmap != null) {
                // Rotate image since the camera is always captured in landscape
                // mode and setDisplayOrientation has no effect on the buffer
                // received by PreviewCallback
                Matrix matrix = new Matrix();
                matrix.postRotate(CameraPreview.DEFAULT_ROTATION);
                bitmap = Bitmap.createBitmap(bitmap, 0, 0, previewSize.width,
                        previewSize.height, matrix, false);
                bitmap.compress(CompressFormat.JPEG, this.jpegCompression, baos);
                jpegImage = baos.toByteArray();
            } else {
                throw new BitmapDecodingException();
            }
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
                Log.w(TAG, "Not able to close stream to read preview buffer: "
                        + e.getLocalizedMessage());
            }
        }
        return jpegImage;
    }

}
