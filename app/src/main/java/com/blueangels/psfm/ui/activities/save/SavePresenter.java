package com.blueangels.psfm.ui.activities.save;

import android.content.Context;

import com.blueangels.psfm.R;
import com.blueangels.psfm.base.BasePresenter;
import com.blueangels.psfm.ui.fragments.fire.FireFragment;
import com.blueangels.psfm.ui.fragments.gate.GateFragment;
import com.blueangels.psfm.ui.fragments.medical.MedicalFragment;
import com.blueangels.psfm.ui.fragments.project.ProjectFragment;
import com.blueangels.psfm.ui.fragments.security.SecurityFragment;
import com.blueangels.psfm.utils.SectionsStatePagerAdapter;

/**
 * Created by Leon on 20-12-17.
 */

public class SavePresenter extends BasePresenter<SaveContract.View> implements SaveContract.Presenter {

    private Context context;

    SavePresenter(Context context) {
        this.context = context;
    }

    @Override
    public void getText() {
        getView().setText();
    }
}
