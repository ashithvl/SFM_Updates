package com.blueangels.psfm.ui.activities.home;

import com.blueangels.psfm.base.PerActivityScope;

import dagger.Component;

@Component(modules = {HomeModule.class})
@PerActivityScope
public interface PaymentComponent {

    void inject(HomeActivity paymentActivity);

}
