package com.didi.vestiaireweather.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import com.didi.vestiaireweather.activity.BaseActivity;
import com.didi.vestiaireweather.utils.SnackBarUtils;
import com.tinmegali.mvp.mvp.GenericMVPFragment;
import com.tinmegali.mvp.mvp.PresenterOps;

/**
 * Created by didi on 11/12/2016.
 * Each "View" layer will have access to these common ops
 */

public class BaseMvpFragmentView<ActivityViewCommonOps, RequiredViewOps,
        ProvidedPresenterOps, PresenterType extends PresenterOps<RequiredViewOps>>
        extends GenericMVPFragment<ActivityViewCommonOps, RequiredViewOps, ProvidedPresenterOps, PresenterType>
        implements FragmentViewCommonOps{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Needed to see and interact with toolbar menuItems
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean result;
        switch (item.getItemId()){
            case android.R.id.home:
                //Will intercept navigation icon in BaseActivity
                ((BaseActivity) mActivity.get()).onBackPressed();
                result = true;
                break;
            default:
                result = super.onOptionsItemSelected(item);
        }
        return result;
    }

    protected void showNavigationIcon(boolean visible){
        if(((BaseActivity) mActivity.get()).getSupportActionBar() != null)
            ((BaseActivity) mActivity.get()).getSupportActionBar().setDisplayHomeAsUpEnabled(visible);
    }


    @Override
    public void showingSnack(int resStringId, String errorValue, SnackBarUtils.SnackBarType snackBarType) {
        ((BaseActivity) mActivity.get()).showSnackBar(resStringId, errorValue, snackBarType);
    }
}
