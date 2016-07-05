package org.ranta.android.frisbeer.api;

import okhttp3.Response;

/**
 * Created by Uula on 2016-07-04.
 */
public class RestResponse<R> {

    private final boolean mSuccess;

    private Response mResponse;

    private final R mResult;

    RestResponse(boolean success, Response response, R result) {
        mSuccess = success;
        mResponse = response;
        mResult = result;
    }

    public R getResult() {
        return mResult;
    }

    public Response getResponse() {
        return mResponse;
    }

    public boolean isSuccess() {
        return mSuccess;
    }
}
