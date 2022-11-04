package com.example.ecobattery;

import android.annotation.SuppressLint;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saver_activity);

        optFileConfig = new OptimizationFileConfig(getApplicationContext());

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
                                optFileConfig.processPackage(optAppDisplayArray[finalX][finalY].getPackageName());
                            }
                        };
                        appImageView.setOnClickListener(clickListener);

                        TextView appTextView = new TextView(v.getContext());

                        appTextView.setText(optAppDisplayArray[x][y].getName());

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
                                optFileConfig.processPackage(allAppDisplayArray[finalX][finalY].getPackageName());
                            }
                        };
                        appImageView.setOnClickListener(clickListener);

                        TextView appTextView = new TextView(v.getContext());

                        appTextView.setText(allAppDisplayArray[x][y].getName());

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
                pagerAdapter.addView (v, x + optAppDisplayArray.length);
                pagerAdapter.notifyDataSetChanged();
            }
        }

        /*

        for (int x = 0; x < applicationArray.length; x++) {
            int currentX = 0;
            int currentY = 0;

            RelativeLayout v = (RelativeLayout) inflater.inflate (R.layout.view_apps, null);
            for (int y = 0; y < applicationArray[x].length; y++) {
                if (applicationArray[x][y] != null) {
                    ImageView appImageView = new ImageView(v.getContext());

                    appImageView.setImageDrawable(applicationArray[x][y].getIcon());
                    appImageView.setContentDescription(applicationArray[x][y].getPackageName());

                    appImageView.setX(currentX);
                    appImageView.setY(currentY);

                    int finalX = x;
                    int finalY = y;
                    View.OnClickListener clickListener = new View.OnClickListener() {
                        public void onClick(View v) {
                            System.out.println(applicationArray[finalX][finalY].getPackageName());
                            optimizationFileConfig.processPackage(applicationArray[finalX][finalY].getPackageName());
                        }
                    };
                    appImageView.setOnClickListener(clickListener);

                    TextView appTextView = new TextView(v.getContext());

                    appTextView.setText(applicationArray[x][y].getName());

                    appTextView.setX(currentX);
                    appTextView.setY(currentY + (heightApp / 2));

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
            pagerAdapter.addView (v, x);
            pagerAdapter.notifyDataSetChanged();
        }
         */
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
            if (optFileConfig.doesPackageExist(allAppInfo.get(i).packageName)) {
                optAppInfo.add(allAppInfo.get(i));
                allAppInfo.remove(allAppInfo.get(i));
                i--;
                allAppInfoSize--;
            }
        }

        int allAppDisplayArraySize = (int) Math.ceil(((double) allAppInfo.size() - 1) / 25) + 1;
        allAppDisplayArray = new DisplayApp[allAppDisplayArraySize][25];

        int x = 0;
        for (int i = 0; i < allAppInfo.size(); i++) {
            allAppDisplayArray[x][i % 25] = new DisplayApp(allAppInfo.get(i).packageName,
                    false,
                    pm.getApplicationIcon(allAppInfo.get(i)),
                    pm.getApplicationLabel(allAppInfo.get(i)).toString());
            if (i != 0 && i % 25 == 0) {
                x += 1;
            }
        }

        int optAppDisplayArraySize = (int) Math.ceil(((double) optAppInfo.size() - 1) / 25) + 1;
        optAppDisplayArray = new DisplayApp[optAppDisplayArraySize][25];

        x = 0;
        for (int i = 0; i < optAppInfo.size(); i++) {
            optAppDisplayArray[x][i % 25] = new DisplayApp(optAppInfo.get(i).packageName,
                    false,
                    pm.getApplicationIcon(optAppInfo.get(i)),
                    pm.getApplicationLabel(optAppInfo.get(i)).toString());
            if (i != 0 && i % 25 == 0) {
                x += 1;
            }
        }
    }

    public void addView (View newPage) {
        int pageIndex = pagerAdapter.addView (newPage);
        pager.setCurrentItem (pageIndex, true);
    }

    public void removeView (View defunctPage) {
        int pageIndex = pagerAdapter.removeView (pager, defunctPage);
        if (pageIndex == pagerAdapter.getCount())
            pageIndex--;
        pager.setCurrentItem (pageIndex);
    }

    public View getCurrentPage () {
        return pagerAdapter.getView (pager.getCurrentItem());
    }

    public void setCurrentPage (View pageToShow) {
        pager.setCurrentItem (pagerAdapter.getItemPosition (pageToShow), true);
    }
}