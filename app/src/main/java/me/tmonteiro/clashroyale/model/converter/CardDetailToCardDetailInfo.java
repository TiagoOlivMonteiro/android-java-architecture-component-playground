package me.tmonteiro.clashroyale.model.converter;

import me.tmonteiro.clashroyale.model.detail.CardDetail;
import me.tmonteiro.clashroyale.vo.detail.CardDetailInfo;

public class CardDetailToCardDetailInfo {

    public static CardDetailInfo convertCardDetatilToCardDetailInfo(CardDetail cardDetail) {
        CardDetailInfo cardDetailInfo = new CardDetailInfo();
        cardDetailInfo.setDescription(cardDetail.getDescription());
        return cardDetailInfo;
    }
}
