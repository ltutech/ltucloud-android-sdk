package com.ltu.sdk.client;

import com.ltu.sdk.resources.ApiResourceData;
import com.ltu.sdk.resources.Project;
import com.ltu.sdk.resources.visuals.Visual;

/**
 * Represents the path of an {@link ApiResourceData}
 *
 */
class Path {

    /**
     * The {@link ApiResourceData} path
     */
    private String path;

    /**
     * The "parent" {@link ApiResourceData} e.g. {@link Project} is the parent of
     * {@link Visual}
     */
    private Class<? extends ApiResourceData> parent;

    public Path(String path, Class<? extends ApiResourceData> parent) {
        super();
        this.path = path;
        this.parent = parent;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return this.path;
    }

    /**
     * @return the parent
     */
    public Class<? extends ApiResourceData> getParent() {
        return this.parent;
    }
}
