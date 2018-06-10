package com.blueangels.psfm.ui.activities.home;

import android.content.Context;

import com.blueangels.psfm.base.ActivityContext;
import com.blueangels.psfm.base.PerActivityScope;
import com.blueangels.psfm.ui.fragments.fire.FireFragment;
import com.blueangels.psfm.ui.fragments.gate.GateFragment;
import com.blueangels.psfm.ui.fragments.medical.MedicalFragment;
import com.blueangels.psfm.ui.fragments.security.SecurityFragment;
import com.blueangels.psfm.utils.SectionsStatePagerAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Leon on 25-12-17.
 */
@Module(includes = {ActivityContext.class})
@PerActivityScope
class HomeModule {

    private HomeActivity homeActivity;

    HomeModule(HomeActivity homeActivity) {
        this.homeActivity = homeActivity;
    }

    @Provides
    @PerActivityScope
    HomePresenter providesHomePresenter(Context context) {
        return new HomePresenter(context);
    }

    @Provides
    @PerActivityScope
    SectionsStatePagerAdapter providesSectionsStatePagerAdapter() {
        return new SectionsStatePagerAdapter(homeActivity.getSupportFragmentManager());
    }

    @Provides
    @PerActivityScope
    SecurityFragment providesSecurityFragment() {
        return new SecurityFragment();
    }

    @Provides
    @PerActivityScope
    GateFragment providesGateFragment() {
        return new GateFragment();
    }

    @Provides
    @PerActivityScope
    FireFragment providesFireFragment() {
        return new FireFragment();
    }

    @Provides
    @PerActivityScope
    MedicalFragment providesMedicalFragment() {
        return new MedicalFragment();
    }

}
