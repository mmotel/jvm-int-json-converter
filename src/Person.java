
public class Person {

	public String name;
	private Integer age;
	
	public Person() {
		this.name = "Mateusz";
		this.age = 25;
	}
	
	public Person(String name, Integer age) {
		this.name = name;
		this.age = age;
	}
	
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
}
