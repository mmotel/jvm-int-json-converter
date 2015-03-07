import static org.junit.Assert.*;

import org.junit.Test;


public class ToJSONTest {
	
	private ToJSON toJSON = new ToJSON();

	@Test
	public void testSimpleProperties() {
		Person p = new Person("Mateusz", 'M', false, 24, (float) 184, 85.0, (short) 0, (long) 150, (byte) 22);
		
		try {
			String resultJSON = toJSON.convert(p);
			assertEquals("{ \"name\": \"Mateusz\", \"gender\": \"M\", \"married\": false, \"age\": 24,"
					+ " \"height\": 184.0, \"weight\": 85.0, \"children\": 0,"
					+ " \"iq\": 150, \"sth\": 22 }" , resultJSON);
			System.out.println(resultJSON);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testSimplePrimitiveProperties() {
		PersonPrimitive p = new PersonPrimitive("Mateusz", 'M', false, 24, (float) 184, 85.0, (short) 0, (long) 150, (byte) 22);
		
		try {
			String resultJSON = toJSON.convert(p);
			assertEquals("{ \"name\": \"Mateusz\", \"gender\": \"M\", \"married\": false, \"age\": 24,"
					+ " \"height\": 184.0, \"weight\": 85.0, \"children\": 0,"
					+ " \"iq\": 150, \"sth\": 22 }" , resultJSON);
			System.out.println(resultJSON);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
