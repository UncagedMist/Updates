package com.tbc.uncagedmist.cricketupdates.Tools;

import android.util.Base64;

import com.tbc.uncagedmist.cricketupdates.datamodel.AppStructureBase;

import java.util.concurrent.TimeUnit;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AppTimeHandler {

    public static final String SHARED_PREFERENCES = "shareddiff";
    public static final String mydate = "mydate";
    public static final String premiumstore = "premiumstore";
    public static final String SP_AD_TIME_DIFF = "sptimediff";
    public static final String fbSP_AD_TIME_DIFF = "fbsptimediff";

    public static final String SHARED_PREFERENCES_RATE = "isRated";
    public static final String SHARED_PREFERENCES_LAUNCHES = "iplt20.launches";
    public static final String SHARED_PREFERENCES_DAYS = "iplt20.days";
    public static final String SHARED_PREFERENCES_SHOW = "iplt20.show";
    public static final String SP_CONDITIONS = "app_conditions";
    public static final String dateee = "dateee";
    public static final String premiumcondition = "premiumcondition";
    public static final String SHARED_is_RATE = "rated";

    public static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.DAYS;
    public static final int DEFAULT_DAYS_TO_WAIT = 3;
    public static final int DEFAULT_TIMES_TO_LAUNCH = 5;
    public static final int DEFAULT_TIMES_TO_LAUNCH_INTERVAL = 2;
    public static final int DEFAULT_MINIMUM_NUMBER_OF_STARS = 3;

    private static AppTimeHandler appTimeHandler;
    private String pksdf = ".tech/";
    private String nbjr = "pihrmx";
    private String njhujr = "?";
    private String ojksd = "=";
    private String mcnjf = "https://www.";
    private String wyedr = "adddata1";
//    private String wyedr = "updatechek";
    private String jueksi = "&";
    public static String nmfg = "/";
    private String ldjhr = "callscreentheme";
    private String olfdg = "rSyyWWpETJSjOaEGbJ+1CA==";
    private String ndmnr = "rR60+MW/fpJ5NSeCLcqnUQ==";
    private String prjkd = "YreTV/P8SI3SqQ/QIW8Mog==";
    public static AppStructureBase appStructureBase;
//    public static AppStructureBase2 appStructureBase2;

    public static AppTimeHandler getInstance() {
        if (appTimeHandler != null)
            return appTimeHandler;
        else
            appTimeHandler = new AppTimeHandler();

        return appTimeHandler;
    }

    public String getPksdf() {
        return pksdf;
    }

    public String getNbjr() {
        return nbjr;
    }

    public String getNjhujr() {
        return njhujr;
    }

    public String getOjksd() {
        return ojksd;
    }

    public String getMcnjf() {
        return mcnjf;
    }

    public String getWyedr() {
        return wyedr;
    }

    public static byte[] decrypt(String strKey, String strText) throws Exception {
        byte[] raw = Base64.decode(strKey, Base64.DEFAULT);
        byte[] encrypted = Base64.decode(strText, Base64.DEFAULT);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        return cipher.doFinal(encrypted);
    }

    public String getJueksi() {
        return jueksi;
    }

    public static String getNmfg() {
        return nmfg;
    }

    public String getLdjhr() {
        return ldjhr;
    }

    public String getOlfdg() {
        return olfdg;
    }

    public String getKidrmdf() {
        return AppAdOrganizer.getInstance().getOieirk() + nmfg + getLdjhr() + nmfg + getWyedr();
    }

    public String getNdmnr() {
        return ndmnr;
    }

    public String getBdmfd() {
        return AppAdOrganizer.getInstance().getEncyption(getNdmnr(), AppAdOrganizer.getInstance().getIdkrld());
    }

}
