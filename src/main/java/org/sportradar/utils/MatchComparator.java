package org.sportradar.utils;

import java.util.Comparator;
import org.sportradar.models.Match;

public class MatchComparator implements Comparator<Match> {

  /**
   * Overriding of the match compare() method
   *
   * @param match1 Match -> the first match to be compared.
   * @param match2 Match -> the second match to be compared.
   * @return Returns 1, 0 or -1 depending on the result of the comparison.
   */
  @Override
  public int compare(Match match1, Match match2) {
    int match1Result = match1.getHomeScore() + match1.getAwayScore();
    int match2Result = match2.getHomeScore() + match2.getAwayScore();
    if (match1Result == match2Result) {
      if (match1.getId() > match2.getId()) {
        return -1;
      } else if (match1.getId() == match2.getId()) {
        return 0;
      } else {
        return 1;
      }
    } else if (match1Result > match2Result) {
      return -1;
    } else {
      return 1;
    }
  }
}
