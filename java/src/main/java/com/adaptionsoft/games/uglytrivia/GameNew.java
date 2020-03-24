package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

public class GameNew {

  private ArrayList<Player> players;
  int currentPlayer;
  private QuestionMaster questionMaster;

  boolean isGettingOutOfPenaltyBox;
  private Player player;

  public GameNew() {
    players = new ArrayList<>();
    questionMaster = new QuestionMaster();
  }

  public void add(String playerName) {
    players.add(new Player(playerName));

    System.out.println(playerName + " was added");
    System.out.println("They are player number " + players.size());
  }

  public void roll(int roll) {
    player = players.get(currentPlayer);

    System.out.println(player.getName() + " is the current player");
    System.out.println("They have rolled a " + roll);

    if (player.isInPenaltyBox()) {
      if (roll % 2 != 0) {
        isGettingOutOfPenaltyBox = true;
        System.out.println(player.getName() + " is getting out of the penalty box");
        move(roll);
        askQuestion();
      } else {
        System.out.println(player.getName() + " is not getting out of the penalty box");
        isGettingOutOfPenaltyBox = false;
      }
    } else {
      move(roll);
      askQuestion();
    }

  }

  private void move(int roll) {
    player.move(roll);
    System.out.println(player.getName()
        + "'s new location is "
        + player.getPosition());
  }

  private void askQuestion() {
    System.out.println("The category is " + questionMaster.getCategory(player.getPosition()));
    questionMaster.getQuestion(player.getPosition());
  }

  public boolean wasCorrectlyAnswered() {
    if (player.isInPenaltyBox()) {
      if (isGettingOutOfPenaltyBox) {
        System.out.println("Answer was correct!!!!");
        player.addToPurse();
        System.out.println(player.getName()
            + " now has "
            + player.getPurse()
            + " Gold Coins.");

        boolean winner = didPlayerWin();
        nextPlayer();
        if (currentPlayer == players.size()) {
          currentPlayer = 0;
        }

        return winner;
      } else {
        nextPlayer();
        if (currentPlayer == players.size()) {
          currentPlayer = 0;
        }
        return true;
      }


    } else {

      System.out.println("Answer was corrent!!!!");
      player.addToPurse();
      System.out.println(player.getName()
          + " now has "
          + player.getPurse()
          + " Gold Coins.");

      boolean winner = didPlayerWin();
      nextPlayer();
      if (currentPlayer == players.size()) {
        currentPlayer = 0;
      }

      return winner;
    }
  }

  public boolean wrongAnswer() {
    System.out.println("Question was incorrectly answered");
    System.out.println(player.getName() + " was sent to the penalty box");

    player.putInPenaltyBox();

    nextPlayer();
    return true;
  }


  private boolean didPlayerWin() {
    return !(player.getPurse() == 6);
  }

  private int nextPlayer() {
    currentPlayer++;
    if (currentPlayer == players.size()) {
      currentPlayer = 0;
    }
    return currentPlayer;
  }
}
