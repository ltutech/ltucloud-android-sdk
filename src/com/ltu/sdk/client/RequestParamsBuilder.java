package com.ltu.sdk.client;

import java.io.InputStream;
import java.util.ArrayList;

import com.loopj.android.http.RequestParams;

/**
 * Provides an easier way to create request parameters.
 *
 * Usage:
 *
 * <pre>
 * RequestParamsBuilder builder = new RequestParamsBuilder().addParam(&quot;myKey&quot;,
 *         &quot;myValue&quot;);
 * new ApiClient().get(ApiResource.class, builder, handler);
 * </pre>
 */
public class RequestParamsBuilder {

    /**
     * The {@link RequestParams} to build
     */
    private RequestParams params;

    /**
     * Create new empty request parameters.
     */
    public RequestParamsBuilder() {
        super();
        this.params = new RequestParams();
    }

    /**
     * Add simple parameters to the request.
     *
     * @param key
     *            the name of the parameter
     * @param value
     *            its value
     * @return the same object on which the method has been called for chaining
     */
    public RequestParamsBuilder addParam(String key, String value) {
        this.params.put(key, value);
        return this;
    }

    /**
     * Add an array to the request.
     *
     * @param key
     *            the name of the parameter
     * @param value
     *            the list of values
     * @return the same object on which the method has been called for chaining
     */
    public RequestParamsBuilder addParam(String key, ArrayList<String> value) {
        this.params.put(key, value);
        return this;
    }

    /**
     * Add a stream to the request, useful for images.
     *
     * @param key
     *            the name of the stream
     * @param value
     *            the content of the stream
     * @return the same object on which the method has been called for chaining
     */
    public RequestParamsBuilder addParam(String key, InputStream value) {
        this.params.put(key, value, "image.jpg");
        return this;
    }

    /**
     * Package method to retrieve the {@link RequestParams}
     *
     * @return the {@link RequestParams} built with this
     *         {@link RequestParamsBuilder}
     */
    RequestParams getRequestParams() {
        return this.params;
    }
}
