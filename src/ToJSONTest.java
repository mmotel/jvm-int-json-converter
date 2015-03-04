import static org.junit.Assert.*;

import org.junit.Test;


public class ToJSONTest {
	
	private ToJSON toJSON = new ToJSON();

	@Test
	public void testSimpleProperties() {
		Person p = new Person("Mateusz", 25, false);
		
		try {
			String resultJSON = toJSON.convert(p);
			assertEquals("{ \"name\": \"Mateusz\", \"age\": 25, \"married\": false }" , resultJSON);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
