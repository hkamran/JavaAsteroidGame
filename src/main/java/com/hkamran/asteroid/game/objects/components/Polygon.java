package com.hkamran.asteroid.game.objects.components;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * This class represents a polygon in a game.
 * 
 * @author hkamran
 */
public class Polygon {

	private static final Double degreeToRad = Math.PI / 180;
	
	public Double angle = 0.0;
	public Point pos;
	public ArrayList<Point> points = new ArrayList<Point>();
	public Color color;

	/**
	 * Constructor.
	 * @param pos The position of this polygon.
	 * @param color The color of this polygon.
	 */
	public Polygon(Point pos, Color color) {
		this.pos = pos;
		this.color = color;
	}

	/**
	 * Add a point to the shape of this polygon.
	 * @param point A point object that will change the shape of the polygon.
	 */
	public void add(Point point) {
		points.add(point);
	}
	
	/**
	 * Get a direction of where this object is pointing.
	 * @return A point.
	 */
	public Vector getDirection() {
		Vector direction = new Vector(new Point(0, -1));
		direction.rotate(angle);
		return direction;
	}

	/**
	 * Draw the polygon.
	 * @param g A graphics object.
	 */
	public void draw(Graphics g) {
		for (int i = 0; i < points.size(); i = i + 2) {
			Point pt1 = points.get(i);
			Point pt2 = points.get(i + 1);

			g.setColor(color);
			g.drawLine((int) Math.floor(pt1.x), 
					(int) Math.floor(pt1.y), 
					(int) Math.floor(pt2.x), 
					(int) Math.floor(pt2.y));
		}
	}

	/**
	 * Translate the polygon by a given x,y value.
	 * @param xpos Translate the polygon by a given x value.
	 * @param ypos Translate the polygon by a given y value.
	 */
	public void translate(Double xpos, Double ypos) {

		Double cos = Math.cos(0 * degreeToRad);
		Double sin = Math.sin(0 * degreeToRad);

		Double xPosIncrease = (xpos * cos) - (ypos * sin);
		Double yPosIncrease = (xpos * sin) + (ypos * cos);

		this.pos.x += xPosIncrease;
		this.pos.y += yPosIncrease;

		for (Point point : points) {
			point.translate(xPosIncrease, yPosIncrease);
		}
		
	}

	/**
	 * Scale the polygon by a given  value.
	 * @param scale Increase/decrease of size of the polygon.
	 */
	public void scale(Double scale) {
		for (Point point : points) {
			point.scale(scale);
		}
	}

	/**
	 * Rotate the polygon of a given angle (Degree).
	 * @param rot Angle of rotation.
	 */
	public void rotate(Double rot) {
		Point oldPos = this.pos.clone();

		translate(-oldPos.x, -oldPos.y);

		for (Point point : points) {
			point.rotate(rot);
		}

		translate(oldPos.x, oldPos.y);
		this.angle += rot;
	}
	
}