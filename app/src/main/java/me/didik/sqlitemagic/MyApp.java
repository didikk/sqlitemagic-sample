package me.didik.sqlitemagic;

import android.app.Application;

import com.siimkinks.sqlitemagic.SqliteMagic;

import timber.log.Timber;

/**
 * Created by didik on 11/30/16.
 * App
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SqliteMagic.init(this);
        SqliteMagic.setLoggingEnabled(true);

        Timber.plant(new Timber.DebugTree());
    }
}
