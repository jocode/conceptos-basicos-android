package com.crexative.tipcalc;

import android.app.Application;

public class TipCalcApp extends Application {

    private final static String ABOUT_URL = "http://johanmosquera.me";

    public String getAboutUrl() {
        return ABOUT_URL;
    }
}
