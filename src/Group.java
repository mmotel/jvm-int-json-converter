import java.util.List;
import java.util.ArrayList;


public class Group {

	private List<String> names = new ArrayList<String>();
	private List<Person> members = new ArrayList<Person>();
	
	Group () {
		Person p = new Person("Mateusz", 'M', false, 24, (float) 184, 85.0, (short) 0, (long) 150, (byte) 22);
		this.members.add(p);
		this.names.add("group");
	}
	
	Group (List<Person> members, List<String> names) {
		this.members = members;
		this.names = names;
	}

	public List<Person> getMembers() {
		return members;
	}

	public void setMembers(List<Person> members) {
		this.members = members;
	}

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}
	
}
