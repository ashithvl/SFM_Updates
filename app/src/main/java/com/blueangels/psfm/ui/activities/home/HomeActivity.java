package com.blueangels.psfm.ui.activities.home;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.Toast;

import com.blueangels.psfm.PsfmApplication;
import com.blueangels.psfm.R;
import com.blueangels.psfm.base.ActivityContext;
import com.blueangels.psfm.ui.fragments.fire.FireFragment;
import com.blueangels.psfm.ui.fragments.gate.GateFragment;
import com.blueangels.psfm.ui.fragments.medical.MedicalFragment;
import com.blueangels.psfm.ui.fragments.security.SecurityFragment;
import com.blueangels.psfm.utils.PreferencesAppHelper;
import com.blueangels.psfm.utils.SectionsStatePagerAdapter;

import java.util.Calendar;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements HomeContract.View {
    private static final String TAG = "HomeActivity";
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.container)
    ViewPager pager;

    @Inject
    HomePresenter homePresenter;
    @Inject
    SectionsStatePagerAdapter adapter;
    @Inject
    SecurityFragment securityFragment;
    @Inject
    FireFragment fireFragment;
    @Inject
    MedicalFragment medicalFragment;
    @Inject
    GateFragment gateFragment;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private Context mContext = HomeActivity.this;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle("SFM Updates");

        DaggerPaymentComponent.builder()
                .activityContext(new ActivityContext(mContext))
                .homeModule(new HomeModule(this))
                .build()
                .inject(this);

        homePresenter.attach(this);
        homePresenter.setupViewPager(adapter, securityFragment, fireFragment, medicalFragment, gateFragment);
    }

    @Override
    public void showTabLayout(SectionsStatePagerAdapter adapter) {
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.date) {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(mContext,
                    AlertDialog.THEME_HOLO_DARK, mDateSetListener, year, month, day);
            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
            mDateSetListener = (datePicker, year1, month1, day1) -> {
                month1 = month1 + 1;
                PreferencesAppHelper.setDate(day1 + "/" + month1 + "/" + year1);
            };
        } else if (item.getItemId() == R.id.save) {

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        homePresenter.detach();
    }

}
