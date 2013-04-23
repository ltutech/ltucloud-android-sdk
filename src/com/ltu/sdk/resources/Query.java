package com.ltu.sdk.resources;

import java.util.ArrayList;

import com.ltu.sdk.resources.links.QueryLinks;

public class Query extends ApiResourceData {

    /**
     * Links to the matches and the Query itself
     */
    private QueryLinks _links;

    /**
     * Media associated to a Query
     */
    private Media _media;

    /**
     * All the Matches linked to this Query
     */
    private ArrayList<Match> matches;

    /**
     * List of projects against which the Query was run
     */
    private ArrayList<Project> projects;

    /**
     * Origin of the Query
     */
    private String source;

    /**
     * Description of the source
     */
    private String source_description;

    /**
     * Status of the Query
     */
    private QueryStatus status;

    /**
     * @return the _links
     */
    public QueryLinks getLinks() {
        return this._links;
    }

    /**
     * @return the matches
     */
    public ArrayList<Match> getMatches() {
        return this.matches;
    }

    /**
     * @return the _media
     */
    public Media getMedia() {
        return this._media;
    }

    /**
     * @return the projects
     */
    public ArrayList<Project> getProjects() {
        return this.projects;
    }

    /**
     * @return the source
     */
    public String getSource() {
        return this.source;
    }

    /**
     * @return the source_description
     */
    public String getSourceDescription() {
        return this.source_description;
    }

    /**
     * @return the status
     */
    public QueryStatus getStatus() {
        return this.status;
    }

    /**
     * @param _links
     *            the _links to set
     */
    public void setLinks(QueryLinks _links) {
        this._links = _links;
    }

    /**
     * @param matches
     *            the matches to set
     */
    public void setMatches(ArrayList<Match> matches) {
        this.matches = matches;
    }

    /**
     * @param _media
     *            the _media to set
     */
    public void setMedia(Media _media) {
        this._media = _media;
    }

    /**
     * @param projects
     *            the projects to set
     */
    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }

    /**
     * @param source
     *            the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @param source_description
     *            the source_description to set
     */
    public void setSourceDescription(String source_description) {
        this.source_description = source_description;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(QueryStatus status) {
        this.status = status;
    }

}
