package com.ltu.sdk.requests;

import java.util.List;

import android.util.Pair;

import com.ltu.sdk.client.RequestParamsBuilder;
import com.ltu.sdk.handlers.GenericResponseHandler;
import com.ltu.sdk.resources.MetaData;
import com.ltu.sdk.resources.visuals.Visual;
import com.ltu.sdk.utils.RequestParamsValuesHelper;

/**
 * Helper class to operate on {@link MetaData}s
 *
 */
public class MetaDataRequests extends VisualSubResourceRequests<MetaData> {

    /**
     *
     * @param username
     *            username for the API
     * @param password
     *            associated password
     */
    public MetaDataRequests(String username, String password) {
        super(MetaData.class, username, password);
    }

    /**
     * Add {@link MetaData} to a given {@link Visual}
     *
     * @param visual
     *            the {@link Visual} to which you want to add {@link MetaData}
     * @param metadataList
     *            the metadata as a list of key/value
     * @param handler
     *            a {@link GenericResponseHandler} to manage the API response
     */
    public void addToVisual(Visual visual,
            List<Pair<String, String>> metadataList,
            GenericResponseHandler<MetaData> handler) {
        RequestParamsBuilder builder = new RequestParamsBuilder();
        RequestParamsValuesHelper.addMetaDataToParams(builder, metadataList);
        super.addToVisual(visual, builder, handler);
    }

}
