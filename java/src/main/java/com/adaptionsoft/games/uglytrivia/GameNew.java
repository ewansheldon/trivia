package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;

public class GameNew {

  public static final int MAXIMUM_QUESTIONS = 50;

  private ArrayList<Player> realPlayers;
  int[] places = new int[6];
  int[] purses = new int[6];
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

    realPlayers = new ArrayList<>();

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
    realPlayers.add(player);
    places[realPlayers.indexOf(player)] = 0;
    purses[realPlayers.indexOf(player)] = 0;
    inPenaltyBox[realPlayers.indexOf(player)] = false;

    System.out.println(playerName + " was added");
    System.out.println("They are player number " + realPlayers.size());
  }

  public void roll(int roll) {
//    System.out.println(players.get(currentPlayer) + " is the current player");
    System.out.println(realPlayers.get(currentPlayer).getName() + " is the current player");
    System.out.println("They have rolled a " + roll);

    if (inPenaltyBox[currentPlayer]) {
      if (roll % 2 != 0) {
        isGettingOutOfPenaltyBox = true;

        System.out.println(realPlayers.get(currentPlayer).getName() + " is getting out of the penalty box");
        places[currentPlayer] = places[currentPlayer] + roll;
        if (places[currentPlayer] > 11) {
          places[currentPlayer] = places[currentPlayer] - 12;
        }

        System.out.println(realPlayers.get(currentPlayer).getName()
            + "'s new location is "
            + places[currentPlayer]);
        System.out.println("The category is " + currentCategory());
        askQuestion();
      } else {
        System.out.println(realPlayers.get(currentPlayer).getName() + " is not getting out of the penalty box");
        isGettingOutOfPenaltyBox = false;
      }

    } else {

      places[currentPlayer] = places[currentPlayer] + roll;
      if (places[currentPlayer] > 11) {
        places[currentPlayer] = places[currentPlayer] - 12;
      }

      System.out.println(realPlayers.get(currentPlayer).getName()
          + "'s new location is "
          + places[currentPlayer]);
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
    return places[currentPlayer] == 2 || places[currentPlayer] == 6 || places[currentPlayer] == 10;
  }

  private boolean isScience() {
    return places[currentPlayer] == 1 || places[currentPlayer] == 5 || places[currentPlayer] == 9;
  }

  private boolean isPop() {
    return places[currentPlayer] == 0 || places[currentPlayer] == 4 || places[currentPlayer] == 8;
  }

  public boolean wasCorrectlyAnswered() {
    if (inPenaltyBox[currentPlayer]) {
      if (isGettingOutOfPenaltyBox) {
        System.out.println("Answer was correct!!!!");
        purses[currentPlayer]++;
        System.out.println(realPlayers.get(currentPlayer).getName()
            + " now has "
            + purses[currentPlayer]
            + " Gold Coins.");

        boolean winner = didPlayerWin();
        currentPlayer++;
        if (currentPlayer == realPlayers.size()) {
          currentPlayer = 0;
        }

        return winner;
      } else {
        currentPlayer++;
        if (currentPlayer == realPlayers.size()) {
          currentPlayer = 0;
        }
        return true;
      }


    } else {

      System.out.println("Answer was corrent!!!!");
      purses[currentPlayer]++;
      System.out.println(realPlayers.get(currentPlayer).getName()
          + " now has "
          + purses[currentPlayer]
          + " Gold Coins.");

      boolean winner = didPlayerWin();
      currentPlayer++;
      if (currentPlayer == realPlayers.size()) {
        currentPlayer = 0;
      }

      return winner;
    }
  }

  public boolean wrongAnswer() {
    System.out.println("Question was incorrectly answered");
    System.out.println(realPlayers.get(currentPlayer).getName() + " was sent to the penalty box");
    inPenaltyBox[currentPlayer] = true;

    currentPlayer++;
    if (currentPlayer == realPlayers.size()) {
      currentPlayer = 0;
    }
    return true;
  }


  private boolean didPlayerWin() {
    return !(purses[currentPlayer] == 6);
  }
}
