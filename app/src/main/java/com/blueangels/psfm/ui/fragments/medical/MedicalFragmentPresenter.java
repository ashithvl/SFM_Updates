package com.blueangels.psfm.ui.fragments.medical;

import android.content.Context;

import com.blueangels.psfm.base.BasePresenter;

/**
 * Created by Leon on 25-12-17.
 */

public class MedicalFragmentPresenter extends BasePresenter<MedicalFragmentContract.View>
        implements MedicalFragmentContract.Presenter {

    private Context context;

    MedicalFragmentPresenter(Context context) {
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
