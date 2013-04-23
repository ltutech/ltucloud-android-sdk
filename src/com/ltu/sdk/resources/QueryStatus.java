package com.ltu.sdk.resources;

/**
 * Represents the status of a {@link Query}, it has simple helper method such
 * as:
 *
 * <ul>
 * <li>was the {@link Query} successful ?</li>
 * <li>does the {@link Query} has results</li>
 * </ul>
 */
public class QueryStatus {

    /**
     * Code of the {@link Query}
     */
    private int code;

    /**
     * Has the {@link Query} failed
     */
    private boolean is_error;

    /**
     *
     */
    private String name;

    /**
     * @return the code
     */
    public int getCode() {
        return this.code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @return the is_error
     */
    public boolean isError() {
        return this.is_error;
    }

    /**
     * @param is_error
     *            the is_error to set
     */
    public void setIsError(boolean is_error) {
        this.is_error = is_error;
    }

    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
