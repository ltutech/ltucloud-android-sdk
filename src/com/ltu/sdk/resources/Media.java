package com.ltu.sdk.resources;

/**
 * Represent an Media resource through its image URL and its thumbnail URL.
 *
 */
public class Media {

    /**
     * URL to the full size image
     */
    private String image;

    /**
     * URL to the thumbnail image
     */
    private String thumbnail;

    /**
     * @return the image
     */
    public String getImage() {
        return this.image;
    }

    /**
     * @return the thumbnail
     */
    public String getThumbnail() {
        return this.thumbnail;
    }

    /**
     * @param image
     *            the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @param thumbnail
     *            the thumbnail to set
     */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

}
