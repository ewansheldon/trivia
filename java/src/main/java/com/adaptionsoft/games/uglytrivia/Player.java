package com.adaptionsoft.games.uglytrivia;

public class Player {
    private String playerName;
    private int position;
    private int purse;

    public Player(String playerName) {
        this.playerName = playerName;
    }

    public String getName() {
        return playerName;
    }

    public int getPosition() {
        return position;
    }

    public void move(int roll) {
        position += roll;
    }

    public void addToPurse() {
        purse++;
    }

    public int getPurse() {
        return purse;
    }
}
