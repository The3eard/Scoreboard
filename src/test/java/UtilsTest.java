import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.sportradar.models.Scoreboard;
import org.sportradar.services.ScoreboardService;
import org.sportradar.utils.Utils;

public class UtilsTest {

  Scoreboard scoreboard = Scoreboard.getInstance();

  @Test
  public void testFormatTeamName() {
    assertEquals("SPAIN", Utils.formatTeamName(" Spain "));
  }

  @Test
  public void testFormatTeamNameWithBadParams() {
    assertTrue(Utils.formatTeamName(null).isEmpty());
    assertTrue(Utils.formatTeamName("     ").isEmpty());
  }

  @Test
  public void testIsTeamPlaying() {
    ScoreboardService.initData();
    assertTrue(Utils.isTeamPlaying("URUGUAY", "ITALY"));
    assertFalse(Utils.isTeamPlaying("JAPAN", "PORTUGAL"));
  }

  @Test
  public void testIsTeamPlayingWithBadParams() {
    assertFalse(Utils.isTeamPlaying(null, "ITALY"));
    assertFalse(Utils.isTeamPlaying("", ""));
  }

  @Test
  public void testAreValidNames() {
    assertTrue(Utils.areValidNames("Spain", "Portugal"));
  }

  @Test
  public void testAreValidNamesWithBadParams() {
    assertFalse(Utils.areValidNames("", ""));
    assertFalse(Utils.areValidNames("@", "?"));
    assertFalse(Utils.areValidNames(null, null));
  }

  @Test
  public void testIsValidScore() {
    assertTrue(Utils.isValidScore("1", "03"));
  }

  @Test
  public void testIsValidScoreWithBadParams() {
    assertFalse(Utils.isValidScore("", ""));
    assertFalse(Utils.isValidScore("@", "?"));
    assertFalse(Utils.isValidScore(null, null));
    assertFalse(Utils.isValidScore(" a ", "win"));

  }

  @Test
  public void testIsValidOption() {
    assertTrue(Utils.isValidOption("1"));
    assertTrue(Utils.isValidOption("2"));
    assertTrue(Utils.isValidOption("3"));
    assertTrue(Utils.isValidOption("4"));
    assertTrue(Utils.isValidOption("5"));
  }

  @Test
  public void testIsValidOptionWithBadParams() {
    assertFalse(Utils.isValidOption("0"));
    assertFalse(Utils.isValidOption("01"));
    assertFalse(Utils.isValidOption("6"));
    assertFalse(Utils.isValidOption("a"));
    assertFalse(Utils.isValidOption("!"));
  }

  @Test
  public void testCheckIfIdExists() {
    ScoreboardService.initData();
    var testId = scoreboard.getMatches().get(0).getId();
    assertTrue(Utils.checkIfIdExists(testId) > -1);
  }

  @Test
  public void testCheckIfIdExistsWithBadParams() {
    ScoreboardService.initData();
    assertEquals(-1, Utils.checkIfIdExists(5000));
    assertEquals(-1, Utils.checkIfIdExists(-1));
  }

  @Test
  public void testIsValidId() {
    assertTrue(Utils.isValidId("1"));
    assertTrue(Utils.isValidId("01"));
  }

  @Test
  public void testIsValidIdWithBadParams() {
    assertFalse(Utils.isValidId("-1"));
    assertFalse(Utils.isValidId("Hello"));
    assertFalse(Utils.isValidId(""));
    assertFalse(Utils.isValidId("@"));
    assertFalse(Utils.isValidId("   "));
    assertFalse(Utils.isValidId("\n"));

  }

  @Test
  public void testTeamsDataIsNotNull() {
    assertTrue(Utils.teamsDataIsNotNull(1, "a"));
    assertFalse(Utils.teamsDataIsNotNull(null, "a"));
  }

}
