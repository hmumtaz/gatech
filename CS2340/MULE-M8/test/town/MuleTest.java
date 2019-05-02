package town;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import player.Player;
import player.Race;
import player.Color;
import player.Mule;
import timer.Turn;
import town.MuleBuilding;

public class MuleTest {
    MuleBuilding store;
    Player player;
    Turn turn;

    @Before
    public void setup() {
        player = new Player("Raymond", Race.BONZOID, Color.ORANGE, null, null, null, null);
        Player[] players = {player};
        turn = new Turn(players, 0);
        store = new MuleBuilding();
    }

    @Test
    public void testPurchaseWithMule() {
        Mule mule = new Mule();
        player.addMule(mule);
        store.action(turn);
        assertEquals(25, store.quantity);
        assertEquals(1000, player.getMoney());
        assertEquals(mule, player.getMule());
    }

    @Test
    public void testPurchaseCapable() {
        store.action(turn);
        assertEquals(24, store.quantity);
        assertEquals(900, player.getMoney());
        assertNotNull(player.getMule());
    }

    @Test
    public void testPurchaseNoMoney() {
        player.setMoney(0);
        store.action(turn);
        assertEquals(25, store.quantity);
        assertEquals(0, player.getMoney());
        assertNull(player.getMule());
    }
    
    @Test
    public void testPurchaseNoStock() {
	store.quantity = 0;
	store.action(turn);
	assertEquals(0, store.quantity);
	assertEquals(1000, player.getMoney());
	assertNull(player.getMule());
    }
}
