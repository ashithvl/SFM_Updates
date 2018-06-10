package com.blueangels.psfm;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Shriram on 4/11/2018.
 */
@Module(includes = PsfmAppModule.class)
public class SharedPreferenceModule {

    @Provides
    @PsfmScope
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

}
