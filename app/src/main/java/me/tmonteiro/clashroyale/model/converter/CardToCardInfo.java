package me.tmonteiro.clashroyale.model.converter;

import java.util.ArrayList;
import java.util.List;

import me.tmonteiro.clashroyale.model.card.Card;
import me.tmonteiro.clashroyale.vo.card.CardInfo;

public class CardToCardInfo {

    public static List<CardInfo> convertCardListToCardInfoList(List<Card> list) {
        List<CardInfo> ret = new ArrayList<>();
        CardInfo cardInfo;

        for (Card card : list) {
            cardInfo = new CardInfo();
            cardInfo.setName(card.getName());
            cardInfo.setIconUrl(card.getImageUrl());
            cardInfo.setIdName(card.getIdName());
            cardInfo.setElixirCost(card.getElixirCost());
            cardInfo.setRarity(card.getRarity());
            cardInfo.setArena(card.getArena());
            ret.add(cardInfo);
        }

        return ret;
    }
}
