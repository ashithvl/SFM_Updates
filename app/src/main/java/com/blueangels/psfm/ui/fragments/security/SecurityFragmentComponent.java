package com.blueangels.psfm.ui.fragments.security;

import com.blueangels.psfm.base.PerFragmentScope;

import dagger.Component;

/**
 * Created by Leon on 25-12-17.
 */
@Component(modules = {SecurityFragmentModule.class})
@PerFragmentScope
public interface SecurityFragmentComponent {

    void inject(SecurityFragment securityFragment);

}
