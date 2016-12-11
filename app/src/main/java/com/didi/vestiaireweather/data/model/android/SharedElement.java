package com.didi.vestiaireweather.data.model.android;

import android.view.View;

/**
 * Created by didi on 11/06/16.
 * Represents a shared element between two activities or fragments
 */
public class SharedElement {
    private View view;
    private String name;

    public SharedElement(View view, String name) {
        this.view = view;
        this.name = name;
    }

    public View getView() {
        return view;
    }

    public String getName() {
        return name;
    }
}
