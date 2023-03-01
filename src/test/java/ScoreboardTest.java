import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.sportradar.constants.Constants;
import org.sportradar.models.Scoreboard;

public class ScoreboardTest {

  @Test
  public void testGetInstance() {
    var scoreboard = Scoreboard.getInstance();
    // Simulate that we are creating the scoreboard from scratch
    if (scoreboard.getMatches().size() > Constants.ZERO) {
      scoreboard.getMatches().clear();
    }
    assertEquals( Constants.ZERO,scoreboard.getMatches().size());
  }

}
