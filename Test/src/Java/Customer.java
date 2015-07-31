package Java;

public class Customer {
	public static void main(String[] args){
		System.out.println("the customer want to but cars");
		Car bmw = CarFactory.getCar("bmw");
		System.out.println("get cars:"+bmw.getInfo());
		System.out.println("the customer want to buy benz");
		Car benz = CarFactory.getCar("benz");
		System.out.println("getcars"+benz.getInfo());
	}
}

