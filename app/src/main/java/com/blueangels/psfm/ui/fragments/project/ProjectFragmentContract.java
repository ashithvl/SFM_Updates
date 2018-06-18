package com.blueangels.psfm.ui.fragments.project;

import com.blueangels.psfm.base.BaseMvpPresenter;

/**
 * Created by Leon on 25-12-17.
 */

public interface ProjectFragmentContract {

    interface Presenter extends BaseMvpPresenter<ProjectFragmentContract.View> {

        void setupComment();

        void setupSpinnerAdapter();

        void setupUpdateListener();
        void saveListener();

    }

    // Action callbacks. Activity/Fragment will implement
    interface View {

        void enableScrollingInComments();

        void populateSpinner();

        void updateListener();
        void saveListener();
    }

}
