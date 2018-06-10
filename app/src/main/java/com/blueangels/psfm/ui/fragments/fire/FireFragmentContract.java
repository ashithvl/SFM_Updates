package com.blueangels.psfm.ui.fragments.fire;

import com.blueangels.psfm.base.BaseMvpPresenter;

/**
 * Created by Leon on 25-12-17.
 */

public interface FireFragmentContract {

    interface Presenter extends BaseMvpPresenter<FireFragmentContract.View> {

        void setupRemarks();
    }

    // Action callbacks. Activity/Fragment will implement
    interface View {

        void enableScrollingInRemarks();
    }

}
