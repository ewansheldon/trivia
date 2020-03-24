package com.adaptionsoft.games.uglytrivia;

public class GameNew {

  private PlayerService playerService;
  private QuestionService questionService;

  boolean isGettingOutOfPenaltyBox;

  public GameNew() {
    playerService = new PlayerService();
    questionService = new QuestionService();
  }

  public void add(String playerName) {
    playerService.addPlayer(playerName);

    System.out.println(playerName + " was added");
    System.out.println("They are player number " + playerService.getTotalPlayers());
  }

  public void roll(int roll) {
    System.out.println(playerService.getCurrentPlayerName() + " is the current player");
    System.out.println("They have rolled a " + roll);

    if (playerService.getCurrentPlayer().isInPenaltyBox()) {
      if (roll % 2 != 0) {
        isGettingOutOfPenaltyBox = true;
        Announcer.getOutOfPenanltyBox(playerService.getCurrentPlayerName());
        move(roll);
        askQuestion();
      } else {
        System.out.println(playerService.getCurrentPlayerName() + " is not getting out of the penalty box");
        isGettingOutOfPenaltyBox = false;
      }
    } else {
      move(roll);
      askQuestion();
    }
  }

  private void move(int roll) {
    playerService.movePlayer(roll);
    System.out.println(playerService.getCurrentPlayerName()
        + "'s new location is "
        + playerService.getCurrentPlayerPosition());
  }

  private void askQuestion() {
    System.out.println(
        "The category is " + questionService.getCategory(playerService.getCurrentPlayerPosition()));
    questionService.getQuestion(playerService.getCurrentPlayerPosition());
  }

  public boolean wasCorrectlyAnswered() {
    boolean shouldContinue = true;

    if (isAbleToMove(playerService.getCurrentPlayer())) {
      System.out.println("Answer was correct!!!!");
      playerService.addToCurrentPlayerPurse();
      System.out.println(playerService.getCurrentPlayerName()
          + " now has "
          + playerService.getCurrentPlayerPurse()
          + " Gold Coins.");

      shouldContinue = continueGame();
    }

    playerService.nextPlayer();
    return shouldContinue;
  }

  private boolean isAbleToMove(Player player) {
    return !player.isInPenaltyBox() || isGettingOutOfPenaltyBox;
  }

  public boolean wrongAnswer() {
    System.out.println("Question was incorrectly answered");
    System.out.println(playerService.getCurrentPlayerName() + " was sent to the penalty box");

    playerService.putCurrentPlayerInPenaltyBox();
    playerService.nextPlayer();
    return true;
  }


  private boolean continueGame() {
    return !(playerService.getCurrentPlayerPurse() == 6);
  }

}
