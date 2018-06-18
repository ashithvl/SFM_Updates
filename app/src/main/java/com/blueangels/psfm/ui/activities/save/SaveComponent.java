package com.blueangels.psfm.ui.activities.save;

import com.blueangels.psfm.base.PerActivityScope;

import dagger.Component;

@Component(modules = {SaveModule.class})
@PerActivityScope
public interface SaveComponent {

    void inject(SaveActivity paymentActivity);

}
