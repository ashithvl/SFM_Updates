package com.blueangels.psfm.ui.fragments.medical;

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
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blueangels.psfm.R;
import com.blueangels.psfm.base.BaseView;
import com.blueangels.psfm.base.FragmentContext;
import com.blueangels.psfm.ui.fragments.fire.FireFragment;
import com.blueangels.psfm.utils.PreferencesAppHelper;

import java.io.File;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

import static com.blueangels.psfm.ui.fragments.fire.FireFragment.CHOOSER_TYPE;


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
    @BindView(R.id.image)
    ImageView image;

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

        medicalFragmentPresenter.saveListener();

        if (PreferencesAppHelper.getMedicalImage() != null) {
            File imgFile = new File(PreferencesAppHelper.getMedicalImage());
            if (imgFile.exists()) {
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                image.setImageBitmap(myBitmap);
            }
        }

        return view;
    }

    @Override
    public void saveListener() {
        numberOfPatients.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                PreferencesAppHelper.setMedicalNoOfPatients(s.toString());
            }
        });

        occupationalInjury.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                PreferencesAppHelper.setMedicalOccupationalInjury(s.toString());
            }
        });
        routine.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                PreferencesAppHelper.setMedicalRoutine(s.toString());
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
                PreferencesAppHelper.setMedicalRemarks(s.toString());
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
        medicalFragmentPresenter.detach();
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
                EasyImage.openChooserWithGallery(MedicalFragment.this, "Pick an Image", CHOOSER_TYPE);
                break;
            case R.id.reset:
                numberOfPatients.setText("");
                occupationalInjury.setText("");
                remarks.setText("");
                routine.setText("");
                PreferencesAppHelper.setMedicalImage(null);
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
                PreferencesAppHelper.setMedicalImage(String.valueOf(imageFile.getAbsoluteFile()));
                if(imgFile.exists()){
                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    image.setImageBitmap(myBitmap);
                }
            }
        });
    }
}
