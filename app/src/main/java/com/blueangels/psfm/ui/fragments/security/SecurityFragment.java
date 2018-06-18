package com.blueangels.psfm.ui.fragments.security;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.blueangels.psfm.R;
import com.blueangels.psfm.base.BaseView;
import com.blueangels.psfm.base.FragmentContext;
import com.blueangels.psfm.ui.fragments.medical.MedicalFragment;
import com.blueangels.psfm.utils.PreferencesAppHelper;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.io.File;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

import static com.blueangels.psfm.ui.fragments.fire.FireFragment.CHOOSER_TYPE;
import static com.blueangels.psfm.utils.Constants.CONSTANTS;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecurityFragment extends Fragment implements SecurityFragmentContract.View, BaseView {

    private static final String TAG = "SecurityFragment";
    @Inject
    SecurityFragmentPresenter securityFragmentPresenter;
    @Inject
    ArrayAdapter<String> stringArrayAdapter;
    @BindView(R.id.total_vehicle_dispatched)
    TextInputEditText totalVehicleDispatched;
    @BindView(R.id.any_theft)
    MaterialBetterSpinner anyTheft;
    @BindView(R.id.comments)
    TextInputEditText comments;
    @BindView(R.id.commentsLayout)
    TextInputLayout commentsLayout;
    @BindView(R.id.remarks)
    TextInputEditText remarks;
    @BindView(R.id.reset)
    AppCompatButton reset;
    private Context context;
    private Unbinder unbinder;
    @BindView(R.id.image)
    ImageView image;

    public SecurityFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;

        DaggerSecurityFragmentComponent.builder()
                .fragmentContext(new FragmentContext(context))
                .build()
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_security, container, false);
        unbinder = ButterKnife.bind(SecurityFragment.this, view);

        securityFragmentPresenter.attach(this);
        //setup spinner
        securityFragmentPresenter.setupSpinnerAdapter();
        //setup scrolling in comments
        securityFragmentPresenter.setupComment();
        //setup scrolling in remarks
        securityFragmentPresenter.setupRemarks();
        //setup theft listener
        securityFragmentPresenter.setupTheftListener();
        //save values to prefs
        securityFragmentPresenter.saveListener();

        if (PreferencesAppHelper.getSecurityImage() != null) {
            File imgFile = new File(PreferencesAppHelper.getSecurityImage());
            if (imgFile.exists()) {
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                image.setImageBitmap(myBitmap);
            }
        }

        return view;
    }

    @Override
    public void theftListener() {

        anyTheft.addTextChangedListener(new TextWatcher() {
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
                PreferencesAppHelper.setSecurityTheft(s.toString());
            }
        });
    }

    @Override
    public void saveListener() {
        totalVehicleDispatched.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                PreferencesAppHelper.setSecurityVehicleDispatched(s.toString());
            }
        });

        comments.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                PreferencesAppHelper.setSecurityTheftComments(s.toString());
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
                PreferencesAppHelper.setSecuritySecurityRemarks(s.toString());
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
    public void populateSpinner() {
        anyTheft.setAdapter(stringArrayAdapter);
    }

    @OnClick({R.id.choose_image, R.id.reset})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.choose_image:
                EasyImage.openChooserWithGallery(SecurityFragment.this, "Pick an Image", CHOOSER_TYPE);
                break;
            case R.id.reset:
                totalVehicleDispatched.setText("");
                anyTheft.setText("");
                comments.setText("");
                remarks.setText("");
                PreferencesAppHelper.setSecurityImage(null);
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
                File imgFile = new  File(String.valueOf(imageFile.getAbsoluteFile()));
                PreferencesAppHelper.setSecurityImage(String.valueOf(imageFile.getAbsoluteFile()));
                if(imgFile.exists()){
                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    image.setImageBitmap(myBitmap);
                }
            }
        });
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        securityFragmentPresenter.detach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
