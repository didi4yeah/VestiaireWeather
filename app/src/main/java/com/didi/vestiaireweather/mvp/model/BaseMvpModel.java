package com.didi.vestiaireweather.mvp.model;

import android.util.Log;

import com.tinmegali.mvp.mvp.GenericModel;
import com.tinmegali.mvp.mvp.ModelOps;

import rx.Subscription;

/**
 * Created by didi on 11/12/2016.
 * Each "Model" layer will have access to these common ops
 */

class BaseMvpModel<RequiredPresenterOps> extends GenericModel<RequiredPresenterOps>
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
        String errorMessage;

//        if(throwable != null) {
//            if(throwable.getCause().getClass() == SocketTimeoutException.class) {
//                Log.e("error", "timeout");
//            } else Log.e("error", throwable.getMessage());
//        }

        Log.e("error", throwable.getMessage());
        //TODO show snack
        /*
        if(throwable == null) errorMessage = getAppContext().getString(R.string.error_generic_with_info);
        else errorMessage = getAppContext().getString(R.string.error_generic_with_info, throwable.getMessage());
        getPresenter().onShowSnack(errorMessage, SnackBarType.ALERT);
        */
    }
}
