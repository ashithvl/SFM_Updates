package com.blueangels.psfm.ui.fragments.security;

import com.blueangels.psfm.base.BaseMvpPresenter;

/**
 * Created by Leon on 25-12-17.
 */

public interface SecurityFragmentContract {

    interface Presenter extends BaseMvpPresenter<View> {

        void setupComment();

        void setupRemarks();

        void setupSpinnerAdapter();

        void setupTheftListener();

    }

    // Action callbacks. Activity/Fragment will implement
    interface View {

        void enableScrollingInComments();

        void enableScrollingInRemarks();

        void populateSpinner();

        void theftListener();
    }

}
