package org.sportradar.models;

import java.util.ArrayList;

public class Scoreboard {

  private static Scoreboard instance = null;
  private ArrayList<Match> matches;

  /**
   * Scoreboard constructor. Creates a scoreboard with an empty list of matches.
   */
  private Scoreboard() {
    this.matches = new ArrayList<>();
  }

  /**
   * Only one instance of scoreboard is created, getInstance() get this istance to work with
   *
   * @return Returns he current instance of the scoreboard if exists, if not, returns a new one.
   */
  public static Scoreboard getInstance() {
    if (instance == null) {
      instance = new Scoreboard();
    }
    return instance;
  }

  public ArrayList<Match> getMatches() {
    return matches;
  }

}
