package com.blueangels.psfm.ui.fragments.gate;

import android.content.Context;

import com.blueangels.psfm.base.BasePresenter;

/**
 * Created by Leon on 25-12-17.
 */

public class GateFragmentPresenter extends BasePresenter<GateFragmentContract.View>
        implements GateFragmentContract.Presenter {

    private Context context;

    GateFragmentPresenter(Context context) {
        this.context = context;
    }

    @Override
    public void saveListener() {
        getView().saveListener();
    }
}
