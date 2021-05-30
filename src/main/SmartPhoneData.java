package main;

public class SmartPhoneData {
	
	private float xCoordinate;
	private float yCoordinate;
	private float radius;
	
	SmartPhoneData(float xCoordinate, float yCoordinate, float radius) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.radius = radius;
	}
	
	float getXCoordinates() {
		return xCoordinate;
	}
	
	float getYCoordinates() {
		return yCoordinate;
	}
	
	float getRadius() {
		return radius;
	}
	
	float getDiameter() {
		return radius*2;
	}

	
}
