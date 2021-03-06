package me.tmonteiro.clashroyale.vo.card;

public class CardInfo {

    private String idName;
    private String name;
    private String iconUrl;
    private int elixirCost;
    private String rarity;
    private int arena;

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Integer getElixirCost() {
        return elixirCost;
    }

    public void setElixirCost(int elixirCost) {
        this.elixirCost = elixirCost;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public Integer getArena() {
        return arena;
    }

    public void setArena(int arena) {
        this.arena = arena;
    }
}
