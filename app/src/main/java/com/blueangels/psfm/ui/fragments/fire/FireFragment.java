package com.blueangels.psfm.ui.fragments.fire;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blueangels.psfm.R;
import com.blueangels.psfm.base.BaseView;
import com.blueangels.psfm.base.FragmentContext;
import com.blueangels.psfm.utils.PreferencesAppHelper;
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.OnSeekChangeListener;
import com.warkiz.widget.SeekParams;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

/**
 * A simple {@link Fragment} subclass.
 */
public class FireFragment extends Fragment implements FireFragmentContract.View, BaseView {

    public static final int CHOOSER_TYPE = 121;
    private static final String TAG = "FireFragment";
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
    @BindView(R.id.image)
    ImageView image;
    private Context context;
    private Unbinder unbinder;

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
        fireFragmentPresenter.saveListener();

        if (PreferencesAppHelper.getFireImage() != null) {
            File imgFile = new File(PreferencesAppHelper.getFireImage());
            if (imgFile.exists()) {
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                image.setImageBitmap(myBitmap);
            }
        }

        return view;
    }

    @Override
    public void saveListener() {
        totalFireCalls.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                PreferencesAppHelper.setFireCalls(s.toString());
            }
        });
        incidental.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                PreferencesAppHelper.setFireIncidentalCount(s.toString());
            }
        });
        nonIncidental.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                PreferencesAppHelper.setFireNonIncidentalCount(s.toString());
            }
        });

        snakeCalls.setOnSeekChangeListener(new OnSeekChangeListener() {
            @Override
            public void onSeeking(SeekParams seekParams) {
                Log.e(TAG, "" + seekParams.progress);
                PreferencesAppHelper.setFireSnakeCalls(String.valueOf(seekParams.progress));
            }

            @Override
            public void onStartTrackingTouch(IndicatorSeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {
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
                PreferencesAppHelper.setFireRemarks(s.toString());
            }
        });
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

    @OnClick({R.id.choose_image, R.id.reset})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.choose_image:
                EasyImage.openChooserWithGallery(FireFragment.this, "Pick an Image", CHOOSER_TYPE);
                break;
            case R.id.reset:
                totalFireCalls.setText("");
                incidental.setText("");
                nonIncidental.setText("");
                snakeCalls.setProgress(0f);
                remarks.setText("");
                PreferencesAppHelper.setFireImage(null);
                image.setImageBitmap(null);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EasyImage.handleActivityResult(requestCode, resultCode, data, getActivity(), new DefaultCallback() {
            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                File imgFile = new File(String.valueOf(imageFile.getAbsoluteFile()));
                PreferencesAppHelper.setFireImage(String.valueOf(imageFile.getAbsoluteFile()));
                if (imgFile.exists()) {
                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    image.setImageBitmap(myBitmap);
                }
            }
        });
    }
}
