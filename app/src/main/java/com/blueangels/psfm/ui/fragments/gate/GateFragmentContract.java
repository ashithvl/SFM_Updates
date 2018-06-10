package com.blueangels.psfm.ui.fragments.gate;

import com.blueangels.psfm.base.BaseMvpPresenter;

/**
 * Created by Leon on 25-12-17.
 */

public interface GateFragmentContract {

    interface Presenter extends BaseMvpPresenter<GateFragmentContract.View> {

        void setupComment();

        void setupSpinnerAdapter();

        void setupUpdateListener();

    }

    // Action callbacks. Activity/Fragment will implement
    interface View {

        void enableScrollingInComments();

        void populateSpinner();

        void updateListener();
    }

}
