package org.sportradar.services;

import org.sportradar.constants.Constants;
import org.sportradar.models.Match;
import org.sportradar.models.Scoreboard;
import org.sportradar.utils.MatchComparator;
import org.sportradar.utils.Utils;

public class ScoreboardService {

  static Scoreboard scoreboard = Scoreboard.getInstance();

  /**
   * Adds a match to the list of matches in the scoreboard if neither of the 2 teams is already
   * playing.
   *
   * @param homeTeam String -> Home team name
   * @param awayTeam String -> Away team name
   * @return Returns the match if it has been added, if not returns null.
   */
  public static Match addMatch(String homeTeam, String awayTeam) {
    if (Utils.areValidNames(homeTeam, awayTeam) && !Utils.isTeamPlaying(homeTeam, awayTeam)) {
      var match = new Match(homeTeam, awayTeam);
      scoreboard.getMatches().add(match);
      return match;
    } else {
      return null;
    }


  }

  /**
   * Removes a match from the matches list in the scoreboard.
   *
   * @param index int -> Position of the match in the scoreboard list
   * @return Returns the match if it has been removed, if not returns null.
   */
  public static Match finishMatch(int index) {
    try {
      var match = scoreboard.getMatches().get(index);
      scoreboard.getMatches().remove(index);
      return match;
    } catch (IndexOutOfBoundsException e) {
      return null;
    }
  }

  /**
   * Updates a match from the list of matches in the scoreboard.
   *
   * @param index     int -> Position of the match in the scoreboard list
   * @param homeScore String -> Home team goals
   * @param awayScore String -> Away team goals
   * @return Returns the match if it has been updated, if not returns null.
   */
  public static Match updateMatch(int index, String homeScore, String awayScore) {
    try {
      if (Utils.isValidScore(homeScore, awayScore)) {
        scoreboard.getMatches().get(index).setHomeScore(Integer.parseInt(homeScore));
        scoreboard.getMatches().get(index).setAwayScore(Integer.parseInt(awayScore));
        return scoreboard.getMatches().get(index);
      } else {
        return null;
      }
    } catch (IndexOutOfBoundsException e) {
      return null;

    }
  }

  /**
   * Displays a summary of the matches sorted by number of goals scored and, secondarily, by the
   * most recent ones.
   */
  public static void getSummary() {
    System.out.println(Constants.LINE_SEPARATOR);
    System.out.println(Constants.HEADER);
    System.out.println(Constants.LINE_SEPARATOR);
    scoreboard.getMatches().sort(new MatchComparator());
    for (int id = 0; id < scoreboard.getMatches().size(); id++) {
      System.out.println(
          Constants.INIT_SPACER + scoreboard.getMatches().get(id).getId() + Constants.MID_SPACER
              + scoreboard.getMatches().get(id).toString());
      System.out.println(Constants.LINE_SEPARATOR);
    }
  }

  /**
   * Introduces the app launching data
   */
  public static void initData() {
    scoreboard.getMatches().clear();
    ScoreboardService.addMatch("MEXICO", "CANADA");
    ScoreboardService.updateMatch(0, "0", "5");
    ScoreboardService.addMatch("SPAIN", "BRAZIL");
    ScoreboardService.updateMatch(1, "10", "2");
    ScoreboardService.addMatch("GERMANY", "FRANCE");
    ScoreboardService.updateMatch(2, "2", "2");
    ScoreboardService.addMatch("URUAGUAY", "ITALY");
    ScoreboardService.updateMatch(3, "6", "6");
    ScoreboardService.addMatch("ARGENTINA", "AUSTRALIA");
    ScoreboardService.updateMatch(4, "3", "1");
  }

}
