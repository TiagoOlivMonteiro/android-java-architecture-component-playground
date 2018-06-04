package me.tmonteiro.clashroyale.di;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import me.tmonteiro.clashroyale.ClashRoyaleApp;


// TODO Add AppModule.class
@Component(modules = {AndroidInjectionModule.class, ActivityModule.class, RestApiModule.class})
public interface AppComponent extends AndroidInjector<ClashRoyaleApp> {
}

