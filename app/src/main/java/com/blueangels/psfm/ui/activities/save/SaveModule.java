package com.blueangels.psfm.ui.activities.save;

import android.content.Context;

import com.blueangels.psfm.base.ActivityContext;
import com.blueangels.psfm.base.PerActivityScope;
import com.blueangels.psfm.ui.fragments.fire.FireFragment;
import com.blueangels.psfm.ui.fragments.gate.GateFragment;
import com.blueangels.psfm.ui.fragments.medical.MedicalFragment;
import com.blueangels.psfm.ui.fragments.project.ProjectFragment;
import com.blueangels.psfm.ui.fragments.security.SecurityFragment;
import com.blueangels.psfm.utils.SectionsStatePagerAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Leon on 25-12-17.
 */
@Module(includes = {ActivityContext.class})
@PerActivityScope
class SaveModule {

    private SaveActivity saveActivity;

    SaveModule(SaveActivity saveActivity) {
        this.saveActivity = saveActivity;
    }

    @Provides
    @PerActivityScope
    SavePresenter providesHomePresenter(Context context) {
        return new SavePresenter(context);
    }

    @Provides
    @PerActivityScope
    SectionsStatePagerAdapter providesSectionsStatePagerAdapter() {
        return new SectionsStatePagerAdapter(saveActivity.getSupportFragmentManager());
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
    @Provides
    @PerActivityScope
    ProjectFragment providesProjectFragment() {
        return new ProjectFragment();
    }

}
