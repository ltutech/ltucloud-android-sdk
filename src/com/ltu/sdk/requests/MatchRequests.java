package com.ltu.sdk.requests;

import com.ltu.sdk.client.RequestParamsBuilder;
import com.ltu.sdk.handlers.PaginatedResponseHandler;
import com.ltu.sdk.resources.Match;
import com.ltu.sdk.resources.Query;

/**
 * Helper class to operate on {@link Match}
 *
 */
public class MatchRequests extends Requests<Match> {

    public MatchRequests(String username, String password) {
        super(Match.class, username, password);
    }

    /**
     * List the {@link Match}es for a given {@link Query}
     *
     * @param query
     *            the {@link Query} from which we want to retrieve the
     *            {@link Match}es
     * @param builder
     *            a {@link RequestParamsBuilder} with all the parameters you
     *            want to pass in the request
     * @param handler
     *            a {@link PaginatedResponseHandler} to manage the API response
     */
    public void listForQuery(Query query, RequestParamsBuilder builder,
            PaginatedResponseHandler<Match> handler) {
        this.client.listResources(query, this.type, builder, handler);
    }

}
