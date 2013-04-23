package com.ltu.sdk.client;

import java.util.HashMap;
import java.util.Map;

import com.ltu.sdk.resources.ApiResourceData;
import com.ltu.sdk.resources.Match;
import com.ltu.sdk.resources.MetaData;
import com.ltu.sdk.resources.Project;
import com.ltu.sdk.resources.Query;
import com.ltu.sdk.resources.images.Image;
import com.ltu.sdk.resources.visuals.Visual;

/**
 * Helper class to compute the path of an {@link ApiResourceData}.
 *
 */
class ResourcePathFactory {

    /**
     * Holds the hierarchy the the API resources.
     */
    private static final Map<Class<? extends ApiResourceData>, Path> apiHierarchy = new HashMap<Class<? extends ApiResourceData>, Path>() {

        /**
         * Required fields - auto-generated
         */
        private static final long serialVersionUID = 649322317850710588L;

        /**
         * Initialization of the resource hierarchy - based on the graph at
         * https://ltu2.ltutech.com/api/v1/
         */
        {
            this.put(Project.class, new Path("projects/", null));
            this.put(Visual.class, new Path("visuals/", Project.class));
            this.put(Image.class, new Path("images/", Visual.class));
            this.put(MetaData.class, new Path("metadata/", Visual.class));
            this.put(Query.class, new Path("queries/", null));
            this.put(Match.class, new Path("matches/", Query.class));
        }
    };

    /**
     * Retrieve the path of a given resource instance
     *
     * @param resource
     *            an instance of {@link ApiResourceData}
     * @return the full path to this {@link ApiResourceData} instance
     */
    public static String get(ApiResourceData resource) {
        return new StringBuilder(get(resource.getClass()))
                .append(resource.getId()).append("/").toString();

    }

    /**
     * Behaves just like {@link #get(ApiResourceData)} but doesn't require to
     * create an instance.
     *
     * @param resourceClass
     *            one of the {@link ApiResourceData} class
     * @param resourceId
     *            the id of the resource you want to get
     * @return the full path to the{@link ApiResourceData} instance with the
     *         given id
     */
    public static String get(Class<? extends ApiResourceData> resourceClass,
            int resourceId) {
        return new StringBuilder(get(resourceClass)).append(resourceId)
                .append("/").toString();
    }

    /**
     * Retrieve the path of the given resource
     *
     * @param resourceClass
     *            one of the {@link ApiResourceData} class
     * @return the full path of the resource
     */
    public static String get(Class<? extends ApiResourceData> resourceClass) {
        StringBuilder pathBuilder = new StringBuilder();
        Class<?> resourceApiClass = getAncestorInApiHierarchy(resourceClass);
        if (resourceClass != null) {
            Path path = apiHierarchy.get(resourceApiClass);
            Class<? extends ApiResourceData> resourceParent = path.getParent();
            if (resourceParent != null) {
                pathBuilder.append(ResourcePathFactory.get(resourceParent));
            }
            pathBuilder.append(path.getPath());
        }
        return pathBuilder.toString();
    }

    /**
     * Retrieve the first ancestor class that is in the API hierarchy.
     *
     * @param currentClass
     *            the class under examination
     * @return the first ancestor class that is in the API hierarchy or null if
     *         none was found.
     */
    private static Class<?> getAncestorInApiHierarchy(Class<?> currentClass) {
        if (apiHierarchy.containsKey(currentClass)) {
            return currentClass;
        }
        Class<?> superClass = currentClass.getSuperclass();
        if (apiHierarchy.containsKey(superClass)) {
            return superClass;
        } else if (!superClass.equals(Object.class)) {
            return getAncestorInApiHierarchy(superClass);
        }
        return null;
    }

    /**
     * Retrieve the path of a "sub" {@link ApiResourceData}
     *
     * @param parentResource
     *            the "parent" {@link ApiResourceData}
     * @param resource
     *            the "sub" {@link ApiResourceData}
     * @return the path to a "sub" {@link ApiResourceData}
     */
    public static <T extends ApiResourceData, V extends ApiResourceData> String getSubResourcePath(
            T parentResource, Class<V> resource) {
        StringBuilder pathBuilder = new StringBuilder();
        Class<?> subResourceApiClass = getAncestorInApiHierarchy(resource);
        if (subResourceApiClass != null) {
            Path path = apiHierarchy.get(subResourceApiClass);
            Class<? extends ApiResourceData> parentResourceClass = path
                    .getParent();
            if (parentResourceClass == parentResource.getClass()) {
                pathBuilder.append(get(parentResource)).append(path.getPath());
            } else if (parentResourceClass != null) {
                pathBuilder
                        .append(getSubResourcePath(parentResource,
                                parentResourceClass)).append(path.getPath());
            }
        }
        return pathBuilder.toString();
    }
}
