package com.ltu.sdk.resources.visuals;

import java.util.ArrayList;

import com.ltu.sdk.resources.images.MatchedImage;

public class MatchedVisual extends BaseVisual {

    /**
     * List of images that matched during a Query
     */
    private ArrayList<MatchedImage> matched_images;

    /**
     * @return the matched_images
     */
    public ArrayList<MatchedImage> getMatchedImages() {
        return this.matched_images;
    }

    /**
     * @param matched_images
     *            the matched_images to set
     */
    public void setMatchedImages(ArrayList<MatchedImage> matched_images) {
        this.matched_images = matched_images;
    }

}
