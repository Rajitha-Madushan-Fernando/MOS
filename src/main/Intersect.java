package main;


public class Intersect {
	
	private SmartPhoneData data1;
	private SmartPhoneData data2;
	private IntersectPoints ip;
	private float distance = 0.0f;
	public boolean f = true;
	
	public Intersect(SmartPhoneData spd1, SmartPhoneData spd2) {
		data1 = spd1;
		data2 = spd2;
		
		if(isIntersecting(data1, data2)) {
			
				ip = getIntersection(data1, data2);
			
		}
		else {
			f = false;
		}
	}
	
	public IntersectPoints getPoints() {
		return ip;
	}
	
	public float calcDistance(float x1, float y1, float x2, float y2) {
		
		return (float) Math.sqrt(((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)));
	}
	public float distanceCentreSquare(float x1, float x2, float y1, float y2) {
		
		return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
	}
	
	private boolean isIntersecting(SmartPhoneData spd1, SmartPhoneData spd2) {
		float x1 = spd1.getXCoordinates();
		float y1 = spd1.getYCoordinates();
		float x2 = spd2.getXCoordinates();
		float y2 = spd2.getYCoordinates();
		float r1 = spd1.getRadius();
		float r2 = spd2.getRadius();
		
		distance = (float) Math.sqrt(distanceCentreSquare(x1, x2, y1, y2));
		float d = distance;
		if(d > (r1 + r2)) {
			return false;
		}
		if(d < Math.abs(r1 - r2)) {
			return false;
		}
		else {
			return true;
		}
	}
	
	private IntersectPoints getIntersection(SmartPhoneData spd1, SmartPhoneData spd2) {
		
		float x3,y3;
	
		float x1 = spd1.getXCoordinates();
		float y1 = spd1.getYCoordinates();
		float x2 = spd2.getXCoordinates();
		float y2 = spd2.getYCoordinates();
		float r1 = spd1.getRadius();
		float r2 = spd2.getRadius();
		
		float dx = x2 - x1;
	    float dy = y2 - y1;
	    float d = distance;
	    float a = ((r1 * r1) - (r2 * r2) + (d * d)) / (2 * d);
	    
	    x3 = x1 + (dx * a / d);
	    y3 = y1 + (dy * a / d);
	    
	    float h = (float) Math.sqrt((r1 * r1) - (a * a));
	    
	    float rx = -dy * (h / d);
	    float ry = dx * (h / d);
	    
	    float xi = x3 + rx;
	    float xiPrime = x3 - rx;
	    float yi = y3 + ry;
	    float yiPrime = y3 - ry;
	   
	    return new IntersectPoints(xi, yi, xiPrime, yiPrime);	
	}
}
