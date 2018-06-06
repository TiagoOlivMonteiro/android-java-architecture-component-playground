package me.tmonteiro.clashroyale.viewmodel.detail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import me.tmonteiro.clashroyale.repository.detail.CardDetailRepository;
import me.tmonteiro.clashroyale.vo.detail.CardDetailComposition;

public class CardDetailViewModel extends ViewModel {

    private final CardDetailRepository cardDetailRepository;

    public CardDetailViewModel(CardDetailRepository cardDetailRepository) {
        this.cardDetailRepository = cardDetailRepository;
    }

    public LiveData<CardDetailComposition> getCard(String idName) {
        return this.cardDetailRepository.getCard(idName);
    }

    public static class Factory implements ViewModelProvider.Factory {

        private final CardDetailRepository cardDetailRepository;

        public Factory(CardDetailRepository cardDetailRepository) {
            this.cardDetailRepository = cardDetailRepository;
        }

        @Override
        public CardDetailViewModel create(Class modelClass) {
            return new CardDetailViewModel(cardDetailRepository);
        }

    }
}
