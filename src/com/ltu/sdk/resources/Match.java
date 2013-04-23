package com.ltu.sdk.resources;

import com.ltu.sdk.resources.links.MatchLinks;
import com.ltu.sdk.resources.visuals.MatchedVisual;

/**
 * Represent a Match i.e. the fact that a Visual matched an image sent through
 * the API. It holds the Visual that matched, the score and some other useful
 * attributes.
 *
 */
public class Match extends ApiResourceData {

    /**
     * Self reference link
     */
    private MatchLinks _links;

    /**
     * The {@link Media} linked to this {@link Match}
     */
    private Media _media;

    /**
     * The Visual that matched
     */
    private MatchedVisual matched_visual;

    /**
     * Number of Images in the Visual that matched
     */
    private int nb_matched_images;

    /**
     * The id of the {@link Query} that generated this {@link Match}
     */
    private int query_id;

    /**
     * Score attributed to this Match
     */
    private float score;

    /**
     * @return the _media
     */
    public Media get_media() {
        return this._media;
    }

    /**
     * @return the _links
     */
    public MatchLinks getLinks() {
        return this._links;
    }

    /**
     * @return the matched_visual
     */
    public MatchedVisual getMatchedVisual() {
        return this.matched_visual;
    }

    /**
     * @return the nb_matched_images
     */
    public int getNbMatchedImages() {
        return this.nb_matched_images;
    }

    /**
     * @return the query_id
     */
    public int getQuery_id() {
        return this.query_id;
    }

    /**
     * @return the score
     */
    public float getScore() {
        return this.score;
    }

    /**
     * @param _media the _media to set
     */
    public void set_media(Media _media) {
        this._media = _media;
    }

    /**
     * @param _links
     *            the _links to set
     */
    public void setLinks(MatchLinks _links) {
        this._links = _links;
    }

    /**
     * @param matched_visual
     *            the matched_visual to set
     */
    public void setMatchedVisual(MatchedVisual matched_visual) {
        this.matched_visual = matched_visual;
    }

    /**
     * @param nb_matched_images
     *            the nb_matched_images to set
     */
    public void setNbMatchedImages(int nb_matched_images) {
        this.nb_matched_images = nb_matched_images;
    }

    /**
     * @param query_id the query_id to set
     */
    public void setQuery_id(int query_id) {
        this.query_id = query_id;
    }

    /**
     * @param score
     *            the score to set
     */
    public void setScore(float score) {
        this.score = score;
    }

}
