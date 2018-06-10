package com.blueangels.psfm.ui.fragments.medical;

import com.blueangels.psfm.base.PerFragmentScope;

import dagger.Component;

/**
 * Created by Leon on 25-12-17.
 */
@Component(modules = { MedicalFragmentModule.class})
@PerFragmentScope
public interface MedicalFragmentComponent {

    void inject(MedicalFragment medicalFragment);

}
