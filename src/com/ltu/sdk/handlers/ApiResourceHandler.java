package com.ltu.sdk.handlers;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.ltu.sdk.resources.ApiResourceData;

/**
 * Basic AsyncHttpResponseHandler to handle our {@link ApiResourceData}s. Use one of
 * the known sub-classes.
 *
 * @param <T>
 *            The type of object to be un-serialize from the HTTP response
 */
public abstract class ApiResourceHandler<T extends ApiResourceData> extends
        AsyncHttpResponseHandler {

    /**
     * Used to un-serialize the response to given generic type
     */
    protected static Gson gson = new Gson();

    /**
     * Class of the object we want to receive from the API
     */
    protected Class<T> genericType = null;

    public ApiResourceHandler(Class<T> genericType) {
        super();
        this.genericType = genericType;
    }

}
