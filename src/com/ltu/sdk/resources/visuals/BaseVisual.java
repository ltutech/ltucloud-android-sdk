package com.ltu.sdk.resources.visuals;

import java.util.ArrayList;

import com.ltu.sdk.resources.ApiResourceData;
import com.ltu.sdk.resources.MetaData;
import com.ltu.sdk.resources.links.VisualLinks;

public class BaseVisual extends ApiResourceData {

    /**
     * Links related to this Visual
     */
    protected VisualLinks _links;

    /**
     * Number of times this Visual matched
     */
    protected int match_count;

    /**
     * MetaData related to this Visual
     */
    protected ArrayList<MetaData> metadata;

    /**
     * Name of this Visual
     */
    protected String name;

    /**
     * Project containing this Visual
     */
    protected int project_id;

    /**
     * Title of this Visual
     */
    protected String title;

    /**
     * @return the _links
     */
    public VisualLinks getLinks() {
        return this._links;
    }

    /**
     * @return the match_count
     */
    public int getMatchCount() {
        return this.match_count;
    }

    /**
     * @return the metadata
     */
    public ArrayList<MetaData> getMetadata() {
        return this.metadata;
    }

    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the project_id
     */
    public int getProjectId() {
        return this.project_id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * @param _links
     *            the _links to set
     */
    public void setLinks(VisualLinks _links) {
        this._links = _links;
    }

    /**
     * @param match_count
     *            the match_count to set
     */
    public void setMatchCount(int match_count) {
        this.match_count = match_count;
    }

    /**
     * @param metadata
     *            the metadata to set
     */
    public void setMetadata(ArrayList<MetaData> metadata) {
        this.metadata = metadata;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param project_id
     *            the project_id to set
     */
    public void setProjectId(int project_id) {
        this.project_id = project_id;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

}
