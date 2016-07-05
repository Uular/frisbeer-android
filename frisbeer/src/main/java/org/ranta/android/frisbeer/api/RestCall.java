package org.ranta.android.frisbeer.api;

import java.io.IOException;


import okhttp3.Call;
import okhttp3.Response;
import timber.log.Timber;

/**
 * Created by Uula on 2016-07-04.
 */
public class RestCall<R> {
    private Call mCall;
    private ResponseParser<R> mResponseParser;

    RestCall (Call okHttpCall, ResponseParser<R> responseParser) {
        mCall = okHttpCall;
        mResponseParser = responseParser;
    }

    public RestResponse<R> execute() throws IOException {
        final Response response = mCall.execute();
        R result = null;
        boolean success = false;
        if (response.isSuccessful()) {
            try {
                result = mResponseParser.parse(response);
                success = true;
            } catch (ResponseParser.ResponseParseException e) {
                Timber.e(e, "Error parsing response");
            }
        }

        return new RestResponse<>(success, response, result);
    }

    public void cancel() {

    }
}
