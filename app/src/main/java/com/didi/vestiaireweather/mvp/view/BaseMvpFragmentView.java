package com.didi.vestiaireweather.mvp.view;

import com.tinmegali.mvp.mvp.GenericMVPFragment;
import com.tinmegali.mvp.mvp.PresenterOps;

/**
 * Created by didi on 11/12/2016.
 * Each "View" layer will have access to these common ops
 */

public class BaseMvpFragmentView<ActivityViewCommonOps, RequiredViewOps,
        ProvidedPresenterOps, PresenterType extends PresenterOps<RequiredViewOps>>
        extends GenericMVPFragment<ActivityViewCommonOps, RequiredViewOps, ProvidedPresenterOps, PresenterType> {

}
