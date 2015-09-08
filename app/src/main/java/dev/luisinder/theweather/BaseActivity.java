package dev.luisinder.theweather;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import dev.luisinder.theweather.service.RequestQueueCompass;

/**
 * Created by Luis on 02/09/2015.
 */
public class BaseActivity extends FragmentActivity {

    protected Activity mActivity;
    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        mContext = this;
        RequestQueueCompass.provide(this);
    }

}
