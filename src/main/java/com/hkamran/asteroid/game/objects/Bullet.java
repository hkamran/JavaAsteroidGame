package com.hkamran.asteroid.game.objects;

import java.awt.Color;

import com.hkamran.asteroid.game.objects.components.Point;
import com.hkamran.asteroid.game.objects.components.Polygon;
import com.hkamran.asteroid.game.objects.components.Vector;

/**
 * This game object represents a bullet in the game.
 * 
 * @author hkamran
 */
public class Bullet extends GameObject {

	Color color;
	public static final Double SPEED = 2.0;
	public Integer duration = 150;

	/**
	 * Constructor.
	 */
	public Bullet() {
		this.color = new Color((float) Math.random(), (float) Math.random(),
				(float) Math.random());
		poly = createShape();
	}

	/**
	 * Create a polygon shape that represents a bullet.
	 * @return A polygon object.
	 */
	private Polygon createShape() {
		Polygon poly = new Polygon(new Point(0.0, 0.0), color);
		poly.add(new Point(0.0, 0.0));
		poly.add(new Point(0.0, 5.0));

		return poly;
	}

	/**
	 * Actions calculated during a game tick.
	 */
	public void tick() {
		super.tick();

		Vector direction = poly.getDirection();
		direction.scale(SPEED);

		poly.translate(direction.getX(), direction.getY());
		duration -= 1;

	}

}
