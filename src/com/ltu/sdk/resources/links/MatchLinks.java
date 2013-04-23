package com.ltu.sdk.resources.links;

import com.ltu.sdk.resources.Query;

public class MatchLinks extends Links {

    /**
     * Link to the associated {@link Query}
     */
    private String query;

    /**
     * @return the query
     */
    public String getQuery() {
        return this.query;
    }

    /**
     * @param query the query to set
     */
    public void setQuery(String query) {
        this.query = query;
    }

}
