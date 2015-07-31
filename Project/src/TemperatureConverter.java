import java.util.Scanner;


public class TemperatureConverter {
	public double toFahrenheit(double centigrade){
		double fahrenheit = 1.8 * centigrade + 32;
		return fahrenheit;
	}

	
	public static void main(String[] args) {
		System.out.println("input the temperature:");// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		double centigrade = in.nextDouble();
		TemperatureConverter tc = new TemperatureConverter();//创建类的对象
		double fahrenheit = tc.toFahrenheit(centigrade);
		System.out.println("after converter:"+ fahrenheit);
	}

}
