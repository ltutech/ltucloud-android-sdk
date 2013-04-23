package com.ltu.sdk.resources.links;

public class PaginationLinks extends Links {

    private String next;

    private String previous;

    /**
     * @return the next
     */
    public String getNext() {
        return this.next;
    }

    /**
     * @return the previous
     */
    public String getPrevious() {
        return this.previous;
    }

    /**
     * @param next the next to set
     */
    public void setNext(String next) {
        this.next = next;
    }

    /**
     * @param previous the previous to set
     */
    public void setPrevious(String previous) {
        this.previous = previous;
    }

}
