package me.tmonteiro.clashroyale.repository.card;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;

import me.tmonteiro.clashroyale.api.CardAPI;
import me.tmonteiro.clashroyale.model.card.Card;
import me.tmonteiro.clashroyale.model.converter.CardToCardInfo;
import me.tmonteiro.clashroyale.vo.card.CardComposition;
import me.tmonteiro.clashroyale.vo.card.CardStatus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CardRepository {

//    @Inject
    CardAPI cardAPI;

    private MutableLiveData<CardComposition> listCard;

    @Inject
    public CardRepository(CardAPI cardAPI){
        this.cardAPI = cardAPI;
    }

    public LiveData<CardComposition> getCardList() {

        if (listCard == null) {
            loadCardList();
        }

        return listCard;
    }

    private void loadCardList() {

        listCard = new MutableLiveData<>();
        final CardComposition cardHolder = new CardComposition();
        cardHolder.setStatus(CardStatus.LOADING);

        Call<List<Card>> cards = cardAPI.getCards();
        cards.enqueue(new Callback<List<Card>>() {
            @Override
            public void onResponse(Call<List<Card>> call, Response<List<Card>> response) {
                cardHolder.setStatus(CardStatus.SUCCESS);
                cardHolder.setResult(CardToCardInfo.convertCardListToCardInfoList(response.body()));
                listCard.setValue(cardHolder);
            }

            @Override
            public void onFailure(Call<List<Card>> call, Throwable t) {
                cardHolder.setStatus(CardStatus.ERROR);
                listCard.setValue(null);
            }
        });

        listCard.postValue(cardHolder);
    }



}
