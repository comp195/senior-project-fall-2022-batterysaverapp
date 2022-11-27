package com.example.ecobattery;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.ecobattery.library.DisplayApp;
import com.example.ecobattery.library.OptimizationFileConfig;
import com.example.ecobattery.library.SaverPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SaverActivity extends AppCompatActivity {

    private OptimizationFileConfig optFileConfig;

    private ViewPager pager = null;
    private SaverPagerAdapter pagerAdapter = null;

    public DisplayApp[][] optAppDisplayArray;
    public DisplayApp[][] allAppDisplayArray;

    private String[] packagesFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        int currentPage = intent.getIntExtra("current_page", 0);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.saver_activity);

        optFileConfig = new OptimizationFileConfig(getApplicationContext());

        initializePackagesFilter();
        initializeApplicationsArray();

        setContentView(R.layout.saver_activity);

        pagerAdapter = new SaverPagerAdapter();
        pager = findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);

        LayoutInflater inflater = getLayoutInflater();

        int widthOfContext = 1080;
        int heightOfContext = 1920;

        int widthApp = widthOfContext / 5;
        int heightApp = heightOfContext / 5;

        if (optAppDisplayArray.length > 0) {
            for (int x = 0; x < optAppDisplayArray.length; x++) {
                int currentX = 0;
                int currentY = 0;

                RelativeLayout v = (RelativeLayout) inflater.inflate (R.layout.view_apps, null);
                for (int y = 0; y < optAppDisplayArray[x].length; y++) {
                    if (optAppDisplayArray[x][y] != null) {
                        ImageView appImageView = new ImageView(v.getContext());

                        appImageView.setImageDrawable(optAppDisplayArray[x][y].getIcon());
                        appImageView.setContentDescription(optAppDisplayArray[x][y].getPackageName());

                        appImageView.setX(currentX);
                        appImageView.setY(currentY);

                        int finalX = x;
                        int finalY = y;
                        View.OnClickListener clickListener = new View.OnClickListener() {
                            public void onClick(View v) {
                                System.out.println(optAppDisplayArray[finalX][finalY].getPackageName());
                                optFileConfig.processPackage(optAppDisplayArray[finalX][finalY].getPackageName());
                                finish();
                                overridePendingTransition(0, 0);
                                Intent newIntent = getIntent();
                                newIntent.putExtra("current_page", finalX);
                                startActivity(getIntent());
                                pager.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        pager.setCurrentItem(finalX);
                                    }
                                });//TODO fix bound cases
                                overridePendingTransition(0, 0);
                            }
                        };
                        appImageView.setOnClickListener(clickListener);

                        TextView appTextView = new TextView(v.getContext());

                        appTextView.setTextColor(Color.WHITE);
                        appTextView.setAllCaps(true);
                        appTextView.setText(optAppDisplayArray[x][y].getName().replace(' ', '\n'));
                        appTextView.setWidth(widthApp);
                        appTextView.setX(currentX);
                        appTextView.setY(currentY + ((float) heightApp / 2));

                        currentX += widthApp;

                        if (currentX == 1080) {
                            currentY += heightApp;
                            currentX = 0;
                        }

                        v.addView(appImageView);
                        v.addView(appTextView);

                        appImageView.requestLayout();
                        appImageView.getLayoutParams().height = 200;
                        appImageView.getLayoutParams().width = 200;
                    }
                }

                TextView pageTextView = new TextView(v.getContext());

                pageTextView.setTextColor(Color.WHITE);
                pageTextView.setAllCaps(true);
                pageTextView.setText("Optimized Apps");

                pageTextView.setX(0);
                pageTextView.setY(heightOfContext - 50);

                v.addView(pageTextView);

                pagerAdapter.addView (v, x);
                pagerAdapter.notifyDataSetChanged();
            }
        }
        if (allAppDisplayArray.length > 0) {
            for (int x = 0; x < allAppDisplayArray.length; x++) {
                int currentX = 0;
                int currentY = 0;

                RelativeLayout v = (RelativeLayout) inflater.inflate (R.layout.view_apps, null);
                for (int y = 0; y < allAppDisplayArray[x].length; y++) {
                    if (allAppDisplayArray[x][y] != null) {
                        ImageView appImageView = new ImageView(v.getContext());

                        appImageView.setImageDrawable(allAppDisplayArray[x][y].getIcon());
                        appImageView.setContentDescription(allAppDisplayArray[x][y].getPackageName());

                        appImageView.setX(currentX);
                        appImageView.setY(currentY);

                        int finalX = x;
                        int finalY = y;
                        View.OnClickListener clickListener = new View.OnClickListener() {
                            public void onClick(View v) {
                                System.out.println(allAppDisplayArray[finalX][finalY].getPackageName());
                                optFileConfig.processPackage(allAppDisplayArray[finalX][finalY].getPackageName());
                                finish();
                                overridePendingTransition(0, 0);
                                Intent newIntent = getIntent();
                                newIntent.putExtra("current_page", finalX + optAppDisplayArray.length);
                                startActivity(getIntent());
                                overridePendingTransition(0, 0);
                            }
                        };
                        appImageView.setOnClickListener(clickListener);

                        TextView appTextView = new TextView(v.getContext());

                        appTextView.setTextColor(Color.WHITE);
                        appTextView.setAllCaps(true);
                        appTextView.setText(allAppDisplayArray[x][y].getName().replace(' ', '\n'));
                        appTextView.setWidth(widthApp);
                        appTextView.setX(currentX);
                        appTextView.setY(currentY + ((float) heightApp / 2));

                        currentX += widthApp;

                        if (currentX == 1080) {
                            currentY += heightApp;
                            currentX = 0;
                        }

                        v.addView(appImageView);
                        v.addView(appTextView);

                        appImageView.requestLayout();
                        appImageView.getLayoutParams().height = 200;
                        appImageView.getLayoutParams().width = 200;
                    }
                }

                TextView pageTextView = new TextView(v.getContext());

                pageTextView.setTextColor(Color.WHITE);
                pageTextView.setAllCaps(true);
                pageTextView.setText("Non-Optimized Apps");

                pageTextView.setX(0);
                pageTextView.setY(heightOfContext - 50);

                v.addView(pageTextView);

                pagerAdapter.addView (v, x + optAppDisplayArray.length);
                pagerAdapter.notifyDataSetChanged();
            }
        }

        pager.setCurrentItem(currentPage);
    }

    public void initializePackagesFilter() {
        packagesFilter = new String[]{
                "com.google.android.cellbroadcastservice",
                "com.android.keychain",
                "com.android.dynsystem",
                "com.android.providers.media",
                "com.android.wallpapercropper",
                "com.android.externalstorage",
                "com.android.companiondevicemanager",
                "com.android.mms.service",
                "com.google.android.providers.media.module",
                "com.android.systemui.plugin.globalactions.wallet",
                "com.android.pacprocessor",
                "com.google.android.adservices.api",
                "com.android.certinstaller",
                "com.android.carrierconfig",
                "com.android.mtp",
                "com.android.ons",
                "com.android.backupconfirm",
                "com.android.emulator.radio.config",
                "com.android.sharedstoragebackup",
                "com.android.se",
                "com.android.inputdevices",
                "com.android.internal.display.cutout.emulation.emu01",
                "com.android.cellbroadcastreceiver",
                "com.google.android.bluetooth.services",
                "com.google.android.networkstack",
                "com.google.android.gsf",
                "com.android.calllogbackup",
                "com.android.cameraextensions",
                "com.android.localtransport",
                "com.android.proxyhandler",
                "com.android.managedprovisioning",
                "com.android.soundpicker",
                "com.google.android.systemui.gxoverlay",
                "com.android.emulator.multidisplay",
                "com.google.android.sdksetup",
                "com.google.android.gms.supervision",
                "com.android.vpndialogs",
                "com.android.shell",
                "com.android.wallpaperbackup",
                "com.android.providers.blockednumber",
                "com.android.providers.userdictionary",
                "com.android.location.fused",
                "com.google.android.ondevicepersonalization.services",
                "com.android.providers.contacts",
                "com.google.android.ext.services",
                "com.google.android.networkstack.tethering",
                "com.example.ecobattery"
        };
    }
    
    public boolean isPackageFiltered(String packageToCheck) {
        for (String packages : packagesFilter) {
            if (packages.equalsIgnoreCase(packageToCheck)) {
                return true;
            }
        }
        return false;
    }

    public void initializeApplicationsArray() {
        final PackageManager pm = getPackageManager();

        @SuppressLint("QueryPermissionsNeeded")
        List<ApplicationInfo> allAppInfo =
                pm.getInstalledApplications(PackageManager.GET_META_DATA);
        List<ApplicationInfo> optAppInfo =
                new ArrayList<ApplicationInfo>();

        int allAppInfoSize = allAppInfo.size();
        for (int i = 0; i < allAppInfoSize; i++) {
            if (isPackageFiltered(allAppInfo.get(i).packageName)) {
                allAppInfo.remove(allAppInfo.get(i));
                i--;
                allAppInfoSize--;
            } else if (optFileConfig.doesPackageExist(allAppInfo.get(i).packageName)) {
                optAppInfo.add(allAppInfo.get(i));
                allAppInfo.remove(allAppInfo.get(i));
                i--;
                allAppInfoSize--;
            }
        }

        int allAppDisplayArraySize = (int) Math.ceil(((double) allAppInfo.size()) / 25);
        allAppDisplayArray = new DisplayApp[allAppDisplayArraySize][25];

        if (allAppDisplayArraySize > 0) {
            int x = 0;
            for (int i = 0; i < allAppInfo.size(); i++) {
                allAppDisplayArray[x][i % 25] = new DisplayApp(allAppInfo.get(i).packageName,
                        pm.getApplicationIcon(allAppInfo.get(i)),
                        pm.getApplicationLabel(allAppInfo.get(i)).toString());
                if (i != 0 && i % 25 == 0) {
                    x += 1;
                }
            }
        }

        int optAppDisplayArraySize = (int) Math.ceil(((double) optAppInfo.size()) / 25);
        optAppDisplayArray = new DisplayApp[optAppDisplayArraySize][25];

        if (optAppDisplayArraySize > 0) {
            int x = 0;
            for (int i = 0; i < optAppInfo.size(); i++) {
                optAppDisplayArray[x][i % 25] = new DisplayApp(optAppInfo.get(i).packageName,
                        pm.getApplicationIcon(optAppInfo.get(i)),
                        pm.getApplicationLabel(optAppInfo.get(i)).toString());
                if (i != 0 && i % 25 == 0) {
                    x += 1;
                }
            }
        }
    }

}