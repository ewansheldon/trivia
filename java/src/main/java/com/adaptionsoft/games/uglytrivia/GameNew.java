package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;

public class GameNew {

  public static final int MAXIMUM_QUESTIONS = 50;

  private ArrayList<Player> players;
  boolean[] inPenaltyBox = new boolean[6];

  LinkedList<String> popQuestions;
  LinkedList<String> scienceQuestions;
  LinkedList<String> sportsQuestions;
  LinkedList<String> rockQuestions;

  int currentPlayer;
  boolean isGettingOutOfPenaltyBox;

  public GameNew() {
    popQuestions = new LinkedList<>();
    scienceQuestions = new LinkedList<>();
    sportsQuestions = new LinkedList<>();
    rockQuestions = new LinkedList<>();

    players = new ArrayList<>();

    createQuestions();
  }

  private void createQuestions() {
    for (int i = 0; i < MAXIMUM_QUESTIONS; i++) {
      popQuestions.addLast("Pop Question " + i);
      scienceQuestions.addLast(("Science Question " + i));
      sportsQuestions.addLast(("Sports Question " + i));
      rockQuestions.addLast("Rock Question " + i);
    }
  }

  public void add(String playerName) {
    Player player = new Player(playerName);
    players.add(player);
    inPenaltyBox[players.indexOf(player)] = false;

    System.out.println(playerName + " was added");
    System.out.println("They are player number " + players.size());
  }

  public void roll(int roll) {
    Player player = players.get(currentPlayer);
    System.out.println(player.getName() + " is the current player");
    System.out.println("They have rolled a " + roll);

    if (inPenaltyBox[currentPlayer]) {
      if (roll % 2 != 0) {
        isGettingOutOfPenaltyBox = true;

        System.out.println(player.getName() + " is getting out of the penalty box");
        player.move(roll);
        if (player.getPosition() > 11) {
          player.move(-12);
        }

        System.out.println(player.getName()
            + "'s new location is "
            + player.getPosition());
        System.out.println("The category is " + currentCategory());
        askQuestion();
      } else {
        System.out.println(player.getName() + " is not getting out of the penalty box");
        isGettingOutOfPenaltyBox = false;
      }

    } else {

      player.move(roll);
      if (player.getPosition() > 11) {
        player.move(-12);
      }

      System.out.println(player.getName()
          + "'s new location is "
          + player.getPosition());
      System.out.println("The category is " + currentCategory());
      askQuestion();
    }

  }

  private void askQuestion() {
    if (isPop()) {
      System.out.println(popQuestions.removeFirst());
    } else if (isScience()) {
      System.out.println(scienceQuestions.removeFirst());
    } else if (isSports()) {
      System.out.println(sportsQuestions.removeFirst());
    } else {
      System.out.println(rockQuestions.removeFirst());
    }
  }


  private String currentCategory() {
    if (isPop()) {
      return "Pop";
    }
    if (isScience()) {
      return "Science";
    }
    if (isSports()) {
      return "Sports";
    }
    return "Rock";
  }


  private boolean isSports() {
    return players.get(currentPlayer).getPosition() == 2 || players.get(currentPlayer).getPosition() == 6 || players.get(currentPlayer).getPosition() == 10;
  }

  private boolean isScience() {
    return players.get(currentPlayer).getPosition() == 1 || players.get(currentPlayer).getPosition() == 5 || players.get(currentPlayer).getPosition() == 9;
  }

  private boolean isPop() {
    return players.get(currentPlayer).getPosition() == 0 || players.get(currentPlayer).getPosition() == 4 || players.get(currentPlayer).getPosition() == 8;
  }

  public boolean wasCorrectlyAnswered() {
    Player player = players.get(currentPlayer);
    if (inPenaltyBox[currentPlayer]) {
      if (isGettingOutOfPenaltyBox) {
        System.out.println("Answer was correct!!!!");
        player.addToPurse();
        System.out.println(players.get(currentPlayer).getName()
            + " now has "
            + players.get(currentPlayer).getPurse()
            + " Gold Coins.");

        boolean winner = didPlayerWin();
        currentPlayer++;
        if (currentPlayer == players.size()) {
          currentPlayer = 0;
        }

        return winner;
      } else {
        currentPlayer++;
        if (currentPlayer == players.size()) {
          currentPlayer = 0;
        }
        return true;
      }


    } else {

      System.out.println("Answer was corrent!!!!");
      player.addToPurse();
      System.out.println(players.get(currentPlayer).getName()
          + " now has "
          + players.get(currentPlayer).getPurse()
          + " Gold Coins.");

      boolean winner = didPlayerWin();
      currentPlayer++;
      if (currentPlayer == players.size()) {
        currentPlayer = 0;
      }

      return winner;
    }
  }

  public boolean wrongAnswer() {
    System.out.println("Question was incorrectly answered");
    System.out.println(players.get(currentPlayer).getName() + " was sent to the penalty box");
    inPenaltyBox[currentPlayer] = true;

    currentPlayer++;
    if (currentPlayer == players.size()) {
      currentPlayer = 0;
    }
    return true;
  }


  private boolean didPlayerWin() {
    return !(players.get(currentPlayer).getPurse() == 6);
  }
}
