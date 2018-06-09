package me.tmonteiro.clashroyale.repository.detail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import javax.inject.Inject;

import me.tmonteiro.clashroyale.api.CardDetailAPI;
import me.tmonteiro.clashroyale.model.converter.CardDetailToCardDetailInfo;
import me.tmonteiro.clashroyale.model.detail.CardDetail;
import me.tmonteiro.clashroyale.vo.detail.CardDetailComposition;
import me.tmonteiro.clashroyale.vo.detail.CardDetailStatus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CardDetailRepository {

    CardDetailAPI detailCardAPI;

    private MutableLiveData<CardDetailComposition> cardDetailLiveData;

    @Inject
    public CardDetailRepository(CardDetailAPI detailCardAPI) {
        this.detailCardAPI = detailCardAPI;
    }

    public LiveData<CardDetailComposition> getCard(String card) {

        loadCardDetail(card);

        return cardDetailLiveData;
    }

    private void loadCardDetail(String card) {

        this.cardDetailLiveData = new MutableLiveData<>();

        final CardDetailComposition cardDetail = new CardDetailComposition();
        cardDetail.setStatus(CardDetailStatus.LOADING);

        Call<CardDetail> cardDetailCall = detailCardAPI.getCard(card);
        cardDetailCall.enqueue(new Callback<CardDetail>() {
            @Override
            public void onResponse(Call<CardDetail> call, Response<CardDetail> response) {
                cardDetail.setStatus(CardDetailStatus.SUCCESS);
                cardDetail.setResult(CardDetailToCardDetailInfo.convertCardDetatilToCardDetailInfo(response.body()));
                cardDetailLiveData.setValue(cardDetail);
            }

            @Override
            public void onFailure(Call<CardDetail> call, Throwable t) {
                cardDetail.setStatus(CardDetailStatus.ERROR);
                cardDetail.setResult(null);
                cardDetailLiveData.setValue(null);
            }
        });


        this.cardDetailLiveData.postValue(cardDetail);
    }


}
