package me.tmonteiro.clashroyale.viewmodel;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

public class ViewModelProviderWrapper {

//    public ViewModelProvider of(Activity activity, ViewModelProvider.Factory factory) {
//        return ViewModelProviders.of(activity, factory);
//    }

    public ViewModelProvider of(@NonNull FragmentActivity activity,
                                @Nullable ViewModelProvider.Factory factory) {
        return ViewModelProviders.of(activity, factory);
    }
}
