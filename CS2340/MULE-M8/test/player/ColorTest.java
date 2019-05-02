package player;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ColorTest {

    @Test
    public void StringShouldConvertToColor() {
        assertEquals("Should convert 'Blue' to Color.BLUE", Color.BLUE, Color.toColor("Blue"));
    }
}


