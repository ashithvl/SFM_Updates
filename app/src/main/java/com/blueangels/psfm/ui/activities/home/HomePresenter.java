package com.blueangels.psfm.ui.activities.home;

import android.annotation.SuppressLint;
import android.content.Context;

import com.blueangels.psfm.R;
import com.blueangels.psfm.base.BasePresenter;
import com.blueangels.psfm.ui.fragments.fire.FireFragment;
import com.blueangels.psfm.ui.fragments.gate.GateFragment;
import com.blueangels.psfm.ui.fragments.medical.MedicalFragment;
import com.blueangels.psfm.ui.fragments.project.ProjectFragment;
import com.blueangels.psfm.ui.fragments.security.SecurityFragment;
import com.blueangels.psfm.utils.PreferencesAppHelper;
import com.blueangels.psfm.utils.SectionsStatePagerAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Leon on 20-12-17.
 */

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    private Context context;

    HomePresenter(Context context) {
        this.context = context;
    }

    @Override
    public void setupViewPager(SectionsStatePagerAdapter adapter, SecurityFragment securityFragment,
                               FireFragment fireFragment, MedicalFragment medicalFragment, GateFragment gateFragment,
                               ProjectFragment projectFragment) {
        adapter.addFragment(securityFragment, context.getString(R.string.security));
        adapter.addFragment(fireFragment, context.getString(R.string.fire));
        adapter.addFragment(medicalFragment, context.getString(R.string.medical));
        adapter.addFragment(gateFragment, context.getString(R.string.gate));
        adapter.addFragment(projectFragment, context.getString(R.string.project));
        getView().showTabLayout(adapter);
    }

    @Override
    public void setTodaysDate() {
        if (PreferencesAppHelper.getDate() == null) {
            Date c = Calendar.getInstance().getTime();
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            PreferencesAppHelper.setDate(df.format(c));
        }
    }
}
