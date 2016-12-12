package com.didi.vestiaireweather.utils;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.didi.vestiaireweather.R;

/**
 * Created by didi on 12/12/16.
 */
public class SnackBarUtils {

    public enum SnackBarType {
        ALERT,
        INFO,
        CONFIRM
    }

    public static void showSnackBar(Context context, View snackBarLayout, String msg, SnackBarType snackBarType){
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(msg);
        showSnackBar(context, snackBarLayout, spannableStringBuilder, snackBarType);
        Log.e("Snack", spannableStringBuilder.toString());
    }

    private static void showSnackBar(Context context, View snackBarLayout, SpannableStringBuilder spannableString, SnackBarType snackBarType){
        Snackbar snackbar = createSnackBar(context, snackBarLayout, spannableString, snackBarType);
        snackbar.show();
    }

    private static Snackbar createSnackBar(Context context, View snackBarLayout, SpannableStringBuilder spannableString, SnackBarType snackBarType){
        Snackbar snackbar = Snackbar.make(snackBarLayout, spannableString, Snackbar.LENGTH_LONG);
        View snackBarView = snackbar.getView();

        int backgroundColorID = -1;
        int textColorID = -1; //white

        //Background
        switch (snackBarType){
            case ALERT:
                backgroundColorID = ContextCompat.getColor(context, android.R.color.holo_red_light);
                textColorID = ContextCompat.getColor(context, android.R.color.white);
                break;
            case INFO:
                backgroundColorID = ContextCompat.getColor(context, R.color.list_color_200);
                textColorID = ContextCompat.getColor(context, R.color.colorAccent);
                break;
            case CONFIRM:
                break;
        }
        if(backgroundColorID != -1)
            snackBarView.setBackgroundColor(backgroundColorID);

        //Text color
        ((TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text)).setTextColor(textColorID);

        return snackbar;
    }
}
