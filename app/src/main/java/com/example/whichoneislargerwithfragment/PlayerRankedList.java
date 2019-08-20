package com.example.whichoneislargerwithfragment;

import java.util.ArrayList;
import java.util.List;

public class PlayerRankedList {

    private List<PlayerAttributes> playerRankedList;

    public PlayerRankedList() {
        playerRankedList = new ArrayList<>();
    }

    public List<PlayerAttributes> getPlayerRankedList() {
        return playerRankedList;
    }

    public void setPlayerRankedList(List<PlayerAttributes> playerList) {
        this.playerRankedList = playerList;
    }

    public void addToRankedList(PlayerAttributes playerRankedList){
        this.playerRankedList.add(playerRankedList);
    }
}
