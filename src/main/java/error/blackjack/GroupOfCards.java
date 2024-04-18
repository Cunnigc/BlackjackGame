package error.blackjack;

import java.util.ArrayList;
import java.util.Collections;

public class GroupOfCards {
    // Attributes
    private ArrayList<Card> cards;
    private int size;

    // Constructor
    public GroupOfCards(ArrayList<Card> cards) {
        this.cards = cards;
        this.size = cards.size();
    }

    // Getters and setters
    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
        this.size = cards.size();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    // Method to shuffle the group of cards
    public void shuffle() {
        Collections.shuffle(cards);
    }
}
