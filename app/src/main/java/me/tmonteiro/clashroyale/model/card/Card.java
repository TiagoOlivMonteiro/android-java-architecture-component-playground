package me.tmonteiro.clashroyale.model.card;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import me.tmonteiro.clashroyale.vo.card.CardInfo;

import static me.tmonteiro.clashroyale.di.RestApiModule.API_URL;

public class Card {

    private static final String IMG_URL_PATTERN = "%simages/cards/%s.png";

    @SerializedName("_id")
    @Expose
    public String id;
    @SerializedName("idName")
    @Expose
    public String idName;
    @SerializedName("rarity")
    @Expose
    public String rarity;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("elixirCost")
    @Expose
    public Integer elixirCost;
    @SerializedName("arena")
    @Expose
    public Integer arena;



    public String getId() {
        return id;
    }

    public String getIdName() {
        return idName;
    }

    public String getRarity() {
        return rarity;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getElixirCost() {
        return elixirCost;
    }

    public Integer getArena() {
        return arena;
    }

    public String getImageUrl(){
        return String.format(IMG_URL_PATTERN, API_URL, getIdName());     }

}
