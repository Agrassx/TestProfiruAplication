package ru.profi.test.myapplication.app;

import android.app.Application;

import com.vk.sdk.VKSdk;

public class VKApplication extends Application {

    private static VKApplication mInstance;

    public static synchronized VKApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        VKSdk.initialize(this);
        mInstance = this;
    }

}
