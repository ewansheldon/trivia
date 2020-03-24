package com.adaptionsoft.games.uglytrivia;

public class GameNew {

  private PlayerService playerService;
  private QuestionService questionService;

  boolean isGettingOutOfPenaltyBox;
  private Player player;

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
    player = playerService.getCurrentPlayer();

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
    System.out.println("The category is " + questionService.getCategory(player.getPosition()));
    questionService.getQuestion(player.getPosition());
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

        playerService.nextPlayer();
        return winner;
      } else {
        playerService.nextPlayer();
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
      playerService.nextPlayer();
      return winner;
    }
  }

  public boolean wrongAnswer() {
    System.out.println("Question was incorrectly answered");
    System.out.println(player.getName() + " was sent to the penalty box");

    player.putInPenaltyBox();

    playerService.nextPlayer();
    return true;
  }


  private boolean didPlayerWin() {
    return !(player.getPurse() == 6);
  }

}
