package com.tbc.uncagedmist.cricketupdates.Tools;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.tbc.uncagedmist.cricketupdates.MyApplication;


public class AppSocialTools {

    private static AppSocialTools appSocialTools;

    public static AppSocialTools getInstance() {

        if (appSocialTools != null)
            return appSocialTools;
        else
            appSocialTools = new AppSocialTools();

        return appSocialTools;
    }

    public static boolean isConnected() {
        NetworkInfo activeNetworkInfo = null;

        ConnectivityManager connectivity_manager = (ConnectivityManager) MyApplication.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity_manager != null) {
            activeNetworkInfo = connectivity_manager.getActiveNetworkInfo();
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public String getOdrkd() {
        return AppAdOrganizer.getInstance().getEncyption(AppAdOrganizer.getInstance().getMnkdr(), AppTimeHandler.getInstance().getOlfdg());
    }

    public String getYdkf() {
        return MyApplication.getInstance().getPackageName();
    }


}
