package server;

public class Player {
    private final String name;
    private int roomNumber;
    private boolean decision; // true for cooperate, false for defect
    private int coins;
    ClientHandler clientHandler;

    public Player(String name, ClientHandler client) {
        this.name = name;
        this.clientHandler = client;
    }

    public String getName() {
        return name;
    }

    public int getRoomNumber(){
        return roomNumber;
    }

    public void setRoomNumber(int num){
        this.roomNumber = num;
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

    public ClientHandler getClientHandler(){
        return this.clientHandler;
    }
}
