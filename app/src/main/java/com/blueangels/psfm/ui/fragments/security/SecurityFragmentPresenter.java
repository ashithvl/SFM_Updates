package com.blueangels.psfm.ui.fragments.security;

import android.content.Context;

import com.blueangels.psfm.base.BasePresenter;

/**
 * Created by Leon on 25-12-17.
 */

public class SecurityFragmentPresenter extends BasePresenter<SecurityFragmentContract.View>
        implements SecurityFragmentContract.Presenter {

    private Context context;

    SecurityFragmentPresenter(Context context) {
        this.context = context;
    }

    @Override
    public void setupComment() {
        getView().enableScrollingInComments();
    }

    @Override
    public void setupRemarks() {
        getView().enableScrollingInRemarks();
    }

    @Override
    public void setupSpinnerAdapter() {
        getView().populateSpinner();
    }

    @Override
    public void setupTheftListener() {
        getView().theftListener();
    }
}
