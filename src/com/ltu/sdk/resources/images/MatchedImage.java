package com.ltu.sdk.resources.images;

import com.ltu.sdk.resources.links.MatchedImageLinks;

public class MatchedImage extends BaseImage {

    /**
     * Links related to this MatchedImage
     */
    private MatchedImageLinks _links;

    /**
     * Id of the image this {@link MatchedImage} is linked to
     */
    private int image_id;

    /**
     * The score attributed to this MatchedImage
     */
    private float score;

    /**
     * @return the id of the {@link Image} linked to this {@link MatchedImage}
     */
    @Override
    public int getId() {
        return this.image_id;
    }

    /**
     * @return the _links
     */
    public MatchedImageLinks getLinks() {
        return this._links;
    }

    /**
     * @return the score
     */
    public float getScore() {
        return this.score;
    }

    /**
     *
     * @param id
     *            the {@link Image} id to set
     */
    @Override
    public void setId(int id) {
        this.image_id = id;
    }

    /**
     * @param _links
     *            the _links to set
     */
    public void setLinks(MatchedImageLinks _links) {
        this._links = _links;
    }

    /**
     * @param score
     *            the score to set
     */
    public void setScore(float score) {
        this.score = score;
    }

}
