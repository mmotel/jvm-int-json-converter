import static org.junit.Assert.*;

import org.junit.Test;


public class FromJSONTest {
	
	private FromJSON fromJSON = new FromJSON();

	@Test
	public void testConvertFromEmptyJSON() {
		
		Person result = (Person) fromJSON.convert("{}", Person.class);

		assert(result.name == null);
		assert(result.gender == '\u0000');
		assert(result.getAge() == 0);
		assert(result.getChildren() == 0);
		assert(result.getHeight() == 0);
		assert(result.getIq() == 0);
		assert(result.isMarried() == false);
		assert(result.getSth() == 0);
		assert(result.getWeight() == 0);
	}
	
	@Test
	public void testConvertFromJSON() {
		
		Person result = (Person) fromJSON.convert("{\"name\":\"Mateusz\",\"gender\":\"M\","
				+ "\"married\":true,\"age\":24,\"height\":184.0,\"weight\":85.0,\"children\":1,"
				+ "\"iq\":150,\"sth\":22}", Person.class);

		assert(result.name.compareTo("Mateusz") == 0);
		assert(result.gender.compareTo('M') == 0);
		assert(result.getAge() == 24);
		assert(result.getChildren() == 1);
		assert(result.getHeight() == 184);
		assert(result.getIq() == 150);
		assert(result.isMarried() == true);
		assert(result.getSth() == 22);
		assert(result.getWeight() == 85);
	}
	
	@Test
	public void testConvertPrimitiveFromJSON() {
		
		PersonPrimitive result = (PersonPrimitive) fromJSON.convert("{\"name\":\"Mateusz\",\"gender\":\"M\","
				+ "\"married\":true,\"age\":24,\"height\":184.0,\"weight\":85.0,\"children\":1,"
				+ "\"iq\":150,\"sth\":22}", PersonPrimitive.class);

		assert(result.name.compareTo("Mateusz") == 0);
		assert(result.gender == 0);
		assert(result.getAge() == 24);
		assert(result.getChildren() == 1);
		assert(result.getHeight() == 184);
		assert(result.getIq() == 150);
		assert(result.isMarried() == true);
		assert(result.getSth() == 22);
		assert(result.getWeight() == 85);
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
