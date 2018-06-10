package com.blueangels.psfm.ui.fragments.medical;

import com.blueangels.psfm.base.BaseMvpPresenter;

/**
 * Created by Leon on 25-12-17.
 */

public interface MedicalFragmentContract {

    interface Presenter extends BaseMvpPresenter<View> {

        void setupRemarks();
    }

    // Action callbacks. Activity/Fragment will implement
    interface View {

        void enableScrollingInRemarks();
    }

}
