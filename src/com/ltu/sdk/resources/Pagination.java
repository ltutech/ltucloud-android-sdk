package com.ltu.sdk.resources;

import com.ltu.sdk.resources.links.PaginationLinks;

public class Pagination {

    /**
     * {@link PaginationLinks} to current, next and previous pages
     */
    private PaginationLinks _links;

    /**
     * Maximum number of resources shown
     */
    private int limit;

    /**
     * The total amount of page given a specific limit
     */
    private int nb_pages;

    /**
     * The actual number of resources shown
     */
    private int nb_resources;

    /**
     * The page number
     */
    private int page;

    /**
     * @return the limit
     */
    public int getLimit() {
        return this.limit;
    }

    /**
     * @return the _links
     */
    public PaginationLinks getLinks() {
        return this._links;
    }

    /**
     * @return the nb_page
     */
    public int getNbPages() {
        return this.nb_pages;
    }

    /**
     * @return the nb_resources
     */
    public int getNbResources() {
        return this.nb_resources;
    }

    /**
     * @return the page
     */
    public int getPage() {
        return this.page;
    }

    /**
     * @param limit
     *            the limit to set
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * @param _links
     *            the _links to set
     */
    public void setLinks(PaginationLinks _links) {
        this._links = _links;
    }

    /**
     * @param nb_pages
     *            the nb_page to set
     */
    public void setNbPages(int nb_pages) {
        this.nb_pages = nb_pages;
    }

    /**
     * @param nb_resources
     *            the nb_resources to set
     */
    public void setNbResources(int nb_resources) {
        this.nb_resources = nb_resources;
    }

    /**
     * @param page
     *            the page to set
     */
    public void setPage(int page) {
        this.page = page;
    }
}
