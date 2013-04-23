package com.ltu.sdk.requests;

import java.io.InputStream;
import java.util.ArrayList;

import android.graphics.Bitmap;

import com.ltu.sdk.client.RequestParamsBuilder;
import com.ltu.sdk.handlers.GenericResponseHandler;
import com.ltu.sdk.resources.Project;
import com.ltu.sdk.resources.Query;
import com.ltu.sdk.utils.RequestParamsValuesHelper;

;

/**
 * Helper class to operate on {@link Query}s
 *
 */
public class QueryRequests extends Requests<Query> {

    /**
     *
     * @param username
     *            username for the API
     * @param password
     *            associated password
     */
    public QueryRequests(String username, String password) {
        super(Query.class, username, password);
    }

    /**
     * Create a new {@link Query}
     *
     * @param image
     *            a Bitmap image
     * @param projects
     *            list of project on which to run the {@link Query}
     * @param source
     *            the name of the source from which the request originated
     * @param source_description
     *            description of the source
     * @param handler
     *            a {@link GenericResponseHandler} to handle the {@link Query}
     *            return by the server
     */
    public void createQuery(Bitmap image, ArrayList<Project> projects,
            String source, String source_description,
            GenericResponseHandler<Query> handler) {
        this.createQuery(RequestParamsValuesHelper.getStreamFromBitmap(image),
                projects, source, source_description, handler);
    }

    /**
     * Create a new {@link Query}
     *
     * @param image
     *            an image as an InputStream
     * @param projects
     *            list of project on which to run the {@link Query}
     * @param source
     *            the name of the source from which the request originated
     * @param source_description
     *            description of the source
     * @param handler
     *            a {@link GenericResponseHandler} to handle the {@link Query}
     *            return by the server
     */
    public void createQuery(InputStream image, ArrayList<Project> projects,
            String source, String source_description,
            GenericResponseHandler<Query> handler) {
        RequestParamsBuilder builder = new RequestParamsBuilder()
                .addParam("image", image)
                .addParam("projects",
                        RequestParamsValuesHelper.getResourceIds(projects))
                .addParam("source", source)
                .addParam("source_description", source_description);
        this.client.createResource(this.type, builder, handler);
    }
}
