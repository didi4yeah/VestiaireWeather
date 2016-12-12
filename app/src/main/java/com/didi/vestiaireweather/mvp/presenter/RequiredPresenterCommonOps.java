package com.didi.vestiaireweather.mvp.presenter;

import com.didi.vestiaireweather.utils.SnackBarUtils;

/**
 * Created by didi on 12/12/16.
 */
public interface RequiredPresenterCommonOps {
    void onShowSnack(int resStringId, String errorValue, SnackBarUtils.SnackBarType snackBarType);
}
