package com.ltu.sdk.resources;

import com.ltu.sdk.resources.images.Image;

/**
 * Base class for all the API resources such as {@link Project}s, {@link Match}
 * s, {@link Image}s, {@link MetaData}s and so on.
 *
 */
public class ApiResourceData {

    /**
     * Id of this Resource
     */
    protected int id;

    /**
     * @return the id
     */
    public int getId() {
        return this.id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

}
