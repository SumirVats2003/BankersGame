package server;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GameRoom implements Runnable {
    private final int roomNumber;
    private Player player1;
    private Player player2;
    private final Lock lock = new ReentrantLock();
    private boolean isFull = false;

    GameRoom(Player player, int roomNum) {
        this.roomNumber = roomNum;
        this.player1 = player;
    }

    public int getRoomNumber() {
        return roomNumber;
    }


    public boolean addPlayer(Player player) {
        lock.lock();
        try {
            if (player2 == null) {
                player2 = player;
                isFull = true;
                System.out.println("Player " + player.getName() + " added to room " + roomNumber);
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    public boolean isFull() {
        return isFull;
    }

    @Override
    public void run() {
        System.out.println("GameRoom " + roomNumber + " is running with players: " + player1.getName() + " and " + player2.getName());
        // Game room logic here
    }

    public void broadcast(String message){
        player1.getClientHandler().sendMessage(message);
        player2.getClientHandler().sendMessage(message);
    }
}
