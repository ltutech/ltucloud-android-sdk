package com.ltu.sdk.requests;

import com.ltu.sdk.client.RequestParamsBuilder;
import com.ltu.sdk.handlers.ApiResourceHandler;
import com.ltu.sdk.handlers.GenericResponseHandler;
import com.ltu.sdk.handlers.PaginatedResponseHandler;
import com.ltu.sdk.resources.ApiResourceData;
import com.ltu.sdk.resources.MetaData;
import com.ltu.sdk.resources.Project;
import com.ltu.sdk.resources.images.Image;
import com.ltu.sdk.resources.visuals.Visual;

/**
 * This class helps operating on sub-resources i.e. resource that are linked to
 * other resources.
 *
 * For example {@link Image}s and {@link MetaData} are sub-resources.
 *
 * @param <T>
 *            One of the sub classes of {@link ApiResourceData}
 */
class VisualSubResourceRequests<T extends ApiResourceData> extends Requests<T> {

    public VisualSubResourceRequests(Class<T> type, String username,
            String password) {
        super(type, username, password);
    }

    /**
     * Retrieve all the sub-resources that belong to a given {@link Project}
     *
     * @param project
     *            the {@link Project} from which you want to retrieve the
     *            {sub-resources
     * @param builder
     *            a {@link RequestParamsBuilder} with all the parameters you
     *            want to pass in the request
     * @param handler
     *            an {@link PaginatedResponseHandler} to manage the API response
     */
    public void listForProject(Project project, RequestParamsBuilder builder,
            PaginatedResponseHandler<T> handler) {
        this.client.listResources(project, this.type, builder, handler);
    }

    /**
     * Retrieve all the sub-resources that belong to a given {@link Visual}
     *
     * @param visual
     *            the {@link Visual} from which you want to retrieve the
     *            sub-resources
     * @param builder
     *            a {@link RequestParamsBuilder} with all the parameters you
     *            want to pass in the request
     * @param handler
     *            an {@link ApiResourceHandler} to manage the API response
     */
    public void listForVisual(Visual visual, RequestParamsBuilder builder,
            ApiResourceHandler<T> handler) {
        this.client.listResources(visual, this.type, builder, handler);
    }

    /**
     * Add a sub-resource to a given {@link Visual}
     *
     * @param visual
     *            the {@link Visual} in which the sub-resource will be added
     * @param builder
     *            a {@link RequestParamsBuilder} with all the parameters you
     *            want to pass in the request
     * @param handler
     *            an {@link GenericResponseHandler} to manage the API response
     */
    public void addToVisual(Visual visual, RequestParamsBuilder builder,
            GenericResponseHandler<T> handler) {
        this.client.createResource(visual, this.type, builder, handler);
    }
}
