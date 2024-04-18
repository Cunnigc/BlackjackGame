package error.blackjack;

public class Dealer extends Player {
    public Dealer(String name) {
        super(name);
    }

    @Override
    public void takeCard(Card card) {
        hand.addCard(card);
    }
}

