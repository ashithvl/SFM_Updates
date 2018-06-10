package com.blueangels.psfm.ui.fragments.medical;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.blueangels.psfm.R;
import com.blueangels.psfm.base.BaseView;
import com.blueangels.psfm.base.FragmentContext;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class MedicalFragment extends Fragment implements MedicalFragmentContract.View, BaseView {

    @Inject
    MedicalFragmentPresenter medicalFragmentPresenter;
    @BindView(R.id.number_of_patients)
    TextInputEditText numberOfPatients;
    @BindView(R.id.occupational_injury)
    TextInputEditText occupationalInjury;
    @BindView(R.id.routine)
    TextInputEditText routine;
    @BindView(R.id.remarks)
    TextInputEditText remarks;

    private Context context;
    private Unbinder unbinder;

    public MedicalFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;

        DaggerMedicalFragmentComponent.builder()
                .fragmentContext(new FragmentContext(context))
                .build()
                .inject(this);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_medical, container, false);
        unbinder = ButterKnife.bind(MedicalFragment.this, view);

        medicalFragmentPresenter.attach(this);
        //setup scrolling in remarks
        medicalFragmentPresenter.setupRemarks();

        return view;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void enableScrollingInRemarks() {
        remarks.setHorizontallyScrolling(false);
        remarks.setOnTouchListener((v, event) -> {
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
    public void onDestroy() {
        super.onDestroy();
        medicalFragmentPresenter.detach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick(R.id.reset)
    public void onViewClicked() {
        numberOfPatients.setText("");
        occupationalInjury.setText("");
        remarks.setText("");
        routine.setText("");
    }
}
