package com.blueangels.psfm.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.blueangels.psfm.PsfmApplication;


public class PreferencesAppHelper {

    private static final String PREFS_NAME = "PSFM_APP";
    private static final String DATE = "date";
    private static final String SECURITY_VEHICLE_DISPATCHED = "security_vehicle_dispatched";
    private static final String SECURITY_THEFT = "security_theft";
    private static final String SECURITY_THEFT_COMMENTS = "security_theft_comments";
    private static final String SECURITY_SECURITY_REMARKS = "security_remarks";
    private static final String FIRE_CALLS = "fire_calls";
    private static final String FIRE_INCIDENTAL_COUNT = "fire_incidental";
    private static final String FIRE_NON_INCIDENTAL_COUNT = "fire_non_incidental";
    private static final String FIRE_SNAKE_CALLS = "fire_snake_calls";
    private static final String FIRE_REMARKS = "fire_remarks";
    private static final String MEDICAL_OCCUPATIONAL_INJURY = "medical_occupation_injury";
    private static final String MEDICAL_NO_OF_PATIENTS = "medical_no_of_patients";
    private static final String MEDICAL_REMARKS = "medical_remarks";
    private static final String MEDICAL_ROUTINE = "medical_routine";
    private static final String GATE_VEHICLE_INWARD = "gate_vehicle_inward";
    private static final String GATE_LAYOVER = "gate_layover";
    private static final String GATE_REMARKS = "gate_remarks";
    private static final String PROJECT_UPDATES = "project_updates";
    private static final String PROJECT_COMMENTS = "project_comments";
    private static final String FIRE_IMAGE = "fire_image";
    private static final String SECURITY_IMAGE = "security_image";
    private static final String MEDICAL_IMAGE = "medical_image";
    private static SharedPreferences mSharedPreferences = null;

    public static String getDate() {
        return getSharedPreference().getString(DATE, null);
    }

    public static void setDate(String date) {
        setStringInPrefs(DATE, date);
    }

    public static String getFireImage() {
        return getSharedPreference().getString(FIRE_IMAGE, null);
    }

    public static void setFireImage(String path) {
        setStringInPrefs(FIRE_IMAGE, path);
    }

    public static String getSecurityImage() {
        return getSharedPreference().getString(SECURITY_IMAGE, null);
    }

    public static void setSecurityImage(String path) {
        setStringInPrefs(SECURITY_IMAGE, path);
    }

    public static String getMedicalImage() {
        return getSharedPreference().getString(MEDICAL_IMAGE, null);
    }

    public static void setMedicalImage(String path) {
        setStringInPrefs(MEDICAL_IMAGE, path);
    }

    public static String getSecurityVehicleDispatched() {
        return getSharedPreference().getString(SECURITY_VEHICLE_DISPATCHED, null);
    }

    public static void setSecurityVehicleDispatched(String securityVehicleDispatched) {
        setStringInPrefs(SECURITY_VEHICLE_DISPATCHED, securityVehicleDispatched);
    }

    public static String getSecurityTheft() {
        return getSharedPreference().getString(SECURITY_THEFT, null);
    }

    public static void setSecurityTheft(String securityTheft) {
        setStringInPrefs(SECURITY_THEFT, securityTheft);
    }

    public static String getProjectUpdates() {
        return getSharedPreference().getString(PROJECT_UPDATES, null);
    }

    public static void setProjectUpdates(String projectUpdates) {
        setStringInPrefs(PROJECT_UPDATES, projectUpdates);
    }

    public static String getProjectComments() {
        return getSharedPreference().getString(PROJECT_COMMENTS, null);
    }

    public static void setProjectComments(String projectComments) {
        setStringInPrefs(PROJECT_COMMENTS, projectComments);
    }

    public static String getSecurityTheftComments() {
        return getSharedPreference().getString(SECURITY_THEFT_COMMENTS, null);
    }

    public static void setSecurityTheftComments(String securityTheftComments) {
        setStringInPrefs(SECURITY_THEFT_COMMENTS, securityTheftComments);
    }

    public static String getSecuritySecurityRemarks() {
        return getSharedPreference().getString(SECURITY_SECURITY_REMARKS, null);
    }

    public static void setSecuritySecurityRemarks(String securitySecurityRemarks) {
        setStringInPrefs(SECURITY_SECURITY_REMARKS, securitySecurityRemarks);
    }

    public static String getFireCalls() {
        return getSharedPreference().getString(FIRE_CALLS, null);
    }

    public static void setFireCalls(String fireCalls) {
        setStringInPrefs(FIRE_CALLS, fireCalls);
    }

    public static String getFireIncidentalCount() {
        return getSharedPreference().getString(FIRE_INCIDENTAL_COUNT, null);
    }

    public static void setFireIncidentalCount(String fireIncidentalCount) {
        setStringInPrefs(FIRE_INCIDENTAL_COUNT, fireIncidentalCount);
    }

    public static String getFireNonIncidentalCount() {
        return getSharedPreference().getString(FIRE_NON_INCIDENTAL_COUNT, null);
    }

    public static void setFireNonIncidentalCount(String fireNonIncidentalCount) {
        setStringInPrefs(FIRE_NON_INCIDENTAL_COUNT, fireNonIncidentalCount);
    }

    public static String getFireSnakeCalls() {
        return getSharedPreference().getString(FIRE_SNAKE_CALLS, null);
    }

    public static void setFireSnakeCalls(String fireSnakeCalls) {
        setStringInPrefs(FIRE_SNAKE_CALLS, fireSnakeCalls);
    }

    public static String getFireRemarks() {
        return getSharedPreference().getString(FIRE_REMARKS, null);
    }

    public static void setFireRemarks(String fireRemarks) {
        setStringInPrefs(FIRE_REMARKS, fireRemarks);
    }

    public static String getMedicalRemarks() {
        return getSharedPreference().getString(MEDICAL_REMARKS, null);
    }

    public static void setMedicalRemarks(String medicalRemarks) {
        setStringInPrefs(MEDICAL_REMARKS, medicalRemarks);
    }

    public static String getMedicalOccupationalInjury() {
        return getSharedPreference().getString(MEDICAL_OCCUPATIONAL_INJURY, null);
    }

    public static void setMedicalOccupationalInjury(String medicalOccupationalInjury) {
        setStringInPrefs(MEDICAL_OCCUPATIONAL_INJURY, medicalOccupationalInjury);
    }

    public static String getMedicalNoOfPatients() {
        return getSharedPreference().getString(MEDICAL_NO_OF_PATIENTS, null);
    }

    public static void setMedicalNoOfPatients(String medicalOccupationalInjury) {
        setStringInPrefs(MEDICAL_NO_OF_PATIENTS, medicalOccupationalInjury);
    }

    public static String getMedicalRoutine() {
        return getSharedPreference().getString(MEDICAL_ROUTINE, null);
    }

    public static void setMedicalRoutine(String medicalRoutine) {
        setStringInPrefs(MEDICAL_ROUTINE, medicalRoutine);
    }

    public static String getGateVehicleInward() {
        return getSharedPreference().getString(GATE_VEHICLE_INWARD, null);
    }

    public static void setGateVehicleInward(String gateVehicleInward) {
        setStringInPrefs(GATE_VEHICLE_INWARD, gateVehicleInward);
    }

    public static String getGateLayover() {
        return getSharedPreference().getString(GATE_LAYOVER, null);
    }

    public static void setGateLayover(String gate_layover) {
        setStringInPrefs(GATE_LAYOVER, gate_layover);
    }

    public static String getGateRemarks() {
        return getSharedPreference().getString(GATE_REMARKS, null);
    }

    public static void setGateRemarks(String fireRemarks) {
        setStringInPrefs(GATE_REMARKS, fireRemarks);
    }

    private static void setStringInPrefs(String key, String value) {
        SharedPreferences.Editor editor = getSharedPreference().edit();
        editor.putString(key, value);
        editor.apply();
    }

    private static void setIntInPrefs(String key, int value) {
        SharedPreferences.Editor editor = getSharedPreference().edit();
        editor.putInt(key, value);
        editor.apply();
    }

    private static SharedPreferences getSharedPreference() {
        if (mSharedPreferences == null) {
            mSharedPreferences = PsfmApplication.getAppContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        }
        return mSharedPreferences;
    }

    public static void clearSharedPreference() {
        SharedPreferences.Editor editor = getSharedPreference().edit();
        editor.clear();
        editor.apply();
    }
}
