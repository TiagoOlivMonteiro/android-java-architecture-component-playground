package me.tmonteiro.clashroyale.di;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import dagger.android.AndroidInjection;
import me.tmonteiro.clashroyale.ClashRoyaleApp;

public class AppInjector {

    public static void init(ClashRoyaleApp application) {

        DaggerAppComponent.builder()
                .build()
                .inject(application);

        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                AndroidInjection.inject(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
                // Do nothing
            }

            @Override
            public void onActivityResumed(Activity activity) {
                // Do nothing
            }

            @Override
            public void onActivityPaused(Activity activity) {
                // Do nothing
            }

            @Override
            public void onActivityStopped(Activity activity) {
                // Do nothing
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                // Do nothing
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                // Do nothing
            }
        });
    }
}
