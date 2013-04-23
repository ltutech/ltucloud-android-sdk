package com.ltu.sdk.resources.links;

public class VisualLinks extends Links {

    /**
     * An alternate to the self link
     */
    private String alternate;

    /**
     * URL to the Images associated to this Visual
     */
    private String images;

    /**
     * URL to the MetaData of this Visual
     */
    private String metadata;

    /**
     * URL to the Project containing this Visual
     */
    private String project;

    /**
     * @return the alternate
     */
    public String getAlternate() {
        return this.alternate;
    }

    /**
     * @return the images
     */
    public String getImages() {
        return this.images;
    }

    /**
     * @return the metadata
     */
    public String getMetadata() {
        return this.metadata;
    }

    /**
     * @return the project
     */
    public String getProject() {
        return this.project;
    }

    /**
     * @param alternate the alternate to set
     */
    public void setAlternate(String alternate) {
        this.alternate = alternate;
    }

    /**
     * @param images the images to set
     */
    public void setImages(String images) {
        this.images = images;
    }

    /**
     * @param metadata the metadata to set
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * @param project the project to set
     */
    public void setProject(String project) {
        this.project = project;
    }
}
