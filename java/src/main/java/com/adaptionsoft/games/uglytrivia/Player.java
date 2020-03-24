package com.adaptionsoft.games.uglytrivia;

public class Player {
    private String playerName;
    private int position;
    private int purse;
    private boolean inPenaltyBox;

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

        if (position > 11) {
            position -= 12;
        }
    }

    public void addToPurse() {
        purse++;
    }

    public int getPurse() {
        return purse;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void putInPenaltyBox() {
        inPenaltyBox = true;
    }
}
