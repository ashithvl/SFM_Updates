package com.blueangels.psfm.ui.fragments.project;

import com.blueangels.psfm.base.PerFragmentScope;

import dagger.Component;

/**
 * Created by Leon on 25-12-17.
 */
@Component(modules = {ProjectFragmentModule.class})
@PerFragmentScope
public interface ProjectFragmentComponent {

    void inject(ProjectFragment projectFragment);

}
