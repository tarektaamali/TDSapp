package com.mpdam.info.tdsapp.Activity;

import android.app.Application;
import android.os.SystemClock;

/**
 * Created by Info on 4/20/2018.
 */

public class MyApp extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        SystemClock.sleep(3000);
    }
}
