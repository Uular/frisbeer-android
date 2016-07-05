package org.ranta.android.frisbeer.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.json.JSONArray;
import org.ranta.android.frisbeer.model.Game;
import org.ranta.android.frisbeer.model.Player;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Http-REST implementation of Frisbeer API client for JSON data types
 */
public class FrisbeerClient {

    private final MediaType TYPE_JSON = MediaType.parse("application/json");

    private final String PUT = "PUT";
    private final String GET = "GET";

    private String mEndpoint;

    private OkHttpClient mClient;

    public FrisbeerClient(String endpoint) {
        this(endpoint, 10, 10, 10);
    }

    public FrisbeerClient(String endpoint,
                          int timeoutSeconds,
                          int writeTimeoutSeconds,
                          int readTimeoutSeconds) {
        mEndpoint = endpoint;
        mClient = new OkHttpClient.Builder()
                .connectTimeout(timeoutSeconds, TimeUnit.SECONDS)
                .readTimeout(readTimeoutSeconds, TimeUnit.SECONDS)
                .writeTimeout(writeTimeoutSeconds, TimeUnit.SECONDS)
                .build();
    }

    public RestCall<JSONArray> getPlayers() {
        final String url = newUrlBuilder()
                .withPath("/players")
                .toUrlString();
        final Request request = newGetRequest(url);
        return new RestCall<>(mClient.newCall(request), new JsonArrayParser());
    }

    public RestCall<JSONArray> getGames() {
        final String url = newUrlBuilder()
                .withPath("/games")
                .toUrlString();
        final Request request = newGetRequest(url);
        return new RestCall<>(mClient.newCall(request), new JsonArrayParser());
    }

    public boolean postPlayer(@NonNull Player player) {
        return false;
    }

    public boolean postGame(@NonNull Game game) {
        return false;
    }

    private UrlBuilder newUrlBuilder() {
        return new UrlBuilder(mEndpoint);
    }

    private Request newGetRequest(@NonNull String urlString) {
        return newRequest(urlString, GET, null);
    }

    private Request newRequest(@NonNull String urlString,
                               @NonNull String method,
                               @Nullable byte[] body) {
        try {
            final URL url = new URL(urlString);

            return new Request.Builder()
                    .url(url)
                    .method(method, body == null ? null : RequestBody.create(TYPE_JSON, body))
                    .build();

        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Url is bad", e);
        }
    }
}
