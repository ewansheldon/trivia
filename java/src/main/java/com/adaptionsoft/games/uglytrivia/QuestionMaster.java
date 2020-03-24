package com.adaptionsoft.games.uglytrivia;

import java.util.LinkedList;

public class QuestionMaster {

  public static final int MAXIMUM_QUESTIONS = 50;

  LinkedList<String> popQuestions;
  LinkedList<String> scienceQuestions;
  LinkedList<String> sportsQuestions;
  LinkedList<String> rockQuestions;


  public QuestionMaster() {
    popQuestions = new LinkedList<>();
    scienceQuestions = new LinkedList<>();
    sportsQuestions = new LinkedList<>();
    rockQuestions = new LinkedList<>();

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

  public String getCategory(int playerPosition) {
    if (isPop(playerPosition)) {
      return "Pop";
    }
    if (isScience(playerPosition)) {
      return "Science";
    }
    if (isSports(playerPosition)) {
      return "Sports";
    }
    return "Rock";
  }


  private boolean isSports(int playerPosition) {
    return playerPosition == 2 || playerPosition == 6 || playerPosition == 10;
  }

  private boolean isScience(int playerPosition) {
    return playerPosition == 1 || playerPosition == 5 || playerPosition == 9;
  }

  private boolean isPop(int playerPosition) {
    return playerPosition == 0 || playerPosition == 4 || playerPosition == 8;
  }

  public void getQuestion(int playerPosition) {
    if (isPop(playerPosition)) {
      System.out.println(popQuestions.removeFirst());
    } else if (isScience(playerPosition)) {
      System.out.println(scienceQuestions.removeFirst());
    } else if (isSports(playerPosition)) {
      System.out.println(sportsQuestions.removeFirst());
    } else {
      System.out.println(rockQuestions.removeFirst());
    }
  }

}
