package com.ltu.sdk.requests;

import java.io.InputStream;
import java.util.List;

import android.graphics.Bitmap;
import android.util.Pair;

import com.ltu.sdk.client.RequestParamsBuilder;
import com.ltu.sdk.handlers.GenericResponseHandler;
import com.ltu.sdk.handlers.PaginatedResponseHandler;
import com.ltu.sdk.resources.ApiResourceData;
import com.ltu.sdk.resources.Project;
import com.ltu.sdk.resources.visuals.Visual;
import com.ltu.sdk.utils.RequestParamsValuesHelper;

/**
 * Helper class to operate on {@link Visual}s
 *
 */
public class VisualRequests extends Requests<Visual> {

    /**
     *
     * @param username
     *            username for the API
     * @param password
     *            associated password
     */
    public VisualRequests(String username, String password) {
        super(Visual.class, username, password);
    }

    /**
     * Create a {@link Visual} in the provided {@link Project}
     *
     * @param project
     *            a {@link Project} with it's id set
     * @param image
     *            a Bitmap image
     * @param title
     *            the title of the {@link Visual} to create
     * @param name
     *            the name of the {@link Visual} to create
     * @param metadataList
     *            the meta data to link with the {@link Visual} built as a
     *            {@link List} of {@link Pair} of {@link String} the first
     *            element is the key, the second the value
     * @param handler
     *            a {@link GenericResponseHandler} to handle the
     *            {@link ApiResourceData} returned by the server
     */
    public void createVisual(Project project, Bitmap image, String title,
            String name, List<Pair<String, String>> metadataList,
            GenericResponseHandler<Visual> handler) {
        this.createVisual(project,
                RequestParamsValuesHelper.getStreamFromBitmap(image), title,
                name, metadataList, handler);
    }

    /**
     * Create a {@link Visual} in the provided {@link Project}
     *
     * @param project
     *            a {@link Project} with it's id set
     * @param stream
     *            an {@link InputStream} linked to the image data
     * @param title
     *            the title of the {@link Visual} to create
     * @param name
     *            the name of the {@link Visual} to create
     * @param metadataList
     *            the meta data to link with the {@link Visual} built as a
     *            {@link List} of {@link Pair} of {@link String} the first
     *            element is the key, the second the value
     * @param handler
     *            a {@link GenericResponseHandler} to handle the
     *            {@link ApiResourceData} returned by the server
     */
    public void createVisual(Project project, InputStream stream, String title,
            String name, List<Pair<String, String>> metadataList,
            GenericResponseHandler<Visual> handler) {
        RequestParamsBuilder builder = new RequestParamsBuilder()
                .addParam("title", title).addParam("name", name)
                .addParam("images-image", stream);
        RequestParamsValuesHelper.addMetaDataToParams(builder, metadataList);
        this.client.createResource(project, this.type, builder, handler);
    }

    /**
     * Retrieve all the {@link Visual}s that belong to a given {@link Project}
     *
     * @param project
     *            the {@link Project} from which you want to retrieve the
     *            {@link Visual}s
     * @param builder
     *            a {@link RequestParamsBuilder} with all the parameters you
     *            want to pass in the request
     * @param handler
     *            an {@link PaginatedResponseHandler} to manage the API response
     */
    public void listForProject(Project project, RequestParamsBuilder builder,
            PaginatedResponseHandler<Visual> handler) {
        this.client.listResources(project, Visual.class, builder, handler);
    }
}
