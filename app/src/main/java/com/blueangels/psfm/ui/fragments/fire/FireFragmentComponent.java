package com.blueangels.psfm.ui.fragments.fire;

import com.blueangels.psfm.base.PerFragmentScope;

import dagger.Component;

/**
 * Created by Leon on 25-12-17.
 */
@Component(modules = {FireFragmentModule.class})
@PerFragmentScope
public interface FireFragmentComponent {

    void inject(FireFragment fireFragment);

}
