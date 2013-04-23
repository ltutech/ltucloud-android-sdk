package com.ltu.sdk.resources.links;

/**
 * Represent the links related to a resource.
 *
 */
public class Links {

    /**
     * Self reference, all Links objects have this reference.
     */
    protected String self;

    /**
     * @return the self
     */
    public String getSelf() {
        return this.self;
    }

    /**
     * @param self
     *            the self to set
     */
    public void setSelf(String self) {
        this.self = self;
    }

}
