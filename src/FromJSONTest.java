import static org.junit.Assert.*;

import org.junit.Test;


public class FromJSONTest {
	
	private FromJSON fromJSON = new FromJSON();

	//@Test
	public void test() {
		
		Person result = (Person) fromJSON.convert("{}", Person.class);

		//fail("Not yet implemented");
	}
	
	@Test
	public void testFindFieldValue() {
		Object result;
		try {
			result = fromJSON.findFieldValue(Person.class.getDeclaredField("name"), "{\"name\":\"Mateusz\"}");
			assertEquals("Mateusz", result);
			result = fromJSON.findFieldValue(Person.class.getDeclaredField("age"), "{\"age\":24}");
			assertEquals(24, result);
			result = fromJSON.findFieldValue(Person.class.getDeclaredField("married"), "{\"married\":true}");
			assertEquals(true, result);
		} catch (NoSuchFieldException | SecurityException e) {
			fail(e.getMessage());
		}
	}

}
