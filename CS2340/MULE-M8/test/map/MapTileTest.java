package map;

// Annotations
import org.junit.Before;
import org.junit.Test;

// Assertions
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;


import player.Player;
import player.Race;
import player.Color;
import player.Mule;


public class MapTileTest {

    private Player p;
    private MapTile mt;
    private Mule m, n;

  @Before
  public void setup() {
    p = new Player("Hussain", Race.BONZOID, Color.ORANGE, null, null, null, null);
    mt = new MapTile();
    m = new Mule();
    n = new Mule();
  }

  @Test
  public void getColorTest1() {
    assertNull(mt.getColor());
    mt.setOwner(p);
    assertNotNull(mt.getColor());
  }

  @Test
  public void getColorTest2() {
    assertNull(mt.getColor());
    mt.setOwner(p);
    assertEquals(Color.ORANGE, mt.getColor());
  }

  @Test
  public void setMuleTest1() {
    assertNull(mt.getMule());
  }

  @Test
  public void setMuleTest2() {
    mt.setMule(m);
    assertNotNull(mt.getMule());
    assertEquals(m, mt.getMule());
  }

  @Test
  public void setMuleTest3() {
    mt.setMule(m);
    assertEquals(m, mt.getMule());
    mt.setMule(n);
    assertEquals(n, mt.getMule());
  }
}
