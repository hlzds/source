package src;
public class Circle implements ICircle{
	public double getCircumference (double radius){
		return 2*PI*radius;
	}
	
	public double getArea(double radius){
		return PI*radius*radius;
	}
	
	public double getVolume(double radius){
		return 4*PI*radius*radius*radius/3.0;
	}
}
