package com.blueangels.psfm.ui.fragments.fire;

import android.content.Context;

import com.blueangels.psfm.base.BasePresenter;

/**
 * Created by Leon on 25-12-17.
 */

public class FireFragmentPresenter extends BasePresenter<FireFragmentContract.View>
        implements FireFragmentContract.Presenter {

    private Context context;

    FireFragmentPresenter(Context context) {
        this.context = context;
    }

    @Override
    public void setupRemarks() {
        getView().enableScrollingInRemarks();
    }

    @Override
    public void saveListener() {
        getView().saveListener();
    }
}
