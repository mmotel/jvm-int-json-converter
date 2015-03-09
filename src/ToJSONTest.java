import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.ArrayList;

import org.junit.Test;

public class ToJSONTest {
	
	private ToJSON toJSON = new ToJSON();

	@Test
	public void testSimpleProperties() {
		Person p = new Person("Mateusz", 'M', false, 24, (float) 184, 85.0, (short) 0, (long) 150, (byte) 22);
		
		try {
			String resultJSON = toJSON.convert(p);
			assertEquals("{\"name\":\"Mateusz\",\"gender\":\"M\",\"married\":false,\"age\":24,"
					+ "\"height\":184.0,\"weight\":85.0,\"children\":0,"
					+ "\"iq\":150,\"sth\":22}" , resultJSON);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testSimplePrimitiveProperties() {
		PersonPrimitive p = new PersonPrimitive("Mateusz", 'M', false, 24, (float) 184, 85.0, (short) 0, (long) 150, (byte) 22);
		
		try {
			String resultJSON = toJSON.convert(p);
			assertEquals("{\"name\":\"Mateusz\",\"gender\":\"M\",\"married\":false,\"age\":24,"
					+ "\"height\":184.0,\"weight\":85.0,\"children\":0,"
					+ "\"iq\":150,\"sth\":22}" , resultJSON);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testCollectionProperties() {
		Person p = new Person("Mateusz", 'M', false, 24, (float) 184, 85.0, (short) 0, (long) 150, (byte) 22);
		List<Person> members = new ArrayList<Person>();
		members.add(p);
		List<String> names = new ArrayList<String>();
		names.add("group");
		Group g = new Group(members, names);
		
		try {
			String resultJSON = toJSON.convert(g);
			//System.out.println(resultJSON);
			assertEquals("{\"names\":[\"group\"],\"members\":["
					+ "{\"name\":\"Mateusz\",\"gender\":\"M\",\"married\":false,\"age\":24,"
					+ "\"height\":184.0,\"weight\":85.0,\"children\":0,"
					+ "\"iq\":150,\"sth\":22}]}", resultJSON);
		} catch (InvocationTargetException e) {
			fail(e.getMessage());
		}
	}

}
