package com.ltu.sdk.resources;

import java.util.List;

public class PaginatedResult<T extends ApiResourceData> {

    /**
     * {@link Pagination} object to navigate the list of resources
     */
    private Pagination pagination;

    /**
     * Holds the list of {@link ApiResourceData}
     */
    private List<T> results;

    /**
     * @return the pagination
     */
    public Pagination getPagination() {
        return this.pagination;
    }

    /**
     * @return the results
     */
    public List<T> getResults() {
        return this.results;
    }

    /**
     * @param pagination
     *            the pagination to set
     */
    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    /**
     * @param results
     *            the results to set
     */
    public void setResults(List<T> results) {
        this.results = results;
    }

}
