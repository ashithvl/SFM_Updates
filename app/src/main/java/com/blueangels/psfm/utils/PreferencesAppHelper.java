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
    private static final String MEDICAL_ROUTINE = "medical_routine";
    private static final String GATE_VEHICLE_INWARD = "gate_vehicle_inward";
    private static final String GATE_LAYOVER = "gate_layover";
    private static SharedPreferences mSharedPreferences = null;

    public static String getDate() {
        return getSharedPreference().getString(DATE, null);
    }

    public static void setDate(String date) {
        setStringInPrefs(DATE, date);
    }

    public static int getSecurityVehicleDispatched() {
        return getSharedPreference().getInt(SECURITY_VEHICLE_DISPATCHED, 0);
    }

    public static void setSecurityVehicleDispatched(int securityVehicleDispatched) {
        setIntInPrefs(SECURITY_VEHICLE_DISPATCHED, securityVehicleDispatched);
    }

    public static String getSecurityTheft() {
        return getSharedPreference().getString(SECURITY_THEFT, null);
    }

    public static void setSecurityTheft(String securityTheft) {
        setStringInPrefs(SECURITY_THEFT,securityTheft);
    }

    public static String getSecurityTheftComments() {
        return getSharedPreference().getString(SECURITY_THEFT_COMMENTS, null);
    }

    public static void setSecurityTheftComments(String securityTheftComments) {
        setStringInPrefs(SECURITY_THEFT_COMMENTS,securityTheftComments);
    }

    public static String getSecuritySecurityRemarks() {
        return getSharedPreference().getString(SECURITY_SECURITY_REMARKS, null);
    }

    public static void setSecuritySecurityRemarks(String securitySecurityRemarks) {
        setStringInPrefs(SECURITY_SECURITY_REMARKS,securitySecurityRemarks);
    }

    public static String getFireCalls() {
        return getSharedPreference().getString(FIRE_CALLS, null);
    }

    public static void setFireCalls(String fireCalls) {
        setStringInPrefs(FIRE_CALLS, fireCalls);
    }

    public static int getFireIncidentalCount() {
        return getSharedPreference().getInt(FIRE_INCIDENTAL_COUNT, 0);
    }

    public static void setFireIncidentalCount(int fireIncidentalCount) {
        setIntInPrefs(FIRE_INCIDENTAL_COUNT, fireIncidentalCount);
    }

    public static int getFireNonIncidentalCount() {
        return getSharedPreference().getInt(FIRE_NON_INCIDENTAL_COUNT, 0);
    }

    public static void setFireNonIncidentalCount(int fireNonIncidentalCount) {
        setIntInPrefs(FIRE_NON_INCIDENTAL_COUNT, fireNonIncidentalCount);
    }

    public static int getFireSnakeCalls() {
        return getSharedPreference().getInt(FIRE_SNAKE_CALLS, 0);
    }

    public static void setFireSnakeCalls(int fireSnakeCalls) {
        setIntInPrefs(FIRE_SNAKE_CALLS, fireSnakeCalls);
    }

    public static String getFireRemarks() {
        return getSharedPreference().getString(FIRE_REMARKS, null);
    }

    public static void setFireRemarks(String fireRemarks) {
        setStringInPrefs(FIRE_REMARKS, fireRemarks);
    }

    public static int getMedicalOccupationalInjury() {
        return getSharedPreference().getInt(MEDICAL_OCCUPATIONAL_INJURY, 0);
    }

    public static void setMedicalOccupationalInjury(String medicalOccupationalInjury) {
        setStringInPrefs(MEDICAL_OCCUPATIONAL_INJURY, medicalOccupationalInjury);
    }

    public static int getMedicalRoutine() {
        return getSharedPreference().getInt(MEDICAL_ROUTINE, 0);
    }

    public static void setMedicalRoutine(String medicalRoutine) {
        setStringInPrefs(MEDICAL_ROUTINE, medicalRoutine);
    }

    public static int getGateVehicleInward() {
        return getSharedPreference().getInt(GATE_VEHICLE_INWARD, 0);
    }

    public static void setGateVehicleInward(String gateVehicleInward) {
        setStringInPrefs(GATE_VEHICLE_INWARD, gateVehicleInward);
    }

    public static int getGateLayover() {
        return getSharedPreference().getInt(GATE_LAYOVER, 0);
    }

    public static void setGateLayover(String gate_layover) {
        setStringInPrefs(GATE_LAYOVER, gate_layover);
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
}
