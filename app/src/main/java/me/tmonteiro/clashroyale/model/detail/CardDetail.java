package me.tmonteiro.clashroyale.model.detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static me.tmonteiro.clashroyale.di.RestApiModule.API_URL;

public class CardDetail {

    private static final String IMG_URL_PATTERN = "%simages/cards/%s.png";

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("idName")
    @Expose
    private String idName;
    @SerializedName("rarity")
    @Expose
    private String rarity;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("elixirCost")
    @Expose
    private Integer elixirCost;
    @SerializedName("arena")
    @Expose
    private Integer arena;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getElixirCost() {
        return elixirCost;
    }

    public void setElixirCost(Integer elixirCost) {
        this.elixirCost = elixirCost;
    }

    public Integer getArena() {
        return arena;
    }

    public void setArena(Integer arena) {
        this.arena = arena;
    }

    public String getImageUrl() {
        return String.format(IMG_URL_PATTERN, API_URL, getIdName());
    }
}
