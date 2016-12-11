package com.didi.vestiaireweather.mvp.view;

import android.support.v4.app.Fragment;

import com.didi.vestiaireweather.data.model.android.SharedElement;

import java.util.List;

/**
 * Created by didi on 11/12/16.
 * Interface with commons feedback operations to be implemented in Activity
 */
public interface ActivityViewCommonOps {
    void replaceSpecificFragment(int layoutFrameId, Fragment destFragment, boolean addToBackStack, String tag);
    void replaceMainFragment(Fragment destFragment, List<SharedElement> listSharedElements, boolean addToBackStack, String tag);
    void showProgressDialog(String message, boolean cancelable);
    void hideProgressDialog();
}
