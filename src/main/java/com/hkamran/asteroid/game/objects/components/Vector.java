package com.hkamran.asteroid.game.objects.components;

/**
 * Represents a vector in a game.
 * 
 * @author hkamran
 */
public class Vector {

	private Point pt;
	
	/**
	 * Constructor.
	 * @param x The x value of the vector.
	 * @param y The y value of the vector.
	 */
	public Vector(Double x, Double y) {
		pt = new Point(x, y);
	}
	
	/**
	 * Constructor.
	 * @param pt The point object which the vector ends on (from the origin).
	 */
	public Vector(Point pt) {
		this.pt = pt;
	}
	
	/**
	 * Add the values of a vector to this one.
	 * @param v2 The vector in question.
	 */
	public void add(Vector v2) {
		pt.x += v2.pt.x;
		pt.y += v2.pt.y;
	}
	
	/**
	 * Rotate a vector by a given angle (Degree).
	 * @param angle A angle of which to rotate it.
	 */
	public void rotate(Double angle) {
		pt.rotate(angle);
	}	
	
	/**
	 * Increase/Decrease the magnitude of the vector.
	 * @param scale The value of which to scale from.
	 */
	public void scale(Double scale) {
		pt.scale(scale);
	}
	
	/**
	 * Return the X value of the vector.
	 * @return Double.
	 */
	public Double getX() {
		return pt.x;
	}
	
	/**
	 * Return the X value of the vector.
	 * @return Double.
	 */
	public Double getY() {
		return pt.y;
	}
	
	/**
	 * Set the X value of this vector.
	 * @param x Double.
	 */
	public void setX(Double x) {
		pt.x = x;
	}
	
	/**
	 * Set the Y value of this vector.
	 * @param y Double.
	 */
	public void setY(Double y) {
		pt.y = y;
	}		
	
	/**
	 * Return a new instance of this vector.
	 */
	public Vector clone() {
		return new Vector(this.pt.clone());
	}
}
