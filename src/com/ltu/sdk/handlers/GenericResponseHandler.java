package com.ltu.sdk.handlers;

import com.ltu.sdk.resources.ApiResourceData;

/**
 * Will automatically un-serialize the HTTP response to the given generic type.
 *
 * Usage:
 *
 * <pre>
 * GenericResponseHandler&lt;MyClass&gt; handler = new GenericResponseHandler&lt;MyClass&gt;(MyClass.class){
 *   protected void onSuccess(MyClass result){
 *      // Do something clever with the result
 *   }
 * }
 * </pre>
 *
 * The trick of passing the class of T in the constructor is mandatory since the
 * class of T cannot be inferred at runtime.
 *
 * @param <T>
 *            The type of object to be un-serialize from the HTTP response
 */
public abstract class GenericResponseHandler<T extends ApiResourceData> extends
        ApiResourceHandler<T> {

    public GenericResponseHandler(Class<T> genericType) {
        super(genericType);
    }

    /**
     * Override this method to handle the object returned by the API
     *
     * @param result
     *            The object received from API
     */
    protected abstract void onResultReceived(T result);

    /**
     * When we receive a response from the API we un-serialize it to the type
     * this {@link GenericResponseHandler} has been created with and then call
     * the method onSuccess(T result) letting the caller handle only high level
     * objects.
     *
     * @param response
     *            The text response from the API
     */
    @Override
    public void onSuccess(String response) {
        T result = gson.fromJson(response, this.genericType);
        if (result != null) {
            this.onResultReceived(result);
        } else {
            this.onFailure(new Throwable(), "Couldn't retrieve object of type "
                    + this.genericType.getName());
        }
    }

}
