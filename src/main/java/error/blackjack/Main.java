package error.blackjack;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Create players
        HumanPlayer player1 = new HumanPlayer("Player 1");
        HumanPlayer player2 = new HumanPlayer("Player 2");
        Dealer dealer = new Dealer("Dealer");

        // Add players to the game
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(dealer);

        // Create a game
        Game game = new Game("Blackjack", players);

        // Start the game
        game.play();
    }
}
