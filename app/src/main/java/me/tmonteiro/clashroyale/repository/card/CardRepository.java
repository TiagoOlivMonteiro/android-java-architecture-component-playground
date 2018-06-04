package me.tmonteiro.clashroyale.repository.card;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import me.tmonteiro.clashroyale.vo.card.CardInfo;
import me.tmonteiro.clashroyale.vo.card.CardComposition;
import me.tmonteiro.clashroyale.vo.card.CardStatus;

public class CardRepository {

    //TODO: Inject API + RxJava to combine streams

    private MutableLiveData<CardComposition> listCard;


    public LiveData<CardComposition> getCardList() {

        if (listCard == null) {
            loadCardList();
        }

        return listCard;

    }

    private void loadCardList() {

        listCard = new MutableLiveData<>();

        CardComposition cardHolder = new CardComposition();
        CardInfo cardInfo = new CardInfo();
        List<CardInfo> cardInfoList = new ArrayList<>();

        cardInfo.setName("ABC");
        cardInfoList.add(cardInfo);

        cardInfo = new CardInfo();
        cardInfo.setName("XYZ");
        cardInfoList.add(cardInfo);

        cardHolder.setStatus(CardStatus.SUCCESS);
        cardHolder.setResult(cardInfoList);
        listCard.postValue(cardHolder);
    }


}
