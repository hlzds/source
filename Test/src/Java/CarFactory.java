package Java;

public class CarFactory {
	public static Car getCar(String name){
		if(name.equalsIgnoreCase("bmw")){
			return new BMW();
		}else if(name.equalsIgnoreCase("benz")){
			return new Bens();
		}else{
			return null;
		}
	}
}
