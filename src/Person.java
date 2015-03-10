
public class Person {

	public String name;
	public Character gender;
	private Boolean married;
	private Integer age;
	private Float height;
	private Double weight;
	private Short children; 
	private Long iq;
	private Byte sth;
	
	public Person() {}
	
	public Person(String name, Character gender, Boolean married, Integer age, Float height, 
			Double weight, Short children, Long iq, Byte sth) {
		this.name = name;
		this.gender = gender;
		this.married = married;
		this.age = age;
		this.height = height;
		this.weight = weight;
		this.children =children;
		this.iq = iq;
		this.sth = sth;
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
	public Float getHeight() {
		return height;
	}

	public void setHeight(Float height) {
		this.height = height;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Short getChildren() {
		return children;
	}

	public void setChildren(Short children) {
		this.children = children;
	}

	public Long getIq() {
		return iq;
	}

	public void setIq(Long iq) {
		this.iq = iq;
	}

	public Byte getSth() {
		return sth;
	}

	public void setSth(Byte sth) {
		this.sth = sth;
	}
}
