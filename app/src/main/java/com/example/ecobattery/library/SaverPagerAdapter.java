package com.example.ecobattery.library;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.media.Image;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class SaverPagerAdapter extends PagerAdapter {

    private Context mContext;
    public DisplayApp[] applicationArray;

    public SaverPagerAdapter(Context context) {
        mContext = context;

        initializeApplicationArray();
    }

    public void initializeApplicationArray() {
        final PackageManager pm = mContext.getPackageManager();
        ApplicationInfo[] packages = pm.getInstalledApplications(PackageManager.GET_META_DATA).toArray(new ApplicationInfo[0]);

        applicationArray = new DisplayApp[packages.length];

        for (int i = 0; i < packages.length; i++) {
            applicationArray[i] = new DisplayApp(false, pm.getApplicationIcon(packages[i]), "test");
        }

        for (int i = 0; i < applicationArray.length; i++) {
            System.out.println(applicationArray[i].getIcon());
        }
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        ModelObject modelObject = ModelObject.values()[position];
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(modelObject.getLayoutResId(), collection, false);

        int widthOfContext = 1080;
        int heightOfContext = 1920;

        System.out.println(widthOfContext);
        System.out.println(heightOfContext);

        int widthApp = widthOfContext / 5;
        int heightApp = heightOfContext / 5;

        int currentX = 0;
        int currentY = 0;

        for (DisplayApp displayApp : applicationArray) {

            ImageView appImageView = new ImageView(mContext);

            appImageView.setImageDrawable(displayApp.getIcon());

            appImageView.setX(currentX);
            appImageView.setY(currentY);

            currentX += widthApp;

            if (currentX == 1080) {
                currentY += heightApp;
                currentX = 0;
            }


            layout.addView(appImageView);

            appImageView.requestLayout();
            appImageView.getLayoutParams().height = 200;
            appImageView.getLayoutParams().width = 200;
        }

        collection.addView(layout);

        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return ModelObject.values().length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        ModelObject customPagerEnum = ModelObject.values()[position];
        return mContext.getString(customPagerEnum.getTitleResId());
    }

}
