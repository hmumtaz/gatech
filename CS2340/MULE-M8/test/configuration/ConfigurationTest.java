package configuration;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ConfigurationTest {

    @Test
    public void ShouldStoreValues() {
        IConfiguration conf = new Configuration();

        conf.set("testfield", 5);
        assertEquals("testfield should have been set.", conf.get("testfield"), 5);
        assertEquals("testfield2 should not have been set.", conf.get("testfield2"), null);

        conf.set("test.field", 5);
        assertEquals("test.field should be nested json.", conf.getJSON().getJSONObject("test").opt("field"), 5);
        assertEquals("test.field2 should not be nested json.", conf.getJSON().getJSONObject("test").opt("field2"), null);
        assertEquals("test.field should have been set.", conf.get("test.field"), 5);
        assertEquals("test.field2 should not have been set.", conf.get("test.field2"), null);

        assertEquals("empty field should be null", conf.get(""), null);
    }
}
