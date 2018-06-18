package com.blueangels.psfm.ui.fragments.gate;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.blueangels.psfm.R;
import com.blueangels.psfm.base.BaseView;
import com.blueangels.psfm.base.FragmentContext;
import com.blueangels.psfm.utils.PreferencesAppHelper;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class GateFragment extends Fragment implements GateFragmentContract.View, BaseView {

    @Inject
    GateFragmentPresenter gateFragmentPresenter;
    @BindView(R.id.total_vehicle_inward)
    TextInputEditText totalVehicleInward;
    @BindView(R.id.layover)
    TextInputEditText layover;
    @BindView(R.id.remarks)
    TextInputEditText remarks;

    private Context context;
    private Unbinder unbinder;

    public GateFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = context;

        DaggerGateFragmentComponent.builder()
                .fragmentContext(new FragmentContext(context))
                .build()
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gate, container, false);
        unbinder = ButterKnife.bind(GateFragment.this, view);

        gateFragmentPresenter.attach(this);

        gateFragmentPresenter.saveListener();

        return view;
    }

    @Override
    public void saveListener() {
        totalVehicleInward.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                PreferencesAppHelper.setGateVehicleInward(s.toString());
            }
        });
        layover.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                PreferencesAppHelper.setGateLayover(s.toString());
            }
        });

        remarks.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                PreferencesAppHelper.setGateRemarks(s.toString());
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        gateFragmentPresenter.detach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.reset)
    public void onViewClicked() {
        totalVehicleInward.setText("");
        layover.setText("");
        remarks.setText("");
    }
}
