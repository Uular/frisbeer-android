package org.ranta.android.frisbeer.api;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

import okhttp3.Response;

/**
 * Parser that converts http response to JSONArray
 */
public class JsonArrayParser implements ResponseParser<JSONArray> {
    @Override
    public JSONArray parse(Response response) throws ResponseParseException {
        try {
            return new JSONArray(response.body().string());
        } catch (JSONException e) {
            throw new ResponseParseException("Failed to parse JSONArray");
        } catch (IOException e) {
            throw new ResponseParseException("Error reading response body");
        }
    }
}
