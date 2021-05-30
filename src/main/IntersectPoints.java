package main;

public class IntersectPoints {
	private float x1;
	private float y1;
	private float x2;
	private float y2;
	
	IntersectPoints(float x1, float y1, float x2, float y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public IntersectPoints(float x12, float y12) {
		this.x1 = x12;
		this.y1 = y12;
	}

	public int getX1() {
		return Math.round(x1);
	}
	
	public int getX2() {
		return Math.round(x2);
	}
	
	public int getY1() {
		return Math.round(y1);
	}
	
	public int getY2() {
		return Math.round(y2);
	}
	
}
