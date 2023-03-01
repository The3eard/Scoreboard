package org.sportradar.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.sportradar.models.Match;
import org.sportradar.models.Scoreboard;

public class Utils {

  /**
   * @param teamName String -> Team name
   * @return Returns the team name provided formated
   */
  public static String formatTeamName(String teamName) {
    if (teamName != null) {
      return teamName.trim().toUpperCase();
    }
    return "";
  }

  /**
   * Checks if a team is playing a match.
   *
   * @param scoreboard ArrayList<Match> -> A list of matches
   * @param homeTeam   String -> Home team name
   * @param awayTeam   String -> Away team name
   * @return Returns true if any of the teams are playing, if not returns false.
   */
  public static boolean isTeamPlaying(String homeTeam, String awayTeam) {
    boolean result = false;
    var scoreboard = Scoreboard.getInstance().getMatches();
    if (scoreboard != null && teamsDataIsNotNull(homeTeam, awayTeam)) {
      for (Match match : scoreboard) {
        if (homeTeam.equals(match.getHomeTeam().getName()) || homeTeam.equals(
            match.getAwayTeam().getName()) || awayTeam.equals(match.getHomeTeam().getName())
            || awayTeam.equals(match.getAwayTeam().getName())) {
          result = true;
          break;
        }
      }
    }
    return result;
  }

  /**
   * Checks if the data provided by the user are a valid score.
   *
   * @param homeTeamName String -> Home team name
   * @param awayTeamName String -> Away team name
   * @return Returns true if the data provided is valid, otherwisen false.
   */
  public static boolean areValidNames(String homeTeamName, String awayTeamName) {
    if (teamsDataIsNotNull(homeTeamName, awayTeamName) && !homeTeamName.isEmpty()
        && !awayTeamName.isEmpty()) {
      Pattern pattern = Pattern.compile("^([a-zA-Z]*)$");
      Matcher homeTeamNameMatcher = pattern.matcher(homeTeamName);
      Matcher awayTeamNameMatcher = pattern.matcher(awayTeamName);
      return homeTeamNameMatcher.matches() && awayTeamNameMatcher.matches();
    }
    return false;
  }

  /**
   * Checks if the data provided by the user are a valid score.
   *
   * @param homeScore String -> Home team score
   * @param awayScore String -> Away team score
   * @return Returns true if the data provided is valid, otherwise false.
   */
  public static boolean isValidScore(String homeScore, String awayScore) {
    if (teamsDataIsNotNull(homeScore, awayScore) && !homeScore.isBlank() && !awayScore.isBlank()) {
      Pattern pattern = Pattern.compile("[0-9]*");
      Matcher homeScoreMatcher = pattern.matcher(homeScore);
      Matcher awayScoreMatcher = pattern.matcher(awayScore);
      return homeScoreMatcher.matches() && awayScoreMatcher.matches();
    } else {
      return false;
    }
  }

  /**
   * Checks if the option provided by the user are a valid option.
   *
   * @param option String -> Selected option by the user
   * @return Returns true if the data provided is valid, if not return null.
   */
  public static boolean isValidOption(String option) {
    Pattern pattern = Pattern.compile("^[12345]$");
    Matcher optionMatcher = pattern.matcher(option);
    return optionMatcher.matches();
  }

  /**
   * Checks if the ID provided by the user corresponds to a current match.
   *
   * @param scoreboard ArrayList<Match> -> A list of matches
   * @param id         int -> An ID
   * @return If the match exists, returns its index in the list, if not return -1
   */
  public static int checkIfIdExists(int id) {
    var scoreboard = Scoreboard.getInstance().getMatches();
    for (int index = 0; index < scoreboard.size(); index++) {
      if (scoreboard.get(index).getId() == id) {
        return index;
      }
    }
    return -1;
  }

  /**
   * Checks if the ID provided is a valid ID.
   *
   * @param id int -> An ID
   * @return Returns true if the user provided a valid ID, otherwise false.
   */
  public static boolean isValidId(String id) {
    if (id != null) {
      Pattern pattern = Pattern.compile("[0-9]+");
      Matcher optionMatcher = pattern.matcher(id.trim());
      return optionMatcher.matches();
    } else {
      return false;
    }
  }

  /**
   * Checks if data provided is null
   *
   * @param homeData Object -> Home team data
   * @param awayData Object -> Away team data
   * @return Returns true if any of objects is null
   */
  public static boolean teamsDataIsNotNull(Object homeData, Object awayData) {
    return homeData != null && awayData != null;
  }

}
