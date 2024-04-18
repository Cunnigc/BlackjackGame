package error.blackjack;

public class Card {
    // Attributes
    private final String suit;
    private final String rank;

    // Constructor
    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    // Method to determine the value of the card
    public int getValue() {
        int value;
        value = switch (rank) {
            case "2", "3", "4", "5", "6", "7", "8", "9", "10" -> Integer.parseInt(rank);
            case "Jack", "Queen", "King" -> 10;
            case "Ace" -> 11;
            default -> 0;
        }; // Assuming Ace can be 11 without busting or 1 otherwise
        // Unknown rank
        return value;
    }

    // Method to represent the card as a String
    @Override
    public String toString() {
        return rank + " of " + suit;
    }

    // Method to set the face up state of the card
    public void setFaceUp(boolean faceUp) {
    }
}
