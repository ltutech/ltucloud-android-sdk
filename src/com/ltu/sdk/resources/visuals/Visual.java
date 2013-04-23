package com.ltu.sdk.resources.visuals;

import java.util.ArrayList;

import com.ltu.sdk.resources.images.Image;

public class Visual extends BaseVisual {

    /**
     * List of images that matched during a Query
     */
    private ArrayList<Image> images;

    /**
     * @return the images
     */
    public ArrayList<Image> getImages() {
        return this.images;
    }

    /**
     * @param images
     *            the images to set
     */
    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }

}
