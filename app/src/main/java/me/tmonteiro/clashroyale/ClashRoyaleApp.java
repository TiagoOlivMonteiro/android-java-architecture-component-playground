package me.tmonteiro.clashroyale;

import android.app.Activity;
import android.app.Application;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import me.tmonteiro.clashroyale.di.AppInjector;

// TODO: Design Detail
// TODO: API: Loading
// TODO> API: Error (Card e Detail)
// TODO: Testing: Instrumented teste and unit test
// TODO: CI CircleCI
public class ClashRoyaleApp extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        AppInjector.init(this);
    }


    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
