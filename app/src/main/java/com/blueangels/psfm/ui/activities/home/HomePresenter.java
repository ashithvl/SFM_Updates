package com.blueangels.psfm.ui.activities.home;

import android.content.Context;
import android.support.v4.view.ViewPager;

import com.blueangels.psfm.base.BasePresenter;
import com.blueangels.psfm.ui.fragments.fire.FireFragment;
import com.blueangels.psfm.ui.fragments.gate.GateFragment;
import com.blueangels.psfm.ui.fragments.medical.MedicalFragment;
import com.blueangels.psfm.ui.fragments.security.SecurityFragment;
import com.blueangels.psfm.utils.SectionsStatePagerAdapter;
import com.blueangels.psfm.R;

/**
 * Created by Leon on 20-12-17.
 */

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    private Context context;

    HomePresenter(Context context) {
        this.context = context;
    }

    @Override
    public void setupViewPager( SectionsStatePagerAdapter adapter, SecurityFragment securityFragment,
                               FireFragment fireFragment , MedicalFragment medicalFragment , GateFragment gateFragment) {
        adapter.addFragment(securityFragment, context.getString(R.string.security));
        adapter.addFragment(fireFragment, context.getString(R.string.fire));
        adapter.addFragment(medicalFragment, context.getString(R.string.medical));
        adapter.addFragment(gateFragment, context.getString(R.string.gate));
        getView().showTabLayout(adapter);
    }
}
