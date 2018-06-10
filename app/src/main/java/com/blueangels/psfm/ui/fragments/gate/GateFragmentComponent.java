package com.blueangels.psfm.ui.fragments.gate;

import com.blueangels.psfm.base.PerFragmentScope;

import dagger.Component;

/**
 * Created by Leon on 25-12-17.
 */
@Component(modules = {GateFragmentModule.class})
@PerFragmentScope
public interface GateFragmentComponent {

    void inject(GateFragment gateFragment);

}
