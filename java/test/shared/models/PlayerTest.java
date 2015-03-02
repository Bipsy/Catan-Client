package shared.models;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import shared.models.DTO.DevCardListDTO;

public class PlayerTest {

    private Player sam;
    private Player mark;
    private Player brooke;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        sam = new Player("blue", "Sam", 0, 0, "Sam", "sam");
        mark = new Player("red", "Mark", 3, 10, "Mark", "mark");
        brooke = new Player("green", "Brooke", 3, 10, "Brooke", "brooke");

        sam.setOldDevCards(new DevCardList(new DevCardListDTO(1, 1, 1, 1, 1)));
        sam.setPlayedDevCard(false);
        mark.setOldDevCards(new DevCardList(new DevCardListDTO(1, 1, 1, 1, 1)));
        mark.setPlayedDevCard(true);
        brooke.setOldDevCards(new DevCardList(new DevCardListDTO(0, 0, 0, 0, 0)));
        brooke.setPlayedDevCard(false);
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * For our current implementation, monuments can be played regardless of
     * current victory points
     *
     * Sam has the card and has not played a development card yet. Mark has the
     * card, but has played another development card Brooke, does not have the
     * card
     */
    @Test
    public void testCanPlayMonument() {
        assertTrue("A player with a monument card can play it", sam.canPlayMonument());
        assertFalse("A player without a monument card can not play it", brooke.canPlayMonument());
    }

    /**
     * Sam has the card and has not played a development card yet. Mark has the
     * card, but has played another development card Brooke, does not have the
     * card
     */
    @Test
    public void testCanPlayMonopoly() {
        assertTrue("A player with a Monopoly card that has not played another dev card can play it", sam.canPlayMonopoly());
        assertFalse("A player with a Monopoly card that has played another dev card can not play it", mark.canPlayMonopoly());
        assertFalse("A player without  a Monopoly card can not play it", brooke.canPlayMonopoly());
    }

    /**
     * Sam has the card and has not played a development card yet. Mark has the
     * card, but has played another development card (but in this case he can
     * still play the monument card. Brooke, does not have the card
     */
    @Test
    public void testCanPlaySoldier() {
        assertTrue("A player with a Soldier card that has not played another dev card can play it", sam.canPlaySoldier());
        assertFalse("A player with a Soldier card that has played another dev card can not play it can play it", mark.canPlaySoldier());
        assertFalse("A player without a Soldier card can not play it", brooke.canPlaySoldier());
    }

    /**
     * Sam has the card and has not played a development card yet. Mark has the
     * card, but has played another development card Brooke, does not have the
     * card
     */
    @Test
    public void testCanUseRoadBuilding() {
        assertTrue("A player with a road building card that has not played another dev card can play it", sam.canUseRoadBuilding());
        assertFalse("A player with a road building that has played another dev card can not play it card can play it", mark.canUseRoadBuilding());
        assertFalse("A player without a road building card can not play it", brooke.canUseRoadBuilding());
    }

    /**
     * Sam has the card and has not played a development card yet. Mark has the
     * card, but has played another development card Brooke, does not have the
     * card
     */
    @Test
    public void testCanUseYearOfPlenty() {
        assertTrue("A player with a year of plenty card that has not played another dev card can play it", sam.canUseRoadBuilding());
        assertFalse("A player with a year of plenty that has played another dev card can not play it card can play it", mark.canUseRoadBuilding());
        assertFalse("A player without a year of plenty card can not play it", brooke.canUseRoadBuilding());
    }

}
