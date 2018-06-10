package com.blueangels.psfm.ui.fragments.medical;

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
public class MedicalFragmentModule {

    @Provides
    @PerFragmentScope
    MedicalFragmentPresenter providesPaymentHistoryFragmentPresenter(Context context) {
        return new MedicalFragmentPresenter(context);
    }
}
