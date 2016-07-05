package org.ranta.android.frisbeer.api;

import android.support.annotation.NonNull;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

/**
 * Created by Uula on 2016-07-04.
 */
public class UrlBuilder {

    private final StringBuilder mStringBuilder;

    private final String mEndpoint;

    private String mPath;

    public  UrlBuilder(@NonNull String endPoint) {
        mStringBuilder = new StringBuilder();
        mEndpoint = endPoint;
    }

    public UrlBuilder withPath(@NonNull String path, Object... args) {
        if (!path.startsWith("/")) {
            mStringBuilder.setLength(0);
            path = mStringBuilder.append("/").append(path).toString();
        } else {
            path = path;
        }

        mPath = args == null ? path : String.format(Locale.US, path, args);
        return this;
    }

    public String toUrlString() {
        mStringBuilder.setLength(0);
        mStringBuilder.append(formatEndpoint(mEndpoint));
        mStringBuilder.append(mPath);
        return mStringBuilder.toString();
    }

    public URL toURL() throws MalformedURLException {
        return new URL(toUrlString());
    }

    private static String formatEndpoint(@NonNull String endpoint) {
        if (endpoint.endsWith("/")) {
            return endpoint.substring(0, endpoint.length() -1);
        } else {
            return endpoint;
        }
    }
}
