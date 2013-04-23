package com.ltu.sdk.requests;

import com.ltu.sdk.client.ApiClient;
import com.ltu.sdk.client.RequestParamsBuilder;
import com.ltu.sdk.handlers.GenericResponseHandler;
import com.ltu.sdk.handlers.PaginatedResponseHandler;
import com.ltu.sdk.resources.ApiResourceData;

/**
 * Generic requests object to help retrieving {@link ApiResourceData}
 *
 * @param <T>
 *            One of the sub classes of {@link ApiResourceData}
 */
class Requests<T extends ApiResourceData> {

    /**
     * The client that will execute the requests
     */
    protected ApiClient client;

    /**
     * The type of {@link ApiResourceData} this class can handle
     */
    protected Class<T> type;

    /**
     *
     * @param type
     *            The type of {@link ApiResourceData} this class can handle
     * @param username
     *            username for the API
     * @param password
     *            associated password
     */
    public Requests(Class<T> type, String username, String password) {
        this.client = new ApiClient(username, password);
        this.type = type;
    }

    /**
     * Retrieve a specific {@link ApiResourceData} object
     *
     * @param resource
     *            An instance of the {@link ApiResourceData} with it's id set
     * @param handler
     *            a {@link GenericResponseHandler} to handle the
     *            {@link ApiResourceData} returned by the server
     */
    public void getResource(T resource, GenericResponseHandler<T> handler) {
        this.client.getResource(resource, null, handler);
    }

    /**
     * Retrieves the list of {@link ApiResourceData}.
     *
     * TODO: The user should be able to filter these results with some sort of
     * PaginationParams
     *
     * @param builder
     *            a {@link RequestParamsBuilder} with all the parameters you
     *            want to pass in the request
     * @param handler
     *            a {@link PaginatedResponseHandler} to handle the server
     *            response
     */
    public void listResource(RequestParamsBuilder builder,
            PaginatedResponseHandler<T> handler) {
        this.client.listResources(this.type, builder, handler);
    }
}
