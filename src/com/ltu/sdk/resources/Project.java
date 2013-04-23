package com.ltu.sdk.resources;

import android.os.Parcel;
import android.os.Parcelable;

import com.ltu.sdk.resources.links.ProjectLinks;

/**
 * Represent a LookThatUp project i.e. Visuals, Images, MetaData, ...
 *
 */
public class Project extends ApiResourceData implements Parcelable {

    public static final Parcelable.Creator<Project> CREATOR = new Parcelable.Creator<Project>() {

        @Override
        public Project createFromParcel(Parcel source) {
            return new Project(source);
        }

        @Override
        public Project[] newArray(int size) {
            return new Project[size];
        }

    };

    /**
     * List of URL linked to this project i.e. visuals, metadata, ...
     */
    private ProjectLinks _links;

    /**
     * Short description of the project
     */
    private String description;

    /**
     * Is the project active
     */
    private boolean is_active;

    /**
     * Number of images linked to this
     */
    private int nb_images;

    /**
     * Number of visuals linked to this project
     */
    private int nb_visuals;

    /**
     * Is the project searchable by default
     */
    private boolean search_by_default;

    /**
     * Title of this project as displayed to the user
     */
    private String title;

    private Project(Parcel source) {
        this._links = source
                .readParcelable(ProjectLinks.class.getClassLoader());
        this.description = source.readString();
        this.id = source.readInt();
        this.is_active = source.readByte() == 1;
        this.nb_images = source.readInt();
        this.nb_visuals = source.readInt();
        this.search_by_default = source.readByte() == 1;
        this.title = source.readString();
    }

    public Project() {
        super();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @return the _links
     */
    public ProjectLinks getLinks() {
        return this._links;
    }

    /**
     * @return the nb_images
     */
    public int getNbImages() {
        return this.nb_images;
    }

    /**
     * @return the nb_visuals
     */
    public int getNbVisuals() {
        return this.nb_visuals;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * @return the is_active
     */
    public boolean isActive() {
        return this.is_active;
    }

    /**
     * @return the search_by_default
     */
    public boolean isSearchByDefault() {
        return this.search_by_default;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @param is_active
     *            the is_active to set
     */
    public void setIsActive(boolean is_active) {
        this.is_active = is_active;
    }

    /**
     * @param _links
     *            the _links to set
     */
    public void setLinks(ProjectLinks links) {
        this._links = links;
    }

    /**
     * @param nb_images
     *            the nb_images to set
     */
    public void setNbImages(int nb_images) {
        this.nb_images = nb_images;
    }

    /**
     * @param nb_visuals
     *            the nb_visuals to set
     */
    public void setNbVisuals(int nb_visuals) {
        this.nb_visuals = nb_visuals;
    }

    /**
     * @param search_by_default
     *            the search_by_default to set
     */
    public void setSearchByDefault(boolean search_by_default) {
        this.search_by_default = search_by_default;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Project [_links=").append(this._links)
                .append(", description=").append(this.description)
                .append(", id=").append(this.id).append(", is_active=")
                .append(this.is_active).append(", nb_images=")
                .append(this.nb_images).append(", nb_visuals=")
                .append(this.nb_visuals).append(", search_by_default=")
                .append(this.search_by_default).append(", title=")
                .append(this.title).append("]");
        return builder.toString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this._links, flags);
        dest.writeString(this.description);
        dest.writeInt(this.id);
        dest.writeByte((byte) (this.is_active ? 1 : 0));
        dest.writeInt(this.nb_images);
        dest.writeInt(this.nb_visuals);
        dest.writeByte((byte) (this.search_by_default ? 1 : 0));
        dest.writeString(this.title);
    }

}
