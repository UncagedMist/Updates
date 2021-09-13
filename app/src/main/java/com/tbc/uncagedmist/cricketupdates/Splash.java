package com.tbc.uncagedmist.cricketupdates;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;

import com.tbc.uncagedmist.cricketupdates.R;

import com.tbc.uncagedmist.cricketupdates.Tools.AppAdOrganizer;
import com.tbc.uncagedmist.cricketupdates.datamodel.AppStructureBase;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AudienceNetworkAds;
import static com.tbc.uncagedmist.cricketupdates.Tools.AppTimeHandler.appStructureBase;

import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.Task;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        checkAppUpdate();

        setContentView(R.layout.activity_splash);

        appStructureBase = new AppStructureBase();
        AppAdOrganizer.getInstance().initAdMobAd(this);
        AudienceNetworkAds.initialize(this);

        AdSettings.addTestDevice("259BAD3F531CF132414B99B9D86CAF18");

        appStructureBase.setInter_id("ca-app-pub-7920815986886474/8029944040");
        appStructureBase.setNative_id("ca-app-pub-7920815986886474/9908688845");
        appStructureBase.setBanner_id("ca-app-pub-7920815986886474/7336982938");

        appStructureBase.setFb_inter_id("405472820995372_405473640995290");
        appStructureBase.setFb_native_id("405472820995372_405473824328605");
        appStructureBase.setFb_banner_id("405472820995372_405473084328679");

        appStructureBase.setFb_native_home(1);
        appStructureBase.setGoogle_native_home(1);

        appStructureBase.setFb_native_liveline(1);
        appStructureBase.setGoogle_native_liveline(1);

        appStructureBase.setFb_native_series_option(1);
        appStructureBase.setGoogle_native_series_option(1);

        appStructureBase.setFb_banner_series_detail(1);
        appStructureBase.setGoogle_banner_series_detail(1);

        appStructureBase.setFb_inter_option1(1);
        appStructureBase.setGoogle_inter_option1(1);

        appStructureBase.setFb_inter_option2(1);
        appStructureBase.setGoogle_inter_option2(1);

        appStructureBase.setFb_inter_option3(1);
        appStructureBase.setGoogle_inter_option3(1);

        Splash.this.startActivity(new Intent(Splash.this.getApplicationContext(), MainActivity.class));
        Splash.this.finish();

    }

    private void checkAppUpdate() {
        final AppUpdateManager appUpdateManager = AppUpdateManagerFactory.create(Splash.this);
        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();

        appUpdateInfoTask.addOnSuccessListener(result -> {

            if (result.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE &&
                    result.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {

                try {
                    appUpdateManager.startUpdateFlowForResult(
                            result, AppUpdateType.IMMEDIATE,
                            Splash.this,
                            51
                    );
                } catch (IntentSender.SendIntentException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}