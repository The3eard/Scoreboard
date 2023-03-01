package org.sportradar;

import java.util.Scanner;
import org.sportradar.constants.Constants;
import org.sportradar.services.ScoreboardService;
import org.sportradar.utils.Utils;

public class Main {

  public static void main(String[] args) {

    ScoreboardService.initData();
    System.out.println(Constants.BANNER);
    ScoreboardService.getSummary();
    launchUserInterface();
  }

  /**
   * Prompts the user to select an option
   */
  public static void launchUserInterface() {
    var exit = true;
    var sc = new Scanner(System.in);
    String userInput;
    while (exit) {
      System.out.println(Constants.MENU);
      userInput = sc.nextLine();
      if (Utils.isValidOption(userInput)) {
        switch (Integer.parseInt(userInput)) {
          case 1 -> startGame();
          case 2 -> finishGame();
          case 3 -> updateScore();
          case 4 -> getSummary();
          case 5 -> exit = false;
        }
      } else {
        System.out.println(Constants.ERROR);
      }
    }
    System.out.println(Constants.THANKS);
  }

  /**
   * Prompts the user to add a match
   */
  public static void startGame() {
    System.out.println(Constants.ADD_MATCH_SELECTED);
    var sc = new Scanner(System.in);
    System.out.println(Constants.INSERT_HOME_TEAM);
    var homeTeam = Utils.formatTeamName(sc.nextLine());
    System.out.println(Constants.INSERT_AWAY_TEAM);
    var awayTeam = Utils.formatTeamName(sc.nextLine());
    if (!homeTeam.equals(awayTeam)) {
      var match = ScoreboardService.addMatch(homeTeam, awayTeam);
      if (match != null) {
        System.out.println(homeTeam + Constants.SEPARATOR + awayTeam + Constants.MATCH_ADDED);
      } else {
        System.out.println(homeTeam + Constants.SEPARATOR + awayTeam + Constants.CANNOT_ADD_MATCH);
      }
    } else {
      System.out.println(Constants.NOT_VALID_TEAMS);
    }

  }

  /**
   * Prompts the user to finish a match
   */
  public static void finishGame() {
    System.out.println(Constants.FINISH_GAME_SELECTED);
    ScoreboardService.getSummary();
    var sc = new Scanner(System.in);
    System.out.println(Constants.FINISH_GAME);
    var id = sc.nextLine();
    if (Utils.isValidId(id)) {
      var index = Utils.checkIfIdExists(Integer.parseInt(id));
      var match = ScoreboardService.finishMatch(index);
      if (match != null) {
        System.out.println(match + Constants.FINISHED_MATCH);
      } else {
        System.out.println(Constants.NOT_FOUND);
      }
    } else {
      System.out.println(Constants.ERROR);
    }
  }

  /**
   * Prompts the user to updathe the score of a match
   */
  public static void updateScore() {
    System.out.println(Constants.UPDATE_GAME_SELECTED);
    ScoreboardService.getSummary();
    var sc = new Scanner(System.in);
    System.out.println(Constants.UPDATE_GAME);
    var id = sc.nextLine();
    if (Utils.isValidId(id)) {
      var index = Utils.checkIfIdExists(Integer.parseInt(id));
      if (index >= Constants.ZERO) {
        System.out.println(Constants.INSERT_HOME_SCORE);
        var homeScore = sc.nextLine().trim();
        System.out.println(Constants.INSERT_AWAY_SCORE);
        var awayScore = sc.nextLine().trim();
        var match = ScoreboardService.updateMatch(index, homeScore, awayScore);
        if (match != null) {
          System.out.println(Constants.UPDATED_MATCH + match);
        } else {
          System.out.println(Constants.BAD_RESULT_FORMAT);
        }
      } else {
        System.out.println(Constants.NOT_FOUND);
      }
    } else {
      System.out.println(Constants.ERROR);
    }
  }

  /**
   * Show matches with sorting
   */
  public static void getSummary() {
    System.out.println(Constants.BANNER);
    ScoreboardService.getSummary();
  }

}