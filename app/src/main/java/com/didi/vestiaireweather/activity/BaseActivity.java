package com.didi.vestiaireweather.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.widget.FrameLayout;

import com.didi.vestiaireweather.R;
import com.didi.vestiaireweather.data.model.android.SharedElement;
import com.didi.vestiaireweather.data.model.vestiaire.WeatherDayVestiaire;
import com.didi.vestiaireweather.listener.OnCurrentWeatherLoadedListener;
import com.didi.vestiaireweather.listener.OnCustomItemSelectedListener;
import com.didi.vestiaireweather.mvp.view.ActivityViewCommonOps;
import com.didi.vestiaireweather.mvp.view.DetailsWeatherFragmentView;
import com.didi.vestiaireweather.mvp.view.HomeWeatherFragmentView;
import com.didi.vestiaireweather.utils.SnackBarUtils;
import com.didi.vestiaireweather.utils.StringUtils;
import com.mikepenz.iconics.context.IconicsLayoutInflater;
import com.mikepenz.iconics.view.IconicsImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by didi on 11/12/2016.
 */

public class BaseActivity extends AppCompatActivity implements ActivityViewCommonOps, OnCustomItemSelectedListener, OnCurrentWeatherLoadedListener {

    @BindView(R.id.coordinator)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Be ready to use font icon
        LayoutInflaterCompat.setFactory(getLayoutInflater(), new IconicsLayoutInflater(getDelegate()));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        //Show Home weather list fragment if handset or tablet
        replaceMainFragment(HomeWeatherFragmentView.newInstance(), null, false, HomeWeatherFragmentView.TAG);
        //Show Details weather only if tablet
        FrameLayout frameLayoutDetails = (FrameLayout) findViewById(R.id.base_frame_layout_second);
        if (frameLayoutDetails != null) {
            replaceSpecificFragment(
                    R.id.base_frame_layout_second,
                    DetailsWeatherFragmentView.newInstance(null),
                    false, DetailsWeatherFragmentView.TAG);
        }
    }

    /**
     * Generic method to replace main fragment in BaseActivity
     * @param layoutFrameId frameLayout we want to destFragment
     * @param destFragment we want to show
     * @param addToBackStack (true/false)
     * @param tag tag
     */
    @Override
    public void replaceSpecificFragment(int layoutFrameId, Fragment destFragment, boolean addToBackStack, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(StringUtils.isNotNullOrEmpty(tag)){
            fragmentTransaction.replace(layoutFrameId, destFragment, tag);
        } else {
            fragmentTransaction.replace(layoutFrameId, destFragment);
        }
        if (addToBackStack){
            //Tag very useful to go back to specific transaction
            fragmentTransaction.addToBackStack(tag);
        }
        fragmentTransaction.commit();
    }

    /**
     * Generic method to replace main fragment in BaseActivity
     * @param destFragment we want to show
     * @param listSharedElements list transition elements
     * @param addToBackStack (true/false)
     * @param tag tag
     */
    @Override
    public void replaceMainFragment(Fragment destFragment, List<SharedElement> listSharedElements, boolean addToBackStack, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //i.e. Icon shared element transition
        Transition moveTransform = TransitionInflater.from(this).inflateTransition(android.R.transition.move);
        moveTransform.setDuration(500);
        destFragment.setSharedElementEnterTransition(moveTransform);
        destFragment.setSharedElementReturnTransition(moveTransform);

        if(listSharedElements != null){
            for (SharedElement sharedElement: listSharedElements) {
                fragmentTransaction.addSharedElement(sharedElement.getView(), sharedElement.getName());
            }
        }

        if(StringUtils.isNotNullOrEmpty(tag)){
            fragmentTransaction.replace(R.id.base_frame_layout_main, destFragment, tag);
        } else {
            fragmentTransaction.replace(R.id.base_frame_layout_main, destFragment);
        }
        if (addToBackStack){
            //Tag very useful to go back to specific transaction
            fragmentTransaction.addToBackStack(tag);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void showProgressDialog(String message, boolean cancelable) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(message);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(cancelable);
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        if(progressDialog != null && progressDialog.isShowing()) progressDialog.dismiss();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void showSnackBar(int resStringId, String errorValue, SnackBarUtils.SnackBarType snackBarType) {
        String snackMessage;
        if(StringUtils.isNotNullOrEmpty(errorValue))
            snackMessage = getString(resStringId, errorValue);
        else
            snackMessage = getString(resStringId);

        SnackBarUtils.showSnackBar(this, coordinatorLayout, snackMessage, snackBarType);
    }

    @Override
    public void onItemSelected(IconicsImageView ivWeatherIcon, WeatherDayVestiaire currentWeatherDay) {
        DetailsWeatherFragmentView detailsFrag =
                    (DetailsWeatherFragmentView) getSupportFragmentManager().findFragmentByTag(DetailsWeatherFragmentView.TAG);
            if (detailsFrag == null) {
                //Details Fragment is not in the layout (handset layout) so display it
                showDetailsWeatherFragment(ivWeatherIcon, currentWeatherDay);
            } else {
                //Details Fragment is in the layout (tablet layout) so refresh content
                detailsFrag.showWeatherDetails(currentWeatherDay);
            }
    }

    private void showDetailsWeatherFragment(IconicsImageView ivWeatherIcon, WeatherDayVestiaire weatherDayVestiaire){
        List<SharedElement> list = new ArrayList<>();
        //Add transition elements, be careful to have a unique transition name id (especially in viewPager with same layout many times) !!
        SharedElement sharedElementPic = new SharedElement(ivWeatherIcon, ivWeatherIcon.getTransitionName());
        list.add(sharedElementPic);

        replaceMainFragment(
                DetailsWeatherFragmentView.newInstance(weatherDayVestiaire),
                list, true, DetailsWeatherFragmentView.TAG);
    }

    @Override
    public void onCurrentWeatherLoaded(WeatherDayVestiaire currentWeatherDay) {
        DetailsWeatherFragmentView detailsFrag =
                (DetailsWeatherFragmentView) getSupportFragmentManager().findFragmentByTag(DetailsWeatherFragmentView.TAG);
        //If tablet mode
        if (detailsFrag != null) {
            //Refresh current weather content
            detailsFrag.showWeatherDetails(currentWeatherDay);
        }
    }
}
