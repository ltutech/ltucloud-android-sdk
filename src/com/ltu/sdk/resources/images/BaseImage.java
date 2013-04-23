package com.ltu.sdk.resources.images;


import com.ltu.sdk.resources.ApiResourceData;
import com.ltu.sdk.resources.Media;

/**
 * This class groups common attribute between all type of Image resources of the
 * API.
 *
 */
public class BaseImage extends ApiResourceData {

    /**
     * The Media representing this image
     */
    protected Media _media;

    /**
     * @return the _media
     */
    public Media getMedia() {
        return this._media;
    }

    /**
     * @param _media
     *            the _media to set
     */
    public void setMedia(Media _media) {
        this._media = _media;
    }
}
