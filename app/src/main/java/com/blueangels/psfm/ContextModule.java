package com.blueangels.psfm;

import android.content.Context;

import dagger.Module;
import dagger.Provides;


@Module
public class ContextModule {

    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @PsfmScope
    public Context getContext() {
        return context;
    }
}
