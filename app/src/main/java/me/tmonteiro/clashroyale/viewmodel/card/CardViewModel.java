package me.tmonteiro.clashroyale.viewmodel.card;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import me.tmonteiro.clashroyale.vo.card.CardComposition;
import me.tmonteiro.clashroyale.repository.card.CardRepository;

public class CardViewModel extends ViewModel {

    private final CardRepository cardRepository;

    public CardViewModel(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public LiveData<CardComposition> getCardList() {
        return this.cardRepository.getCardList();
    }

    public static class Factory implements ViewModelProvider.Factory {

        private final CardRepository cardRepository;

        public Factory(CardRepository cardRepository) {
            this.cardRepository = cardRepository;
        }

        @Override
        public CardViewModel create(Class modelClass) {
            return new CardViewModel(cardRepository);
        }

    }

}
