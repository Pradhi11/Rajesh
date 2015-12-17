package com.example.pradeep.rajtrack.utils;

import android.util.Log;

/**
 * Created by PRADEEP on 12/8/2015.
 */
public class UseFullMethods {


    public String conCat(String usn,String urll)
    {
        String url=urll;
        String ready=url.concat(usn);
        Log.d("ready", ready);

        return ready;
    }


}
