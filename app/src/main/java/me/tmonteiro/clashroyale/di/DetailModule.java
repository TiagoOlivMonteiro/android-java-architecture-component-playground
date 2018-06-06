package me.tmonteiro.clashroyale.di;

import android.arch.lifecycle.ViewModelProvider;

import dagger.Module;
import dagger.Provides;
import me.tmonteiro.clashroyale.api.CardDetailAPI;
import me.tmonteiro.clashroyale.repository.detail.CardDetailRepository;
import me.tmonteiro.clashroyale.viewmodel.detail.CardDetailViewModel;

@Module
public class DetailModule {

    @Provides
    CardDetailRepository providesCardDetailRepository(CardDetailAPI cardDetailAPI) {
        return new CardDetailRepository(cardDetailAPI);
    }

    @Provides
    ViewModelProvider.Factory providesCardDetailViewModelFactory(CardDetailRepository repository) {
        return new CardDetailViewModel.Factory(repository);
    }

}
