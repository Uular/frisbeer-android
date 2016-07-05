package org.ranta.android.apps.frisbeer;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

/**
 * Base application to bind Timber
 */
public class FrisbeerApp extends Application {
    @Override
    public void onCreate() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }


        super.onCreate();
        Fabric.with(this, new Crashlytics());
    }
}
