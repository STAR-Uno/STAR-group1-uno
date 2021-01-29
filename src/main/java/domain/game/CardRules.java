package domain.game;

import domain.card.*;

public class CardRules {
    public static boolean isValidNumberCard(Card topCard, NumberCard playedCard) {
        if(isSameColor(topCard, playedCard)){
            return true;
        }

        if (topCard.getType() == CardType.NUMBER) {
            return ((NumberCard) topCard).getValue() == playedCard.getValue();
        }

        return false;
    }

    public static boolean isValidActionCard(Card topCard, ActionCard playedCard) {
        if(isSameColor(topCard, playedCard)){
            return true;
        }

        return topCard.getType() == playedCard.getType();
    }

    public static boolean isValidWildCard(Card topCard, WildCard playedCard) {
        if(isSameColor(topCard, playedCard)){
            return true;
        }

        return topCard.getType() == playedCard.getType();
    }

    private static boolean isSameColor(Card topCard, Card playedCard) {
        return topCard.getColor() == playedCard.getColor();
    }
}
