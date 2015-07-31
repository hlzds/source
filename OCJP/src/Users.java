import java.io.Serializable;


public class Users implements Serializable{
	private String name;
	private int age;
	public Users(){}
	public Users(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	@Override
	public String toString() {
		return "Users [name=" + name + ", age=" + age + "]";
	}
	
}
