package com.blueangels.psfm.ui.fragments.fire;

import android.content.Context;

import com.blueangels.psfm.base.FragmentContext;
import com.blueangels.psfm.base.PerFragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Leon on 25-12-17.
 */
@Module(includes = {FragmentContext.class})
@PerFragmentScope
public class FireFragmentModule {

    @Provides
    @PerFragmentScope
    FireFragmentPresenter providesFireFragmentPresenter(Context context) {
        return new FireFragmentPresenter(context);
    }
}
