package com.blueangels.psfm.ui.fragments.gate;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.blueangels.psfm.R;
import com.blueangels.psfm.base.BaseView;
import com.blueangels.psfm.base.FragmentContext;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.blueangels.psfm.utils.Constants.CONSTANTS;

/**
 * A simple {@link Fragment} subclass.
 */
public class GateFragment extends Fragment implements GateFragmentContract.View, BaseView {

    @Inject
    GateFragmentPresenter gateFragmentPresenter;
    @Inject
    ArrayAdapter<String> stringArrayAdapter;
    @BindView(R.id.total_vehicle_inward)
    TextInputEditText totalVehicleInward;
    @BindView(R.id.layover)
    TextInputEditText layover;
    @BindView(R.id.any_updates)
    MaterialBetterSpinner anyUpdates;
    @BindView(R.id.comments)
    TextInputEditText comments;
    @BindView(R.id.commentsLayout)
    TextInputLayout commentsLayout;

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
        //setup spinner
        gateFragmentPresenter.setupSpinnerAdapter();
        //setup scrolling in comments
        gateFragmentPresenter.setupComment();
        //setup theft listener
        gateFragmentPresenter.setupUpdateListener();

        return view;
    }

    @Override
    public void updateListener() {

        anyUpdates.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (CONSTANTS[0].equals(s.toString()))
                    commentsLayout.setVisibility(View.VISIBLE);
                else
                    commentsLayout.setVisibility(View.GONE);
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void enableScrollingInComments() {
        comments.setHorizontallyScrolling(false);
        comments.setOnTouchListener((v, event) -> {
            v.getParent().requestDisallowInterceptTouchEvent(true);
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_UP:
                    v.getParent().requestDisallowInterceptTouchEvent(false);
                    break;
            }
            return false;
        });

    }


    @Override
    public void populateSpinner() {
        anyUpdates.setAdapter(stringArrayAdapter);
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
        comments.setText("");
        anyUpdates.setText("");
    }
}
