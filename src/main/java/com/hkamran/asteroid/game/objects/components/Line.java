package com.hkamran.asteroid.game.objects.components;

/**
 * This class represent a line in the game.
 * 
 * @author hkamran
 */
public class Line {

	Point pt1;
	Point pt2;
	Double slope;
	Double b;
	Double tolerance = 0.0;
	
	/**
	 * Constructor.
	 * @param pt1 First point of the line.
	 * @param pt2 Second point of the line.
	 */
	public Line(Point pt1, Point pt2) {
		this.pt1 = pt1;
		this.pt2 = pt2;		
		this.slope = (pt2.y - pt1.y) / (pt2.x - pt1.x);
		this.b = pt1.y - (this.slope * pt1.x); ;
	}

	/**
	 * Return the Y value of this line given an X value.
	 * @param x X value
	 * @return - Double
	 */
	private Double getPoint(Double x) {
		return (slope * x) + b;
	}

	/**
	 * Determine if this line is intersecting another line. 
	 * @param line The line in question.
	 * @return Return true if this line is intersecting the line in question, other false.
	 */
	public Boolean isIntersecting(Line line) {
		// y = mx + b
		Double mx = (line.slope - this.slope);
		Double b = (this.b - line.b);
				
		Double thisMinX = Math.min(pt1.x, pt2.x) - tolerance;
		Double thisMaxX = Math.max(pt1.x, pt2.x) + tolerance;
		
		Double thisMinY = Math.min(pt1.y, pt2.y) - tolerance; 
		Double thisMaxY = Math.max(pt1.y, pt2.y) + tolerance; 
		
		Double lineMinX = Math.min(line.pt1.x, line.pt2.x) - tolerance;
		Double lineMaxX = Math.max(line.pt1.x, line.pt2.x) + tolerance;
		
		Double lineMinY = Math.min(line.pt1.y, line.pt2.y) - tolerance; 
		Double lineMaxY = Math.max(line.pt1.y, line.pt2.y) + tolerance; 
		
		Point point;

		if (Double.isInfinite(this.slope)) {
			//This line is vertical.
			//Assume the intersecting point is this lines x value and the y value of the incoming line.
			//and just check if the range of this intersecting point is valid.
			point = new Point(pt1.x, line.getPoint(pt1.x));
		} else if (Double.isInfinite(line.slope)) {
			//The line we are colliding with is vertical..
			point = new Point(line.pt1.x, this.getPoint(line.pt1.x));
		} else {
			Double xIntersect = b/mx;
			Double yIntersect = getPoint(xIntersect);
			
			point = new Point(xIntersect, yIntersect);
		}
		

		//Check if we are within range of this line
		if ((point.y >= thisMinY && point.y <= thisMaxY) &&
			(point.x >= thisMinX && point.x <= thisMaxX)) {
			
			//Check if we are within range of the incoming line
			if ((point.x >= lineMinX && point.x <= lineMaxX) &&
				(point.y >= lineMinY && point.y <= lineMaxY)) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		Line line1 = new Line(new Point(5, 0), new Point(5, 10));
		Line line2 = new Line(new Point(2, 5), new Point(10, 10));
		
		System.out.println(line1.isIntersecting(line2));
		
	}

	
}
