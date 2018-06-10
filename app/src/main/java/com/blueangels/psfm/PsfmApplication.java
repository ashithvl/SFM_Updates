package com.blueangels.psfm;


import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;

import javax.inject.Inject;

/**
 * Created by Shriram on 4/11/2018.
 */

public class PsfmApplication extends Application {

    private static Context context;
    @Inject
    SharedPreferences sharedPreferences;
    private PsfmComponent psfmComponent;

    public static PsfmApplication get(Activity activity) {
        return (PsfmApplication) activity.getApplication();
    }

    public static PsfmApplication getApplication(Application application) {
        return (PsfmApplication) application;
    }

    public static Context getAppContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // LeakCanary.install(this);
        // JodaTimeAndroid.init(this);

        context = this;

        psfmComponent = DaggerPsfmComponent.builder()
                .psfmAppModule(new PsfmAppModule(this))
                .sharedPreferenceModule(new SharedPreferenceModule())
                .build();

        psfmComponent.inject(this);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public PsfmComponent getSwsComponent() {
        return psfmComponent;
    }


}
