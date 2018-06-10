package com.blueangels.psfm;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

@Module
@PsfmScope
public class PsfmAppModule {

    Application mApplication;

    public PsfmAppModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @PsfmScope
    Application provideApplication() {
        return mApplication;
    }

}
