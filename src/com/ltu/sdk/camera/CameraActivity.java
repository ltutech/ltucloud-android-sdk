package com.ltu.sdk.camera;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.ltu.sdk.R;

/**
 * Encapsulate the image capture process.
 *
 * It is meant to be used as the native Camera intent i.e.
 *
 * <pre>
 * // Somewhere in your Activity
 * Intent cameraIntent = new Intent(CameraActivity.class);
 * // We set the preview format to JPEG if the hardware supports it
 * cameraIntant.putExtra(CameraActivity.EXTRA_JPEG_PREVIEW_IMAGE_FORMAT, true);
 * startActivityForResult(cameraIntent, INNER_CODE_TO_ID_THE_INTENT);
 *
 * // In your Activity onActivityResult method
 * if (requestCode == INNER_CODE_TO_ID_THE_INTENT
 *         &amp;&amp; resultCode == Activity.RESULT_OK) {
 *     byte[] image = data.getExtras().getByteArray(
 *             CameraActivity.EXTRA_IMAGE_DATA);
 *     // Do want you want we the image
 * }
 * </pre>
 */
public class CameraActivity extends Activity {

    /**
     * Key of the extra to which the image data will be linked.
     */
    public final static String EXTRA_IMAGE_DATA = CameraActivity.class
            .getCanonicalName() + "::IMAGE_DATA";

    /**
     * Key of the extra to use JPEG preview image format. This Activity is
     * expecting a boolean.
     */
    public final static String EXTRA_JPEG_PREVIEW_IMAGE_FORMAT = CameraActivity.class
            .getCanonicalName() + "::JPEG_PREVIEW_IMAGE_FORMAT";

    private CameraPreview preview;

    /**
     * Returns the {@link Camera} if it's available otherwise it displays an
     * alert message and returns to the previous Activity
     *
     * @return an instance of {@link Camera}
     */
    private Camera getCameraInstance() {
        Camera camera = null;
        try {
            camera = Camera.open();
        } catch (Exception exception) {
            // Camera is not available, probably in use, we return to the
            // previous Activity
            Toast.makeText(this, this.getString(R.string.error_camera_in_use),
                    Toast.LENGTH_LONG).show();
            this.finish();
        }
        return camera;
    }

    void setResult(byte[] imageData) {
        Intent data = new Intent();
        data.putExtra(EXTRA_IMAGE_DATA, imageData);
        this.setResult(Activity.RESULT_OK, data);
        this.releasePreview();
        this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_camera);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Camera camera = this.getCameraInstance();
        boolean previewJpegFormat = false;
        Bundle extras = this.getIntent().getExtras();
        if (extras != null) {
            previewJpegFormat = extras.getBoolean(
                    EXTRA_JPEG_PREVIEW_IMAGE_FORMAT, false);
        }
        this.preview = new CameraPreview(this, camera, previewJpegFormat);
        ((FrameLayout) this.findViewById(R.id.camera_preview))
                .addView(this.preview);
    }

    private void releasePreview() {
        if (this.preview != null) {
            this.preview.release();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.releasePreview();
    }

    public void takePicture(View view) {
        this.preview.takePicture(this);
    }
}
