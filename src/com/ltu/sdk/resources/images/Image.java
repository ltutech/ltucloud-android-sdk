package com.ltu.sdk.resources.images;

import com.ltu.sdk.resources.links.ImageLinks;

public class Image extends BaseImage {

    /**
     * Links to self and the associated Visual
     */
    private ImageLinks _links;

    /**
     * MD5 of this Image
     */
    private String image_md5;

    /**
     * Name of this Image
     */
    private String name;

    /**
     * The source that added this Image
     */
    private String source;

    /**
     * Id of the Visual containing this Image
     */
    private int visual_id;

    /**
     * @return the image_md5
     */
    public String getImageMd5() {
        return this.image_md5;
    }

    /**
     * @return the _links
     */
    public ImageLinks getLinks() {
        return this._links;
    }

    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the source
     */
    public String getSource() {
        return this.source;
    }

    /**
     * @return the visual_id
     */
    public int getVisualId() {
        return this.visual_id;
    }

    /**
     * @param image_md5
     *            the image_md5 to set
     */
    public void setImageMd5(String image_md5) {
        this.image_md5 = image_md5;
    }

    /**
     * @param _links
     *            the _links to set
     */
    public void setLinks(ImageLinks _links) {
        this._links = _links;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param source
     *            the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @param visual_id
     *            the visual_id to set
     */
    public void setVisualId(int visual_id) {
        this.visual_id = visual_id;
    }

}
