package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

public class PlayerService {

  private ArrayList<Player> players;
  int currentPlayer;

  public PlayerService() {
    this.players = new ArrayList<>();
  }

  public void addPlayer(String playerName) {
    players.add(new Player(playerName));
  }

  public Player getCurrentPlayer() {
    return players.get(currentPlayer);
  }

  public void nextPlayer() {
    currentPlayer++;
    if (currentPlayer == players.size()) {
      currentPlayer = 0;
    }
  }

  public int getTotalPlayers() {
    return players.size();
  }

  public void movePlayer(int roll) {
    getCurrentPlayer().move(roll);
  }

  public String getCurrentPlayerName() {
    return getCurrentPlayer().getName();
  }

  public int getCurrentPlayerPosition() {
    return getCurrentPlayer().getPosition();
  }


  public void putCurrentPlayerInPenaltyBox() {
    getCurrentPlayer().putInPenaltyBox();
  }

  public int getCurrentPlayerPurse() {
    return getCurrentPlayer().getPurse();
  }

  public void addToCurrentPlayerPurse() {
    getCurrentPlayer().addToPurse();
  }
}
