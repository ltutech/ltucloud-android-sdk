package com.ltu.sdk.camera;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * This class is in charge of displaying the camera feed to the phone screen.
 *
 * All camera management are made here. The class has a package visibility to
 * avoid misuse by third party developers.
 *
 */
class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {

    public final static String TAG = CameraPreview.class.getCanonicalName();

    /**
     * Camera preview is in landscape mode and our Activity is portrait only
     */
    public final static int DEFAULT_ROTATION = 90;

    /**
     * An instance of the phone's camera
     */
    private Camera camera;

    /**
     * The component the camera's image will be displayed in
     */
    private SurfaceHolder holder;

    /**
     * Is the camera able to auto focus
     */
    private boolean autoFocus = false;

    /**
     * Use Jpeg for the preview image if the format is supported
     */
    private boolean useJpegPreviewIfSupported;

    public CameraPreview(Context context) {
        super(context);
        this.init(null);
    }

    public CameraPreview(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init(null);
    }

    public CameraPreview(Context context, AttributeSet attrs, int defstyle) {
        super(context, attrs, defstyle);
        this.init(null);
    }

    public CameraPreview(Context context, Camera camera,
            boolean useJpegPreviewIfSupported) {
        super(context);
        this.useJpegPreviewIfSupported = useJpegPreviewIfSupported;
        this.init(camera);
    }

    @SuppressWarnings("deprecation")
    private void init(Camera camera) {
        this.camera = camera;
        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        this.holder = this.getHolder();
        this.holder.addCallback(this);
        // Deprecated setting, but required on Android versions prior to 3.0
        this.holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public void release() {
        // Release camera for other applications
        if (this.camera != null) {
            this.camera.release();
            this.camera = null;
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
            int height) {
        if (holder.getSurface() == null) {
            // preview surface does not exist nothing to do.
            return;
        }
        if (this.camera != null) {
            // stop preview before making changes
            try {
                this.camera.stopPreview();
            } catch (Exception e) {
                // ignore: tried to stop a non-existent preview
            }
            this.setCameraParameters();
            // start preview with new settings
            try {
                this.camera.setPreviewDisplay(holder);
                this.camera.startPreview();
            } catch (Exception e) {
                Log.d(TAG, "Error starting camera preview: " + e.getMessage());
            }
        }
    }

    /**
     * Set parameters for the preview camera
     */
    @SuppressWarnings("deprecation")
    private void setCameraParameters() {
        Camera.Parameters params = this.camera.getParameters();
        // Default camera is in landscape mode so we rotate the preview
        this.camera.setDisplayOrientation(DEFAULT_ROTATION);
        // Set focus mode to auto if available
        List<String> availableFocusModes = params.getSupportedFocusModes();
        if (availableFocusModes.contains(Camera.Parameters.FOCUS_MODE_AUTO)) {
            params.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
            this.autoFocus = true;
        }
        // Set flash mode to auto if available
        List<String> availableFlashModes = params.getSupportedFlashModes();
        if (availableFlashModes.contains(Camera.Parameters.FLASH_MODE_AUTO)) {
            params.setFlashMode(Camera.Parameters.FLASH_MODE_AUTO);
        }
        // Set the preview fps to the maximum we can get
        List<int[]> supportedFpsRange = params.getSupportedPreviewFpsRange();
        // Retrieve the highest range possible
        int[] previewFps = supportedFpsRange.get(supportedFpsRange.size() - 1);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD) {
            params.setPreviewFpsRange(
                    previewFps[Camera.Parameters.PREVIEW_FPS_MIN_INDEX],
                    previewFps[Camera.Parameters.PREVIEW_FPS_MAX_INDEX]);
        } else {
            // This method was deprecated in API level 9.This is the target
            // frame rate. The actual frame rate depends on the driver.
            params.setPreviewFrameRate(30);
        }
        // Set the preview as JPEG if available and if required. Otherwise the
        // default format will be used
        if (this.useJpegPreviewIfSupported
                && params.getSupportedPreviewFormats().contains(
                        ImageFormat.JPEG)) {
            params.setPreviewFormat(ImageFormat.JPEG);
        }
        this.camera.setParameters(params);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // The Surface has been created, now tell the camera where to draw the
        // preview.
        if (this.camera != null) {
            try {
                this.camera.setPreviewDisplay(holder);
                this.camera.startPreview();
            } catch (IOException e) {
                Log.d(TAG, "Error setting camera preview: " + e.getMessage());
            }
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // The Activity is set to portrait mode which means this method will
        // only be called when we exit the Activity
    }

    /**
     * Called when the screen is tapped by the user to take a picture.
     *
     * @param cameraActivity
     *            The activity that initiate the camera feed and need the get
     *            the captured image back
     */
    public void takePicture(final CameraActivity cameraActivity) {
        // Installs a callback to be invoked for the next preview frame in
        // addition to displaying it on the screen. After one invocation, the
        // callback is cleared.
        if (this.autoFocus) {
            this.camera.autoFocus(new AutoFocusCallback() {
                @Override
                public void onAutoFocus(boolean success, Camera camera) {
                    // Note: If the current flash mode is not FLASH_MODE_OFF,
                    // flash may be fired during auto-focus, depending on the
                    // driver and camera hardware.
                    if (success) {
                        camera.setOneShotPreviewCallback(new CameraPreviewCallback(
                                cameraActivity));
                    } else {
                        camera.autoFocus(this);
                    }
                }
            });
        } else {
            this.camera.setOneShotPreviewCallback(new CameraPreviewCallback(
                    cameraActivity));
        }
    }
}
