package player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import player.Player;
import player.Race;
import player.Color;
import player.Type;
import map.Map;
import map.MapTile;

public class PlayerCalculationTest {
    private Player player;
    Map map;
    String name;
    Race r;
    Color c;

    @Before
    public void setup() {
        name = "Alan";
        r = Race.HUMAN;
        c = Color.PURPLE;
        player = new Player(name, r, c, null, null, null, null);
    }

    @Test(timeout = 100)
    public void testDefault() {
        assertEquals(name, player.getName());
        assertEquals(r, player.getRace());
        assertEquals(c, player.getColor());

        player.setName("Raymond");
        assertEquals("Raymond", player.getName());

        assertEquals(player.getFood(), 8);
        assertEquals(player.getEnergy(), 4);
        assertEquals(player.getOre(), 0);
    }

    @Test(timeout = 100)
    public void testCalculation() {
        map = new Map(0);
        map.getTile(3, 4).setOwner(player);
        player.addLand(map.getTile(3, 4));
        map.getTile(3, 4).setMule(new Mule(Type.FARM));
        map.getTile(0, 0).setOwner(player);
        player.addLand(map.getTile(0, 0));
        map.getTile(0, 0).setMule(new Mule(Type.ENERGY));
        map.getTile(0, 6).setOwner(player);
        player.addLand(map.getTile(0, 6));
        map.getTile(0, 6).setMule(new Mule(Type.MINE));
	player.calculateProduction();
        assertEquals(player.getFood(), 12);
        assertEquals(player.getEnergy(), 4);
        assertEquals(player.getOre(), 4);
    }
}
