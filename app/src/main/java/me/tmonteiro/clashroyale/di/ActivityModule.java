package me.tmonteiro.clashroyale.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import me.tmonteiro.clashroyale.ui.activity.HomeActivity;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector(modules = {HomeModule.class})
    abstract HomeActivity contributeActivityInjector();
}
