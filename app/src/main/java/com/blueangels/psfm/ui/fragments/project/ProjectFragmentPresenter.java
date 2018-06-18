package com.blueangels.psfm.ui.fragments.project;

import android.content.Context;

import com.blueangels.psfm.base.BasePresenter;

/**
 * Created by Leon on 25-12-17.
 */

public class ProjectFragmentPresenter extends BasePresenter<ProjectFragmentContract.View>
        implements ProjectFragmentContract.Presenter {

    private Context context;

    ProjectFragmentPresenter(Context context) {
        this.context = context;
    }

    @Override
    public void setupComment() {
        getView().enableScrollingInComments();
    }


    @Override
    public void setupSpinnerAdapter() {
        getView().populateSpinner();
    }

    @Override
    public void setupUpdateListener() {
        getView().updateListener();
    }

    @Override
    public void saveListener() {
        getView().saveListener();
    }
}
