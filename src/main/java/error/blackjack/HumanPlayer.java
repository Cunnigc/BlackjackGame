package error.blackjack;


import error.blackjack.Player;


public class HumanPlayer extends Player {
    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public void takeCard(Card card) {
        hand.addCard(card);
    }
}

