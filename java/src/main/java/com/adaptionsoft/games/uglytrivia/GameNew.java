package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;

public class GameNew {

  public static final int MAXIMUM_QUESTIONS = 50;

  private ArrayList<Player> players;
  int currentPlayer;
  private QuestionMaster question;

  LinkedList<String> popQuestions;
  LinkedList<String> scienceQuestions;
  LinkedList<String> sportsQuestions;
  LinkedList<String> rockQuestions;

  boolean isGettingOutOfPenaltyBox;

  public GameNew() {
    popQuestions = new LinkedList<>();
    scienceQuestions = new LinkedList<>();
    sportsQuestions = new LinkedList<>();
    rockQuestions = new LinkedList<>();

    players = new ArrayList<>();
    question = new QuestionMaster();
  }

  public void add(String playerName) {
    Player player = new Player(playerName);
    players.add(player);

    System.out.println(playerName + " was added");
    System.out.println("They are player number " + players.size());
  }

  public void roll(int roll) {
    Player player = players.get(currentPlayer);
    System.out.println(player.getName() + " is the current player");
    System.out.println("They have rolled a " + roll);

    if (player.isInPenaltyBox()) {
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
        System.out.println("The category is " + question.getCategory(player.getPosition()));
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
      System.out.println("The category is " + question.getCategory(player.getPosition()));
      askQuestion();
    }

  }

  private void askQuestion() {
    question.getQuestion(players.get(currentPlayer).getPosition());
  }

  public boolean wasCorrectlyAnswered() {
    Player player = players.get(currentPlayer);
    if (player.isInPenaltyBox()) {
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
    players.get(currentPlayer).putInPenaltyBox();

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
