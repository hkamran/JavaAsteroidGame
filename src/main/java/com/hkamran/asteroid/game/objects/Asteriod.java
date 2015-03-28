package com.hkamran.asteroid.game.objects;

import java.awt.Color;
import java.util.ArrayList;

import com.hkamran.asteroid.game.objects.components.Point;
import com.hkamran.asteroid.game.objects.components.Polygon;

/**
 * This class represents an asteroid in the game.
 * 
 * @author hkamran
 */
public class Asteriod extends GameObject {

	private Color color;
	
	private Double rotationSpeed;
	private Double angle;
	private Double scale;
	private static final Integer NUMOFCHILDREN = 2;
	private static final Double SPEED = 2.0;
	
	public ArrayList<Asteriod> childrens = new ArrayList<>();

	/**
	 * Constructor.
	 * @param pos Position of the asteroid.
	 * @param scale The scale of the asteroid.
	 * @param game The Game object.
	 */
	public Asteriod(Point pos, Double scale) {
		this.color = new Color((float) Math.random(), (float) Math.random(), (float) Math.random());
		poly = createShape();

		angle = Math.random() * 360;
		rotationSpeed = Math.random();
		poly.scale(scale);
		this.scale = scale;

		poly.translate(pos.x, pos.y);
	}

	/**
	 * Create the polygon shape of the asteroid.
	 * @return A polygon object that represents the asteroid.
	 */
	private Polygon createShape() {
		Polygon poly = new Polygon(new Point(0.0, 0.0), color);
		poly.add(new Point(0.0, 0.0));
		poly.add(new Point(5.0, 1.0));
		poly.add(new Point(5.0, 1.0));
		poly.add(new Point(6.0, 5.0));
		poly.add(new Point(6.0, 5.0));
		poly.add(new Point(-1.0, 10.0));
		poly.add(new Point(-1.0, 10.0));
		poly.add(new Point(0.0, 0.0));
		return poly;
	}

	/**
	 * Actions calculated during a game tick.
	 */
	public void tick() {
		super.tick();
		poly.rotate(rotationSpeed);
		
		Point point = new Point(0, -1);
		point.rotate(angle);
		point.scale(SPEED);
		
		poly.translate(point.x, point.y);
	}

	/**
	 * Actions done when this asteroid gets destroyed.
	 */
	public void dies() {
		
		//Add Children asteroids.
		if (this.scale / 2 > 0.5) {
			for (int i = 0; i < NUMOFCHILDREN; i++) {
				Asteriod asteriod = new Asteriod(new Point(poly.pos.x, poly.pos.y), this.scale / 2);
				childrens.add(asteriod);
			}
		}

	}
}
