package com.ltu.sdk.resources;

import com.ltu.sdk.resources.links.MetaDataLinks;
import com.ltu.sdk.resources.visuals.Visual;

public class MetaData extends ApiResourceData {

    /**
     * Links related to this {@link MetaData}
     */
    private MetaDataLinks _links;

    /**
     * The key/name of this {@link MetaData}
     */
    private String key;

    /**
     * Ordering of this {@link MetaData}
     */
    private int ordering;

    /**
     * The value of this {@link MetaData}
     */
    private String value;

    /**
     * The id of the {@link Visual} to which this {@link MetaData} is linked
     */
    private int visual_id;

    /**
     * @return the key
     */
    public String getKey() {
        return this.key;
    }

    /**
     * @return the _links
     */
    public MetaDataLinks getLinks() {
        return this._links;
    }

    /**
     * @return the ordering
     */
    public int getOrdering() {
        return this.ordering;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return this.value;
    }

    /**
     * @return the visual_id
     */
    public int getVisualId() {
        return this.visual_id;
    }

    /**
     * @param key
     *            the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @param _links
     *            the _links to set
     */
    public void setLinks(MetaDataLinks _links) {
        this._links = _links;
    }

    /**
     * @param ordering
     *            the ordering to set
     */
    public void setOrdering(int ordering) {
        this.ordering = ordering;
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @param visual_id the visual_id to set
     */
    public void setVisualId(int visual_id) {
        this.visual_id = visual_id;
    }
}
