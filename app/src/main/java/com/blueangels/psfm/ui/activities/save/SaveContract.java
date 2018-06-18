package com.blueangels.psfm.ui.activities.save;

import com.blueangels.psfm.base.BaseMvpPresenter;

/**
 * Created by Leon on 20-12-17.
 */

public interface SaveContract {

    interface Presenter extends BaseMvpPresenter<SaveContract.View> {
        void getText();
    }

    // Action callbacks. Activity/Fragment will implement
    interface View {
        void setText();
    }

}
