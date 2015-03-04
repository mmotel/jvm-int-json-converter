
public class Person {

	public String name;
	private Integer age;
	private Boolean married;
	
	public Person() {
		this.name = "Mateusz";
		this.age = 25;
		this.married = false;
	}
	
	public Person(String name, Integer age, Boolean married) {
		this.name = name;
		this.age = age;
		this.married = married;
	}
	
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Boolean isMarried() {
		return married;
	}

	public void setMarried(Boolean married) {
		this.married = married;
	}
}
