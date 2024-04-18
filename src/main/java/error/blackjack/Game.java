package error.blackjack;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private String name;
    private ArrayList<Player> players;
    private final GroupOfCards deck;

    public Game(String name, ArrayList<Player> players) {
        this.name = name;
        this.players = players;
        this.deck = initializeDeck();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void play() {
        // Shuffle the deck
        deck.shuffle();

        // Deal two cards to each player
        for (int i = 0; i < 2; i++) {
            for (Player player : players) {
                player.takeCard(deck.getCards().remove(0));
            }
        }

        // Dealer's initial turn
        Player dealer = players.get(players.size() - 1);
        dealer.getHand().addCard(deck.getCards().remove(0)); // Add one card face down
        dealer.getHand().addCard(deck.getCards().remove(0)); // Add one card face up

        // Display initial cards for each player and the dealer
        System.out.println("Initial cards:");
        for (int i = 0; i < players.size() - 1; i++) {
            Player player = players.get(i);
            System.out.println(player.getName() + ": " + player.getHand());
        }
        System.out.println("Dealer: " + dealer.getHand().getCards().get(1)); // Show only one of the dealer's cards

        // Players' turns
        for (Player player : players) {
            if (player instanceof HumanPlayer humanPlayer) {
                humanPlayerTurn(humanPlayer);
            } else {
                dealerTurn((Dealer) player);
            }
        }

        // Dealer's turn
        while (dealer.getHand().calculateHandValue() < 16) {
            dealer.takeCard(deck.getCards().remove(0));
        }

        // Display the dealer's final hand
        System.out.println("Dealer's hand: " + dealer.getHand());

        // Determine the winner
        declareWinner();
    }

    private GroupOfCards initializeDeck() {
        ArrayList<Card> cards = new ArrayList<>();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        for (String suit : suits) {
            for (String rank : ranks) {
                cards.add(new Card(suit, rank));
            }
        }

        return new GroupOfCards(cards);
    }

    private void humanPlayerTurn(HumanPlayer player) {
        Scanner scanner = new Scanner(System.in);
        String choice;
        do {
            System.out.println(player.getName() + ", do you want to hit or stand? (h/s)");
            choice = scanner.nextLine();
            if (choice.equals("h")) {
                player.takeCard(deck.getCards().remove(0));
                System.out.println(player.getName() + " hits and gets: " + player.getHand().getLastCard());
                if (player.getHand().calculateHandValue() > 21) {
                    System.out.println(player.getName() + " loses!");
                    return; // Player busts, no need to continue
                }
            }
        } while (!choice.equals("s") && player.getHand().calculateHandValue() < 21);
    }

    private void dealerTurn(Dealer dealer) {
        while (dealer.getHand().calculateHandValue() < 16) {
            dealer.takeCard(deck.getCards().remove(0));
        }
    }

    private void declareWinner() {
        int dealerValue = players.get(players.size() - 1).getHand().calculateHandValue();
        for (int i = 0; i < players.size() - 1; i++) {
            Player player = players.get(i);
            int playerValue = player.getHand().calculateHandValue();
            if (playerValue > 21 || (dealerValue <= 21 && dealerValue > playerValue)) {
                System.out.println(player.getName() + " loses!");
            } else if (playerValue == dealerValue) {
                System.out.println(player.getName() + " ties with the dealer!");
            } else {
                System.out.println(player.getName() + " wins!");
            }
        }
    }
}

