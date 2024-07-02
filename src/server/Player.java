package server;

public class Player {
    private final String name;
    private boolean decision; // true for cooperate, false for defect
    private int coins;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean getDecision() {
        return decision;
    }

    public void setDecision(boolean decision) {
        this.decision = decision;
    }

    public int getCoins() {
        return this.coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }
}
