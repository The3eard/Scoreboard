import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import org.junit.Test;
import org.sportradar.models.Match;
import org.sportradar.models.Scoreboard;
import org.sportradar.services.ScoreboardService;

public class ScoreboardServiceTest {

  Scoreboard scoreboard = Scoreboard.getInstance();

  @Test
  public void testAddMatch() {
    scoreboard.getMatches().clear();
    var testMatch = ScoreboardService.addMatch("Spain", "USA");
    assertNotNull(testMatch);
    ScoreboardService.addMatch("England", "France");
    assertEquals(
        1,
        scoreboard.getMatches().stream().filter(match -> match.equals(testMatch)).toList().size());
  }

  @Test
  public void testAddMatchWithBadParams() {
    scoreboard.getMatches().clear();
    var testMatch_1 = ScoreboardService.addMatch("1", "!");
    var testMatch_2 = ScoreboardService.addMatch(null, " ");
    var testMatch_3 = ScoreboardService.addMatch("", "\n");
    assertNull(testMatch_1);
    assertNull(testMatch_2);
    assertNull(testMatch_3);
  }

  @Test
  public void testFinishMatch() {
    scoreboard.getMatches().clear();
    var testMatch = ScoreboardService.addMatch("Brazil", "Argentina");
    var finishedMatch = ScoreboardService.finishMatch(0);
    assertEquals(finishedMatch, testMatch);
  }

  @Test
  public void testFinishMatchWithBadParams() {
    scoreboard.getMatches().clear();
    ScoreboardService.addMatch("Belgium", "Netherlands");
    var finishedMatch = ScoreboardService.finishMatch(5);
    assertNull(finishedMatch);
  }

  @Test
  public void testUpdateMatch() {
    scoreboard.getMatches().clear();
    ScoreboardService.addMatch("Mexico", "Uruguay");
    var testMatch = ScoreboardService.updateMatch(0, "2", "0");
    assertEquals(2, scoreboard.getMatches().get(0).getHomeScore());
    assertEquals(0, scoreboard.getMatches().get(0).getAwayScore());
  }

  @Test
  public void testUpdateMatchWithBadParams() {
    scoreboard.getMatches().clear();
    ScoreboardService.addMatch("England", "France");
    assertNull(ScoreboardService.updateMatch(1, "1", "0"));
    assertNull(ScoreboardService.updateMatch(0, null, "0"));
    assertNull(ScoreboardService.updateMatch(0, "\n", ""));
  }

  @Test
  public void testGetSummary() {
    scoreboard.getMatches().clear();
    ScoreboardService.initData();
    var testScoreboard = (ArrayList<Match>) scoreboard.getMatches().clone();
    ScoreboardService.getSummary();
    assertEquals(testScoreboard.get(3).getId(), scoreboard.getMatches().get(0).getId());
    assertEquals(testScoreboard.get(1).getId(), scoreboard.getMatches().get(1).getId());
    assertEquals(testScoreboard.get(0).getId(), scoreboard.getMatches().get(2).getId());
    assertEquals(testScoreboard.get(4).getId(), scoreboard.getMatches().get(3).getId());
    assertEquals(testScoreboard.get(2).getId(), scoreboard.getMatches().get(4).getId());
  }


}
