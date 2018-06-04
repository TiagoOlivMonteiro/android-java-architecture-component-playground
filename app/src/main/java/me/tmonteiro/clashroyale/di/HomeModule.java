package me.tmonteiro.clashroyale.di;

import android.arch.lifecycle.ViewModelProvider;

import dagger.Module;
import dagger.Provides;
import me.tmonteiro.clashroyale.repository.card.CardRepository;
import me.tmonteiro.clashroyale.ui.activity.HomeActivity;
import me.tmonteiro.clashroyale.viewmodel.card.CardViewModel;

@Module
public class HomeModule {

    @Provides
    CardRepository providesCardRepository() {
        return new CardRepository();
    }

    @Provides
    ViewModelProvider.Factory providesViewModelFactory(HomeActivity activity, CardRepository repository) {
        return new CardViewModel.Factory(repository);
    }

}
