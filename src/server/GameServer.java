package server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class GameServer {
    private List<GameRoom> gameRooms = new ArrayList<>();
    private Map<Player, GameRoom> playerRoomMap = new HashMap<>();

    public synchronized void addPlayerToRoom(Player player) {
        System.out.println("Attempting to add player to a room");
        for (GameRoom room : gameRooms) {
            System.out.println("Checking room: " + room.getRoomNumber() + " | Is full: " + room.isFull());
            if (!room.isFull()) {
                if (room.addPlayer(player)) {
                    System.out.println("Player added to room: " + room.getRoomNumber());
                    playerRoomMap.put(player, room);
                    new Thread(room).start();
                    return;
                }
            }
        }

        // If no available room, create a new one
        GameRoom newRoom = new GameRoom(player, gameRooms.size());
        System.out.println("Creating new room with ID: " + newRoom.getRoomNumber());
        gameRooms.add(newRoom);
        playerRoomMap.put(player, newRoom);
        System.out.println("New room created");
    }

    public void sendMessageToRoom(String message, Player player){
        GameRoom room = playerRoomMap.get(player);
        room.broadcast(message);
    }
}
