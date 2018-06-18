package com.blueangels.psfm.ui.activities.save;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blueangels.psfm.BuildConfig;
import com.blueangels.psfm.R;
import com.blueangels.psfm.base.ActivityContext;
import com.blueangels.psfm.utils.PreferencesAppHelper;
import com.snatik.storage.Storage;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.blueangels.psfm.utils.PreferencesAppHelper.clearSharedPreference;

public class SaveActivity extends AppCompatActivity implements SaveContract.View {
    private static final String TAG = "SaveActivity";
    private static final int WRITE_EXTERNAL_STORAGE = 111;
    @Inject
    SavePresenter savePresenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.save_to_image)
    LinearLayout saveToImage;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.total_vehicle_dispatched_br)
    TextView totalVehicleDispatchedBr;
    @BindView(R.id.any_theft)
    TextView anyTheft;
    @BindView(R.id.theft_comments)
    TextView theftComments;
    @BindView(R.id.security_remarks)
    TextView securityRemarks;
    @BindView(R.id.incidental)
    TextView incidental;
    @BindView(R.id.non_incidental)
    TextView nonIncidental;
    @BindView(R.id.snake_calls)
    TextView snakeCalls;
    @BindView(R.id.fire_remarks)
    TextView fireRemarks;
    @BindView(R.id.number_of_patients)
    TextView numberOfPatients;
    @BindView(R.id.occupational_injury)
    TextView occupationalInjury;
    @BindView(R.id.routine)
    TextView routine;
    @BindView(R.id.medical_remarks)
    TextView medicalRemarks;
    @BindView(R.id.total_vehicle_inward_br)
    TextView totalVehicleInwardBr;
    @BindView(R.id.layover)
    TextView layover;
    @BindView(R.id.gate_remarks)
    TextView gateRemarks;
    @BindView(R.id.any_updates)
    TextView anyUpdates;
    @BindView(R.id.project_comments)
    TextView projectComments;
    @BindView(R.id.total_fire_calls)
    TextView totalFireCalls;
    @BindView(R.id.securityImage)
    ImageView securityImage;
    @BindView(R.id.fireImage)
    ImageView fireImage;
    @BindView(R.id.medicalImage)
    ImageView medicalImage;
    private Context mContext = SaveActivity.this;
    private Storage storage;
    private String newDir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle("SFM Updates");

        DaggerSaveComponent.builder()
                .activityContext(new ActivityContext(mContext))
                .saveModule(new SaveModule(this))
                .build()
                .inject(this);

        savePresenter.attach(this);
        savePresenter.getText();

        //init
        storage = new Storage(getApplicationContext());
        // get external storage
        String path = storage.getExternalStorageDirectory();

        // new dir
        newDir = path + File.separator + getString(R.string.app_name);
        storage.createDirectory(newDir);

        boolean hasPermission = (ContextCompat.checkSelfPermission(getBaseContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) &&
                (ContextCompat.checkSelfPermission(getBaseContext(),
                        Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);

        if (!hasPermission) {
            ActivityCompat.requestPermissions(SaveActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }, WRITE_EXTERNAL_STORAGE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        savePresenter.detach();
    }

    @Override
    public void setText() {
        if (PreferencesAppHelper.getDate() != null)
            date.setText(PreferencesAppHelper.getDate());
        else {
            Date c = Calendar.getInstance().getTime();
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            date.setText(df.format(c));
            Log.e(TAG, "df.format(c) " + df.format(c));
        }

        if (PreferencesAppHelper.getSecurityVehicleDispatched() != null)
            totalVehicleDispatchedBr.setText(String.valueOf(PreferencesAppHelper.getSecurityVehicleDispatched()));
        else
            totalVehicleDispatchedBr.setText("0");

        if (PreferencesAppHelper.getSecurityTheft() != null)
            anyTheft.setText(PreferencesAppHelper.getSecurityTheft());
        else
            anyTheft.setText("No");

        if (PreferencesAppHelper.getSecurityTheftComments() != null)
            theftComments.setText(PreferencesAppHelper.getSecurityTheftComments());
        else
            theftComments.setText("");

        if (PreferencesAppHelper.getSecuritySecurityRemarks() != null)
            securityRemarks.setText(PreferencesAppHelper.getSecuritySecurityRemarks());
        else
            securityRemarks.setText("");

        if (PreferencesAppHelper.getFireCalls() != null)
            totalFireCalls.setText(PreferencesAppHelper.getFireCalls());
        else
            totalFireCalls.setText("0");

        if (PreferencesAppHelper.getFireIncidentalCount() != null)
            incidental.setText(String.valueOf(PreferencesAppHelper.getFireIncidentalCount()));
        else
            incidental.setText("0");

        if (PreferencesAppHelper.getFireNonIncidentalCount() != null)
            nonIncidental.setText(String.valueOf(PreferencesAppHelper.getFireNonIncidentalCount()));
        else
            nonIncidental.setText("0");

        if (PreferencesAppHelper.getFireSnakeCalls() != null)
            snakeCalls.setText(String.valueOf(PreferencesAppHelper.getFireSnakeCalls()));
        else
            snakeCalls.setText("0");

        if (PreferencesAppHelper.getFireRemarks() != null)
            fireRemarks.setText(PreferencesAppHelper.getFireRemarks());
        else
            fireRemarks.setText("");

        if (PreferencesAppHelper.getMedicalNoOfPatients() != null)
            numberOfPatients.setText(PreferencesAppHelper.getMedicalNoOfPatients());
        else
            numberOfPatients.setText("0");

        if (PreferencesAppHelper.getMedicalOccupationalInjury() != null)
            occupationalInjury.setText(String.valueOf(PreferencesAppHelper.getMedicalOccupationalInjury()));
        else
            occupationalInjury.setText("0");

        if (PreferencesAppHelper.getMedicalRoutine() != null)
            routine.setText(String.valueOf(PreferencesAppHelper.getMedicalRoutine()));
        else
            routine.setText("0");

        if (PreferencesAppHelper.getMedicalRemarks() != null)
            medicalRemarks.setText(PreferencesAppHelper.getMedicalRemarks());
        else
            medicalRemarks.setText("");

        if (PreferencesAppHelper.getGateVehicleInward() != null)
            totalVehicleInwardBr.setText(String.valueOf(PreferencesAppHelper.getGateVehicleInward()));
        else
            totalVehicleInwardBr.setText("0");

        if (PreferencesAppHelper.getGateLayover() != null)
            layover.setText(String.valueOf(PreferencesAppHelper.getGateLayover()));
        else
            layover.setText("0");

        if (PreferencesAppHelper.getGateRemarks() != null)
            gateRemarks.setText(String.valueOf(PreferencesAppHelper.getGateRemarks()));
        else
            gateRemarks.setText("");

        if (PreferencesAppHelper.getProjectUpdates() != null)
            anyUpdates.setText(PreferencesAppHelper.getProjectUpdates());
        else
            anyUpdates.setText("No");

        if (PreferencesAppHelper.getProjectComments() != null)
            projectComments.setText(PreferencesAppHelper.getProjectComments());
        else
            projectComments.setText("");


        if (PreferencesAppHelper.getSecurityImage() != null) {
            File imgFile = new File(PreferencesAppHelper.getSecurityImage());
            if (imgFile.exists()) {
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                securityImage.setImageBitmap(myBitmap);
                securityImage.setVisibility(View.VISIBLE);
            }
        } else
            securityImage.setVisibility(View.GONE);

        if (PreferencesAppHelper.getFireImage() != null) {
            File imgFile = new File(PreferencesAppHelper.getFireImage());
            if (imgFile.exists()) {
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                fireImage.setImageBitmap(myBitmap);
                fireImage.setVisibility(View.VISIBLE);
            }
        } else
            fireImage.setVisibility(View.GONE);

        if (PreferencesAppHelper.getMedicalImage() != null) {
            File imgFile = new File(PreferencesAppHelper.getMedicalImage());
            if (imgFile.exists()) {
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                medicalImage.setImageBitmap(myBitmap);
                medicalImage.setVisibility(View.VISIBLE);
            }
        } else
            medicalImage.setVisibility(View.GONE);

    }

    @OnClick(R.id.save)
    public void onViewClicked() {
        boolean success = false;
        String path = null;
        try {
            String fileName = new SimpleDateFormat("yyyyMMddHHmm'.jpeg'").format(new Date());
            path = newDir + File.separator + "sfm" + fileName;
            FileOutputStream out = new FileOutputStream(path);
            loadBitmapFromView(saveToImage).compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
            success = true;
        } catch (Exception error) {
            Log.e("Error saving image", error.getMessage());
        }
        if (success) {
            Toast.makeText(this, "Image saved to " + path,
                    Toast.LENGTH_LONG).show();
            clearSharedPreference();
            Intent intent = new Intent(Intent.ACTION_SEND);
            Uri uri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID, new File(path));
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setType("image/*");
            startActivity(Intent.createChooser(intent, "Share..."));
        } else
            Toast.makeText(this, "Image couldn't be saved to " + path, Toast.LENGTH_LONG).show();
    }

    public Bitmap loadBitmapFromView(View view) {

        // width measure spec
        int widthSpec = View.MeasureSpec.makeMeasureSpec(
                view.getMeasuredWidth(), View.MeasureSpec.AT_MOST);
        // height measure spec
        int heightSpec = View.MeasureSpec.makeMeasureSpec(
                view.getMeasuredHeight(), View.MeasureSpec.AT_MOST);
        // measure the view
        view.measure(widthSpec, heightSpec);
        // set the layout sizes
        view.layout(view.getLeft(), view.getTop(), view.getMeasuredWidth() + view.getLeft(),
                view.getMeasuredHeight() + view.getTop());
        // create the bitmap
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        // create a canvas used to get the view's image and draw it on the bitmap
        Canvas c = new Canvas(bitmap);
        // position the image inside the canvas
        c.translate(-view.getScrollX(), -view.getScrollY());
        // get the canvas
        view.draw(c);

        return bitmap;
    }
}
