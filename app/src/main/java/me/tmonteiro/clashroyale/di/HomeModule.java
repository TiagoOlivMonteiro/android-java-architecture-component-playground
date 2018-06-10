package me.tmonteiro.clashroyale.di;

import android.arch.lifecycle.ViewModelProvider;

import dagger.Module;
import dagger.Provides;
import me.tmonteiro.clashroyale.api.CardAPI;
import me.tmonteiro.clashroyale.repository.card.CardRepository;
import me.tmonteiro.clashroyale.viewmodel.ViewModelProviderWrapper;
import me.tmonteiro.clashroyale.viewmodel.card.CardViewModel;

@Module
public class HomeModule {

    @Provides
    CardRepository providesCardRepository(CardAPI cardAPI) {
        return new CardRepository(cardAPI);
    }

    @Provides
    ViewModelProvider.Factory providesCardViewModelFactory(CardRepository repository) {
        return new CardViewModel.Factory(repository);
    }

    @Provides
    ViewModelProviderWrapper providesViewModelProviderWrapper() {
        return new ViewModelProviderWrapper();
    }


}
