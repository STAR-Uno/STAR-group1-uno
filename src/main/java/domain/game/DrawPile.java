package domain.game;

import domain.card.Card;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class DrawPile {
    private final Deque<Card> cards = new ArrayDeque<>();

    public DrawPile(List<Card> shuffledCards) {
        cards.addAll(shuffledCards);
    }

    public Card drawCard() {
        return cards.pop();
    }

    public int getSize() {
        return cards.size();
    }
}
