package com.hkamran.asteroid.game.objects.components;

/**
 * This class represents a single point in the game.
 * 
 * @author hkamran
 */
public class Point {

	public Double x;
	public Double y;
	private static final Double degreeToRad = Math.PI / 180;
	
	/**
	 * Constructor.
	 * @param x The x-position of this point (Double)
	 * @param y The y-position of this point (Double)
	 */
	public Point(Double x, Double y) {
		this.x = x ;
		this.y = y;
	}
	
	/**
	 * Constructor.
	 * @param x The x-position of this point (Integer)
	 * @param y The y-position of this point (Integer)
	 */
	public Point(Integer x, Integer y) {
		this.x = x + 0.0;
		this.y = y + 0.0;
	}
	
	/**
	 * Return a new instance containing the same values.
	 */
	public Point clone() {
		return new Point(x, y);
	}
	
	/**
	 * Return a string containing the x, and y values of this point.
	 */
	public String toString() {
		return "X: " + x + ", " + "Y: " + y;
	}
	
	/**
	 * Rotate a point given an angle (Degree)
	 * Assumption all rotation is done at 0,0
	 * @param rot degree of rotation
	 */
	public void rotate(Double rot) {
		Double cos = Math.cos(rot * degreeToRad);
		Double sin = Math.sin(rot * degreeToRad);

		Double newXpos = (x * cos) - (y * sin);
		Double newYpos = (x * sin) + (y * cos);			
		
		x = newXpos;
		y = newYpos;
	}
	
	/**
	 * Translate the point
	 * @param xPosIncrease The x value to translate the point from.
	 * @param yPosIncrease The y value to translate the point from.
	 */
	public void translate(Double xPosIncrease, Double yPosIncrease) {
		x = (x + xPosIncrease);
		y = (y + yPosIncrease);
	}
	
	/**
	 * Scale the point by a given value
	 * @param scale A value to scale from.
	 */
	public void scale(Double scale) {
		x = x * scale;
		y = y * scale;
	}

}
