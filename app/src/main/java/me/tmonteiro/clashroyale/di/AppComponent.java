package me.tmonteiro.clashroyale.di;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import me.tmonteiro.clashroyale.ClashRoyaleApp;

@Singleton
@Component(modules = {AndroidInjectionModule.class, ActivityModule.class, RestApiModule.class})
public interface AppComponent extends AndroidInjector<ClashRoyaleApp> {
}


