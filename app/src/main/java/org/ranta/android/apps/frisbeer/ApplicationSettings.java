package org.ranta.android.apps.frisbeer;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import butterknife.BindString;
import butterknife.ButterKnife;

/**
 * Wrapper for Android's SharedPreferences
 */
public class ApplicationSettings {
    SharedPreferences mPrefs;

    final String KEY_ENDPOINT;

    public ApplicationSettings(@NonNull Context context) {
        KEY_ENDPOINT = context.getString(R.string.pref_key_endpoint);
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public String apiEndPoint() {
        return mPrefs.getString(KEY_ENDPOINT, BuildConfig.ENDPOINT);
    }
}
