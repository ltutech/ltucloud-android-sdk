package com.ltu.sdk.activities;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import com.ltu.sdk.camera.CameraActivity;
import com.ltu.sdk.handlers.GenericResponseHandler;
import com.ltu.sdk.requests.QueryRequests;
import com.ltu.sdk.resources.Project;
import com.ltu.sdk.resources.Query;
import com.ltu.sdk.resources.visuals.Visual;

/**
 * Make sending a search {@link Query} easier than ever
 *
 */
public abstract class SearchQueryActivity extends Activity {

    /**
     * Code to identify the camera intent we started
     */
    private static final int CAPTURE_IMAGE_REQUEST_CODE = 42;

    private String api_password = null;

    private String api_username = null;

    private ArrayList<Project> projects = new ArrayList<Project>();

    private String source = "LTUAndroidMobileSDK";

    private String source_description = null;

    /**
     * Check if the API credentials and project ids are set
     *
     * @return true if they are set, false otherwise
     */
    private boolean checkParameters() {
        boolean result = true;
        if (TextUtils.isEmpty(this.api_username)
                || TextUtils.isEmpty(this.api_password)) {
            result = false;
        }
        if (this.projects.isEmpty()) {
            result = false;
        }
        return result;
    }

    /**
     * Handle the request result depending on its status and the number of
     * matches found
     *
     * @param result
     *            the {@link Query} returned by the API
     */
    private void handleResult(Query result) {
        if (result.getStatus().isError()) {
            this.onSearchQueryError();
        } else {
            if (result.getMatches().size() == 0) {
                this.onNoMatchFound();
            } else {
                this.onSearchQuerySuccessful(result);
            }
        }
    }

    /**
     * We retrieve the result from Activities
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                this.sendSearchQuery(data.getExtras().getByteArray(
                        CameraActivity.EXTRA_IMAGE_DATA));
            } else {
                this.onImageCaptureFailed();
            }
        }
    }

    /**
     * Triggered when we were not able to capture an image from the Camera
     */
    protected abstract void onImageCaptureFailed();

    /**
     * Triggered if the search is successful but no {@link Visual} matched the
     * image captured
     */
    protected void onNoMatchFound() {
    }

    /**
     * Triggered when the Http request was correctly processed but an error
     * occurred on LTU's servers
     */
    protected void onSearchQueryError() {
    }

    /**
     * Triggered when the Http request failed
     *
     * @param error
     *            the exception that caused the request to fail
     * @param content
     *            the error message
     */
    protected abstract void onSearchQueryFailed(Throwable error, String content);

    /**
     * Triggered when the search is complete, no matter the outcome. Useful for
     * toggling UI display.
     */
    protected void onSearchQueryFinish() {
    }

    /**
     * Triggered when the search starts. Useful for toggling UI display.
     */
    protected void onSearchQueryStart() {

    }

    /**
     * Triggered when the request is successful and we were able to retrieve the
     * associated {@link Query}
     *
     * @param result
     *            the {@link Query} object created by your request
     */
    protected abstract void onSearchQuerySuccessful(Query result);

    /**
     * Send the image capture from the camera to the API
     *
     * @param image
     *            the image as a byte array
     */
    private void sendSearchQuery(byte[] image) {
        QueryRequests request = new QueryRequests(this.api_username,
                this.api_password);
        GenericResponseHandler<Query> handler = new GenericResponseHandler<Query>(
                Query.class) {

            @Override
            public void onFailure(Throwable error, String content) {
                SearchQueryActivity.this.onSearchQueryFailed(error, content);
            }

            @Override
            public void onFinish() {
                SearchQueryActivity.this.onSearchQueryFinish();
            }

            @Override
            protected void onResultReceived(Query result) {
                SearchQueryActivity.this.handleResult(result);
            }

            @Override
            public void onStart() {
                SearchQueryActivity.this.onSearchQueryStart();
            }
        };
        request.createQuery(new ByteArrayInputStream(image), this.projects,
                this.source, this.source_description, handler);
    }

    /**
     * Method to be called before starting the camera
     *
     * @param username
     *            your API username
     * @param password
     *            your API password
     * @param projectIds
     *            a list of project ids you want to create
     */
    protected void setup(String username, String password, int... projectIds) {
        this.setup(username, password, this.source, this.source_description,
                projectIds);
    }

    /**
     * Method to be called before starting the camera
     *
     * @param username
     *            your API username
     * @param password
     *            your API password
     * @param source
     *            the name of your application
     * @param source_description
     *            a short description
     * @param projectIds
     *            a list of project ids you want to create
     */
    protected void setup(String username, String password, String source,
            String source_description, int... projectIds) {
        this.setSource(source);
        this.setSourceDescription(source_description);
        this.setCredentials(username, password);
        this.setProjectIds(projectIds);
    }

    /**
     * Set the credentials for the API
     *
     * @param username
     *            your API username
     * @param password
     *            your API password
     */
    private void setCredentials(String username, String password) {
        this.api_username = username;
        this.api_password = password;
    }

    /**
     * The {@link Project}s on which the request will be made
     *
     * @param projectIds
     *            a list of project ids
     */
    private void setProjectIds(int... projectIds) {
        for (int projectId : projectIds) {
            Project project = new Project();
            project.setId(projectId);
            this.projects.add(project);
        }
    }

    /**
     * @param source
     *            the source to set
     */
    protected void setSource(String source) {
        this.source = source;
    }

    /**
     * @param source_description
     *            the source_description to set
     */
    protected void setSourceDescription(String sourceDescription) {
        this.source_description = sourceDescription;
    }

    /**
     * Starts a Camera and display the live feed full screen. When the screen is
     * tapped a picture is taken.
     *
     * @throws RuntimeException
     *             if the username/password or the project id(s) are not set
     */
    protected void startCamera() {
        if (!this.checkParameters()) {
            throw new RuntimeException(
                    "Your username, password and project id need to be set");
        }
        Intent cameraIntent = new Intent(this, CameraActivity.class);
        this.startActivityForResult(cameraIntent, CAPTURE_IMAGE_REQUEST_CODE);
    }
}
