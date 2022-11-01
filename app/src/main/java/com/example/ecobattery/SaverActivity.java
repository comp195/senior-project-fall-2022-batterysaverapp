package com.example.ecobattery;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.ecobattery.library.DisplayApp;
import com.example.ecobattery.library.SaverPagerAdapter;

public class SaverActivity extends AppCompatActivity {

    private ViewPager pager = null;
    private SaverPagerAdapter pagerAdapter = null;

    public DisplayApp[][] applicationArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saver_activity);

        initializeApplicationArray();

        pagerAdapter = new SaverPagerAdapter();
        pager = (ViewPager) findViewById (R.id.pager);
        pager.setAdapter (pagerAdapter);

        LayoutInflater inflater = getLayoutInflater();

        int widthOfContext = 1080;
        int heightOfContext = 1920;

        int widthApp = widthOfContext / 5;
        int heightApp = heightOfContext / 5;

        for (int x = 0; x < applicationArray.length; x++) {
            int currentX = 0;
            int currentY = 0;

            RelativeLayout v = (RelativeLayout) inflater.inflate (R.layout.view_apps, null);
            for (int y = 0; y < applicationArray[x].length; y++) {
                if (applicationArray[x][y] != null) {
                    ImageView appImageView = new ImageView(v.getContext());

                    appImageView.setImageDrawable(applicationArray[x][y].getIcon());

                    appImageView.setX(currentX);
                    appImageView.setY(currentY);

                    currentX += widthApp;

                    if (currentX == 1080) {
                        currentY += heightApp;
                        currentX = 0;
                    }


                    v.addView(appImageView);

                    appImageView.requestLayout();
                    appImageView.getLayoutParams().height = 200;
                    appImageView.getLayoutParams().width = 200;
                }
            }
            pagerAdapter.addView (v, x);
            pagerAdapter.notifyDataSetChanged();
        }
    }

    public void initializeApplicationArray() {
        final PackageManager pm = getPackageManager();
        ApplicationInfo[] packages = pm.getInstalledApplications(PackageManager.GET_META_DATA).toArray(new ApplicationInfo[0]);

        applicationArray = new DisplayApp[(int) Math.ceil((packages.length - 1) / 25) + 1][25];

        int x = 0;
        for (int i = 0; i < packages.length; i++) {
            applicationArray[x][i % 25] = new DisplayApp(false, pm.getApplicationIcon(packages[i]), "test");
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