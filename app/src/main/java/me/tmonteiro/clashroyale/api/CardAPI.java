package me.tmonteiro.clashroyale.api;

import java.util.List;

import me.tmonteiro.clashroyale.model.card.Card;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CardAPI {

    @GET("/api/cards")
    Call<List<Card>> getCards();

}
