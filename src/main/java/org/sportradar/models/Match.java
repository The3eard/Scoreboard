package org.sportradar.models;

import java.util.concurrent.atomic.AtomicInteger;
import org.sportradar.constants.Constants;

public class Match {

  private static final AtomicInteger count = new AtomicInteger(0);
  private int id;
  private final Team homeTeam;
  private int homeScore;
  private final Team awayTeam;
  private int awayScore;

  /**
   * Match constructor. Creates a match with a score of 0 - 0 and adds an ID to it.
   *
   * @param homeTeam String -> Home team name
   * @param awayTeam String -> Away team name
   */
  public Match(String homeTeam, String awayTeam) {
    this.id = count.incrementAndGet();
    this.homeTeam = new Team(homeTeam);
    this.awayTeam = new Team(awayTeam);
    this.awayScore = Constants.ZERO;
    this.homeScore = Constants.ZERO;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Team getHomeTeam() {
    return homeTeam;
  }

  public int getHomeScore() {
    return homeScore;
  }

  public void setHomeScore(int homeScore) {
    this.homeScore = homeScore;
  }

  public Team getAwayTeam() {
    return awayTeam;
  }

  public int getAwayScore() {
    return awayScore;
  }

  public void setAwayScore(int awayScore) {
    this.awayScore = awayScore;
  }

  /**
   * Overriding of the match toString() method
   *
   * @return String -> HomeTeamName HomeTeamScore - AwayTeamScore AwayTeamName
   */
  @Override
  public String toString() {
    return homeTeam.getName() + Constants.SPACE + homeScore + Constants.SEPARATOR + awayScore
        + Constants.SPACE + awayTeam.getName();
  }

}
