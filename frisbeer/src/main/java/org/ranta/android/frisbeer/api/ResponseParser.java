package org.ranta.android.frisbeer.api;

import okhttp3.Response;

/**
 * Created by Uula on 2016-07-04.
 */
public interface ResponseParser<R> {

    R parse(Response response) throws ResponseParseException;

    final class ResponseParseException extends Exception {
        public ResponseParseException(String message) {
            super(message);
        }
    }
}
