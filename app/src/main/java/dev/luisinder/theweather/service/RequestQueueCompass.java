package dev.luisinder.theweather.service;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Luis on 04/09/2015.
 * https://developer.android.com/training/volley/index.html
 * https://developer.android.com/training/volley/requestqueue.html
 */
public class RequestQueueCompass {
    private static RequestQueue requestQueue;

    public static void provide( Context context ){
        requestQueue = Volley.newRequestQueue(context);
    }

    public static RequestQueue getRequestQueue(){
        return requestQueue;
    }
}
