package com.ltu.sdk.resources.links;

public class QueryLinks extends Links {

    /**
     * URL to the matches associated to a given query
     */
    private String matches;

    /**
     * @return the matches
     */
    public String getMatches() {
        return this.matches;
    }

    /**
     * @param matches the matches to set
     */
    public void setMatches(String matches) {
        this.matches = matches;
    }

}
