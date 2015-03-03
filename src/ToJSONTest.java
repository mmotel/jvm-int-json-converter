import static org.junit.Assert.*;

import org.junit.Test;


public class ToJSONTest {
	
	private ToJSON toJSON = new ToJSON();

	@Test
	public void testSimpleProperties() {
		Person p = new Person("Mateusz", 25);
		
		try {
			assertEquals("{ \"name\": Mateusz, \"age\": 25 }" ,toJSON.SimpleProperites(p));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
