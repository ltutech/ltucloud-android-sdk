package com.ltu.sdk.requests;

import com.ltu.sdk.resources.images.Image;

/**
 * Helper class to operate on {@link Image}
 *
 */
public class ImageRequests extends VisualSubResourceRequests<Image> {

    /**
     *
     * @param username
     *            username for the API
     * @param password
     *            associated password
     */
    public ImageRequests(String username, String password) {
        super(Image.class, username, password);
    }
}
