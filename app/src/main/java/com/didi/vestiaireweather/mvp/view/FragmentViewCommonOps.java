package com.didi.vestiaireweather.mvp.view;

import com.didi.vestiaireweather.utils.SnackBarUtils;
import com.tinmegali.mvp.mvp.ContextView;

/**
 * Created by didi on 12/12/16.
 */
public interface FragmentViewCommonOps extends ContextView {
    void showingSnack(int resStringId, String errorValue, SnackBarUtils.SnackBarType snackBarType);
}
