package com.ltu.sdk.requests;

import com.ltu.sdk.resources.Project;

/**
 * Helper class to operate on {@link Project}
 *
 */
public class ProjectRequests extends Requests<Project> {

    public ProjectRequests(String username, String password) {
        super(Project.class, username, password);
    }

}
