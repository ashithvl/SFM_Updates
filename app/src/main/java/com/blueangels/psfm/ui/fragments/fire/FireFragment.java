package com.blueangels.psfm.ui.fragments.fire;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.blueangels.psfm.R;
import com.blueangels.psfm.base.BaseView;
import com.blueangels.psfm.base.FragmentContext;
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.OnSeekChangeListener;
import com.warkiz.widget.SeekParams;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class FireFragment extends Fragment implements FireFragmentContract.View, BaseView {

    @Inject
    FireFragmentPresenter fireFragmentPresenter;
    @BindView(R.id.total_fire_calls)
    TextInputEditText totalFireCalls;
    @BindView(R.id.incidental)
    TextInputEditText incidental;
    @BindView(R.id.non_incidental)
    TextInputEditText nonIncidental;
    @BindView(R.id.snake_calls)
    IndicatorSeekBar snakeCalls;
    @BindView(R.id.remarks)
    TextInputEditText remarks;

    private Context context;
    private Unbinder unbinder;
    private static final String TAG = "FireFragment";

    public FireFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = context;

        DaggerFireFragmentComponent.builder()
                .fragmentContext(new FragmentContext(context))
                .build()
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fire, container, false);
        unbinder = ButterKnife.bind(FireFragment.this, view);

        fireFragmentPresenter.attach(this);
        //setup scrolling in remarks
        fireFragmentPresenter.setupRemarks();
//
//        snakeCalls.setOnSeekChangeListener(new OnSeekChangeListener() {
//            @Override
//            public void onSeeking(SeekParams seekParams) {
//                Log.e(TAG,""+ seekParams.progress);
//            }
//
//            @Override
//            public void onStartTrackingTouch(IndicatorSeekBar seekBar) {
//            }
//
//            @Override
//            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {
//            }
//        });


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
        fireFragmentPresenter.detach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.reset)
    public void onViewClicked() {
        totalFireCalls.setText("");
        incidental.setText("");
        nonIncidental.setText("");
        snakeCalls.setProgress(0f);
        remarks.setText("");
    }
}
