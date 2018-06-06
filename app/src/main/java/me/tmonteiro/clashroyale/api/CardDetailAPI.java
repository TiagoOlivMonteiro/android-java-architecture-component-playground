package me.tmonteiro.clashroyale.api;

import me.tmonteiro.clashroyale.model.detail.CardDetail;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CardDetailAPI {

    @GET("/api/cards/{idName}")
    Call<CardDetail> getCard(@Path("idName") String idName);
}
