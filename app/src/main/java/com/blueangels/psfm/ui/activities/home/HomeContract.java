package com.blueangels.psfm.ui.activities.home;

import android.support.v4.view.ViewPager;

import com.blueangels.psfm.base.BaseMvpPresenter;
import com.blueangels.psfm.ui.fragments.fire.FireFragment;
import com.blueangels.psfm.ui.fragments.gate.GateFragment;
import com.blueangels.psfm.ui.fragments.medical.MedicalFragment;
import com.blueangels.psfm.ui.fragments.security.SecurityFragment;
import com.blueangels.psfm.utils.SectionsStatePagerAdapter;

/**
 * Created by Leon on 20-12-17.
 */

public interface HomeContract {

    interface Presenter extends BaseMvpPresenter<HomeContract.View> {

        void setupViewPager(SectionsStatePagerAdapter adapter, SecurityFragment securityFragment
        , FireFragment fireFragment , MedicalFragment medicalFragment , GateFragment gateFragment);
    }

    // Action callbacks. Activity/Fragment will implement
    interface View {

        void showTabLayout(SectionsStatePagerAdapter adapter);

    }

}
