package com.ltu.sdk.client;

import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.ltu.sdk.handlers.ApiResourceHandler;
import com.ltu.sdk.resources.ApiResourceData;
import com.ltu.sdk.resources.PaginatedResult;
import com.ltu.sdk.resources.Pagination;
import com.ltu.sdk.resources.Project;
import com.ltu.sdk.resources.visuals.Visual;

/**
 * Client to query the LTU Cloud API.
 *
 */
public class ApiClient {

    /**
     * The root path of the API
     */
    private static final String API_ROOT_PATH = "/api/v1/";

    /**
     * The host of the API
     */
    private static final String HOST = "cloud.ltutech.com";

    /**
     * The port of the API
     */
    private static final int PORT = 443;

    /**
     * The protocol to use for requests
     */
    private static final String PROTOCOL = "https";

    /**
     * Tag to use for logging
     */
    private static final String TAG = ApiClient.class.getCanonicalName();

    /**
     * User agent header of the requests
     */
    private static final String USER_AGENT = "AndroidApplication";

    /**
     * The client that will actually perform the requests
     */
    private AsyncHttpClient client = new AsyncHttpClient();

    /**
     * A {@link Context} to which each request will be attached to
     */
    private Context context;

    /**
     * Creates an {@link ApiClient} linked to a specific {@link Context} and
     * authorization header
     *
     * @param context
     *            the {@link Context} to which each request will be attached to,
     *            it is used to cancel the requests
     * @param username
     *            your username for LTU Cloud
     * @param password
     *            the associated password
     */
    public ApiClient(Context context, String username, String password) {
        this.context = context;
        this.client.setUserAgent(USER_AGENT);
        this.setAuthorization(username, password);
    }

    /**
     * Creates an {@link ApiClient} with the authorization header already set.
     *
     * Note: Creating an {@link ApiClient} without a {@link Context} makes it
     * impossible to cancel the requests. If you need to cancel requests use
     * {@link ApiClient#ApiClient(Context, String, String)}
     *
     * @param username
     *            your username for LTU Cloud
     * @param password
     *            the associated password
     */
    public ApiClient(String username, String password) {
        this(null, username, password);
    }

    /**
     * Cancels any pending (or potentially active) requests associated with the
     * passed {@link Context} this {@link ApiClient} was created with.
     *
     * You can call this method in your Activity onDestroy method to destroy all
     * request that are no longer required.
     *
     * Note: a {@link RuntimeException} is thrown if you call this method with an
     * instance that was not created with a {@link Context}
     *
     * @param cancelActive
     *            specifies if active requests should be cancelled along with
     *            pending requests
     */
    public void cancelRequests(boolean cancelActive) {
        if (this.context != null) {
            this.client.cancelRequests(this.context, cancelActive);
        } else {
            throw new RuntimeException("Not cancelling requests the "
                    + this.getClass().getSimpleName()
                    + " was created without a Context.");
        }
    }

    /**
     * Create a single top-level {@link ApiResourceData}. This method is
     * equivalent to a POST on the URL of the resource.
     *
     * @param resource
     *            the class of the {@link ApiResourceData} to create
     * @param builder
     *            a {@link RequestParamsBuilder} with all the parameters you
     *            want to pass in the request
     * @param handler
     *            an {@link ApiResourceHandler} to manage the API response
     */
    public <T extends ApiResourceData> void createResource(Class<T> resource,
            RequestParamsBuilder builder, ApiResourceHandler<T> handler) {
        this.post(ResourcePathFactory.get(resource), builder, handler);
    }

    /**
     * Create a sub-resource i.e. a {@link Visual} is linked to a single
     * {@link Project} and is therefore considered a sub-resource
     *
     * @param parentResource
     *            the instance of {@link ApiResourceData} the resource to be
     *            created will be linked to
     * @param resource
     *            the class of the {@link ApiResourceData} to create
     * @param builder
     *            a {@link RequestParamsBuilder} with all the parameters you
     *            want to pass in the request
     * @param handler
     *            an {@link ApiResourceHandler} to manage the API response
     */
    public <T extends ApiResourceData, V extends ApiResourceData> void createResource(
            T parentResource, Class<V> resource, RequestParamsBuilder builder,
            ApiResourceHandler<V> handler) {
        this.post(ResourcePathFactory.getSubResourcePath(parentResource,
                resource), builder, handler);
    }

    protected void get(String path, RequestParamsBuilder builder,
            ApiResourceHandler<? extends ApiResourceData> handler) {
        this.client.get(this.context, this.getAbsoluteUrl(path),
                this.getRequestParams(builder), handler);
    }

    private String getAbsoluteUrl(String path) {
        try {
            return new URL(PROTOCOL, HOST, PORT, API_ROOT_PATH + path)
                    .toString();
        } catch (MalformedURLException e) {
            Log.e(TAG, new StringBuilder("Couldn't parse url from path: ")
                    .append(path).toString());
        }
        return null;
    }

    /**
     * Return the base64 encoded authorization header value
     *
     * @param username
     *            user's username
     * @param password
     *            user's password
     * @return base64 encoded value for the authorization header
     */
    private String getEncodedAuthHeader(String username, String password) {
        return "Basic "
                + Base64.encodeToString((username + ":" + password).getBytes(),
                        Base64.URL_SAFE | Base64.NO_WRAP);
    }

    /**
     * Retrieve the resources from the next page
     *
     * @param pagination
     *            the {@link Pagination} object from a {@link PaginatedResult}
     * @param handler
     *            an {@link ApiResourceHandler} to manage the API response
     * @throws NoSuchFieldException
     *             if the {@link Pagination} object has no next link
     */
    public <T extends ApiResourceData> void getNextPage(Pagination pagination,
            ApiResourceHandler<T> handler) throws NoSuchFieldException {
        String nextPageUrl = pagination.getLinks().getNext();
        if (nextPageUrl == null) {
            throw new NoSuchFieldException("No next page available");
        }
        this.get(nextPageUrl, null, handler);
    }

    /**
     * Retrieve the resources from the previous page
     *
     * @param pagination
     *            the {@link Pagination} object from a {@link PaginatedResult}
     * @param handler
     *            an {@link ApiResourceHandler} to manage the API response
     * @throws NoSuchFieldException
     *             if the {@link Pagination} object has no previous link
     */
    public <T extends ApiResourceData> void getPreviousPage(
            Pagination pagination, ApiResourceHandler<T> handler)
            throws NoSuchFieldException {
        String previousPageUrl = pagination.getLinks().getPrevious();
        if (previousPageUrl == null) {
            throw new NoSuchFieldException("No previous page available");
        }
        this.get(previousPageUrl, null, handler);
    }

    /**
     * Retrieve a specific {@link ApiResourceData}.
     *
     * @param resource
     *            an {@link ApiResourceData} instance
     * @param builder
     *            a {@link RequestParamsBuilder} with all the parameters you
     *            want to pass in the request
     * @param handler
     *            an {@link ApiResourceHandler} to manage the API response
     */
    public <T extends ApiResourceData> void getResource(T resource,
            RequestParamsBuilder builder, ApiResourceHandler<T> handler) {
        this.get(ResourcePathFactory.get(resource), builder, handler);
    }

    /**
     * Retrieve a specific {@link ApiResourceData}.
     *
     * @param resourceClass
     *            the type of {@link ApiResourceData} you want to retrieve
     * @param id
     *            the id of the resource you want to retrieve
     * @param builder
     *            a {@link RequestParamsBuilder} with all the parameters you
     *            want to pass in the request
     * @param handler
     *            an {@link ApiResourceHandler} to manage the API response
     */
    public <T extends ApiResourceData> void getResourceById(
            Class<T> resourceClass, int id, RequestParamsBuilder builder,
            ApiResourceHandler<T> handler) {
        this.get(ResourcePathFactory.get(resourceClass, id), builder, handler);
    }

    /**
     * List all {@link ApiResourceData} of a specific type.
     *
     * @param resource
     *            the type of {@link ApiResourceData} you want to retrieve
     * @param builder
     *            a {@link RequestParamsBuilder} with all the parameters you
     *            want to pass in the request
     * @param handler
     *            an {@link ApiResourceHandler} to manage the API response
     */
    public <T extends ApiResourceData> void listResources(Class<T> resource,
            RequestParamsBuilder builder, ApiResourceHandler<T> handler) {
        this.get(ResourcePathFactory.get(resource), builder, handler);
    }

    /**
     * List all sub-resource of a specific type.
     *
     * @param parentResource
     *            the instance of {@link ApiResourceData} which the sub-resource
     *            belongs to
     * @param resource
     *            the class of the {@link ApiResourceData} to retrieve
     * @param builder
     *            a {@link RequestParamsBuilder} with all the parameters you
     *            want to pass in the request
     * @param handler
     *            an {@link ApiResourceHandler} to manage the API response
     */
    public <T extends ApiResourceData, V extends ApiResourceData> void listResources(
            T parentResource, Class<V> resource, RequestParamsBuilder builder,
            ApiResourceHandler<V> handler) {
        this.get(ResourcePathFactory.getSubResourcePath(parentResource,
                resource), builder, handler);
    }

    protected void post(String path, RequestParamsBuilder builder,
            ApiResourceHandler<? extends ApiResourceData> handler) {
        this.client.post(this.context, this.getAbsoluteUrl(path),
                this.getRequestParams(builder), handler);
    }

    /**
     * Gets the request parameters from the builder.
     *
     * @param builder
     *            the {@link RequestParamsBuilder} with the parameters, if null
     *            no parameters will be sent
     * @return the {@link RequestParams} to send with the request
     */
    private RequestParams getRequestParams(RequestParamsBuilder builder) {
        if (builder == null) {
            return new RequestParams();
        }
        return builder.getRequestParams();
    }

    /**
     * Set the authorization header for the client. This header will be used for
     * all requests until it is overridden.
     *
     * @param username
     *            user's username
     * @param password
     *            user's password
     */
    protected void setAuthorization(String username, String password) {
        this.client.addHeader("Authorization",
                this.getEncodedAuthHeader(username, password));
    }
}
