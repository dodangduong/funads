package funs.adds.android.sdk.component.util;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

public class ScreenUtil {
    public static boolean isLanScape (Context context) {
        int orientation = context.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            return true;
        } else {
            return false;
            // In portrait
        }
    }

    public static int getHeigthScreen(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        AppCompatActivity activity = (AppCompatActivity) context;
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return  displayMetrics.heightPixels;
    }

    public static int getWidthScreen(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        AppCompatActivity activity = (AppCompatActivity) context;
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return  displayMetrics.widthPixels;
    }
}
