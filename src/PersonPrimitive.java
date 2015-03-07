
public class PersonPrimitive {
	
	public String name;
	public char gender;
	private boolean married;
	private int age;
	private float height;
	private double weight;
	private short children; 
	private long iq;
	private byte sth;
	
	public PersonPrimitive() {
		this.name = "Mateusz";
		this.gender = 'M';
		this.married = false;
		this.age = 25;
		this.height = (float) 185;
		this.weight = 90.0;
		this.children = 0;
		this.iq = (long) 135;
		this.sth = 10;
	}
	
	public PersonPrimitive(String name, char gender, boolean married, int age, float height, 
			double weight, short children, long iq, byte sth) {
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
	
	public boolean isMarried() {
		return married;
	}
	public void setMarried(boolean married) {
		this.married = married;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public short getChildren() {
		return children;
	}
	public void setChildren(short children) {
		this.children = children;
	}
	public long getIq() {
		return iq;
	}
	public void setIq(long iq) {
		this.iq = iq;
	}
	public byte getSth() {
		return sth;
	}
	public void setSth(byte sth) {
		this.sth = sth;
	}

}
