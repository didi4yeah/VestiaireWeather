package com.didi.vestiaireweather.mvp.presenter;

import com.didi.vestiaireweather.mvp.view.FragmentViewCommonOps;
import com.didi.vestiaireweather.utils.SnackBarUtils;
import com.tinmegali.mvp.mvp.GenericPresenter;
import com.tinmegali.mvp.mvp.ModelOps;

/**
 * Created by didi on 12/12/16.
 * Recurrent methods to communicate between layers
 */
public abstract class BaseMVPPresenter
        <RequiredPresenterOps extends RequiredPresenterCommonOps,
        ProvidedModelOps, RequiredViewOps extends FragmentViewCommonOps,
        ModelType extends ModelOps<RequiredPresenterOps>>
        extends GenericPresenter<RequiredPresenterOps, ProvidedModelOps, RequiredViewOps, ModelType>
        implements RequiredPresenterCommonOps {

    @Override
    public void onDestroy(boolean isChangingConfiguration) {
        //Don't call super except activity (not fragment) is being destroyed !!
        //super.onDestroy(isChangingConfiguration);
    }

    @Override
    public void onShowSnack(int resStringId, String errorValue, SnackBarUtils.SnackBarType snackBarType) {
        getView().showingSnack(resStringId, errorValue, snackBarType);
    }

    @Override
    public void onBackPressed() {}
}