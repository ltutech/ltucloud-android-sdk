package com.ltu.sdk.resources.links;

import android.os.Parcel;
import android.os.Parcelable;

public class ProjectLinks extends Links implements Parcelable {

    public static final Parcelable.Creator<ProjectLinks> CREATOR = new Parcelable.Creator<ProjectLinks>() {

        @Override
        public ProjectLinks createFromParcel(Parcel source) {
            return new ProjectLinks(source);
        }

        @Override
        public ProjectLinks[] newArray(int size) {
            return new ProjectLinks[size];
        }
    };

    /**
     * URL to the images of a project
     */
    private String images;

    /**
     * URL to the members of a project
     */
    private String members;

    /**
     * URL to the metadata of a project
     */
    private String metadata;

    /**
     * URL to the visuals of a project
     */
    private String visuals;

    private ProjectLinks(Parcel source) {
        this.self = source.readString();
        this.images = source.readString();
        this.members = source.readString();
        this.metadata = source.readString();
        this.visuals = source.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * @return the images
     */
    public String getImages() {
        return this.images;
    }

    /**
     * @return the members
     */
    public String getMembers() {
        return this.members;
    }

    /**
     * @return the metadata
     */
    public String getMetadata() {
        return this.metadata;
    }

    /**
     * @return the visuals
     */
    public String getVisuals() {
        return this.visuals;
    }

    /**
     * @param images the images to set
     */
    public void setImages(String images) {
        this.images = images;
    }

    /**
     * @param members the members to set
     */
    public void setMembers(String members) {
        this.members = members;
    }

    /**
     * @param metadata the metadata to set
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * @param visuals the visuals to set
     */
    public void setVisuals(String visuals) {
        this.visuals = visuals;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ProjectLinks [images=").append(this.images)
                .append(", members=").append(this.members).append(", metadata=")
                .append(this.metadata).append(", visuals=").append(this.visuals)
                .append(", self=").append(this.self).append("]");
        return builder.toString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.self);
        dest.writeString(this.images);
        dest.writeString(this.members);
        dest.writeString(this.metadata);
        dest.writeString(this.visuals);
    }

}
