package com.didi.vestiaireweather.mvp.model;

import com.didi.vestiaireweather.R;
import com.didi.vestiaireweather.mvp.presenter.RequiredPresenterCommonOps;
import com.didi.vestiaireweather.network.RetrofitException;
import com.didi.vestiaireweather.utils.SnackBarUtils;
import com.tinmegali.mvp.mvp.GenericModel;
import com.tinmegali.mvp.mvp.ModelOps;

import rx.Subscription;

/**
 * Created by didi on 11/12/2016.
 * Each "Model" layer will have access to these common ops
 */

class BaseMvpModel<RequiredPresenterOps extends RequiredPresenterCommonOps> extends GenericModel<RequiredPresenterOps>
        implements ModelOps<RequiredPresenterOps> {

    Subscription anySubscription;

    /**
     * Called by layer PRESENTER when VIEW pass for a reconstruction/destruction.
     * Useful for kill/stop activities that could be running on the background threads
     * @param isChangingConfiguration Informs that a change is occurring on configuration
     */
    @Override
    public void onDestroy(boolean isChangingConfiguration) {
        //Kill or stop actions
        if(anySubscription != null) anySubscription.unsubscribe();
    }

    /**
     * Show snackbar with throwable error
     * @param throwable error
     */
    void showSnackBarErrorApi(Throwable throwable) {
        String errorMessageValue;
        if(throwable != null) {
            errorMessageValue = throwable.getMessage();
            getPresenter().onShowSnack(R.string.error_generic_with_info, errorMessageValue, SnackBarUtils.SnackBarType.ALERT);

            //Special case if network problem :p
            if(((RetrofitException) throwable).getKind() == RetrofitException.Kind.NETWORK)
                getPresenter().onShowSnack(R.string.error_network, null, SnackBarUtils.SnackBarType.ALERT);
        } else
            getPresenter().onShowSnack(R.string.error_generic, null, SnackBarUtils.SnackBarType.ALERT);
    }
}
