package com.hkamran.asteroid.game.objects;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import com.hkamran.asteroid.game.objects.components.Line;
import com.hkamran.asteroid.game.objects.components.Point;
import com.hkamran.asteroid.game.objects.components.Polygon;

/**
 * All game objects extends of this class, that holds common properties throughout all
 * game objects in the game.
 * 
 * @author hkamran
 */
public abstract class GameObject {

	public Polygon poly;
	public ArrayList<KeyListener> listeners = new ArrayList<KeyListener>();
	public static Dimension screenSize;
	public boolean collided = false;
	
	/**
	 * Draw the polygon held in this game object.
	 * @param g Graphics objects.
	 */
	public void draw(Graphics g) {
		poly.draw(g);
	}
	
	/**
	 * Actions calculated during a game tick.
	 */
	public void tick() {
		outOfBoundHandler();
	} 
	
	/**
	 * Handler for when the game object is outside the screen.
	 */
	public void outOfBoundHandler() {
		Point pos = poly.pos;
		
		if (pos.x > screenSize.width) {
			poly.translate(-pos.x, 0.0);
		} else if (pos.x < 0) {
			poly.translate(screenSize.width + 0.0, 0.0);
		}
		
		if (pos.y > screenSize.height) {
			poly.translate(0.0, -pos.y);
		} else if (pos.y < 0) {
			poly.translate(0.0, screenSize.height + 0.0);
		}	
	}	
	
	/**
	 * Determine if this game object is colliding with the one in question.
	 * @param object The game object in question to check for collision 
	 * @return True if this game object is colliding with the game object, otherwise false.
	 */
	public Boolean isColliding(GameObject object) {
		for (int i = 0; i < this.poly.points.size(); i = i + 2) {
			Point p1 = this.poly.points.get(i);
			Point p2 = this.poly.points.get(i + 1);
			Line line1 = new Line(p1, p2);
			
			for (int x = 0; x < object.poly.points.size(); x = x + 2) {
				Point a1 = object.poly.points.get(x);
				Point a2 = object.poly.points.get(x + 1);	
				Line line2 = new Line(a1, a2);
				if (line1.isIntersecting(line2)) {
					if (object.collided == false) {
						object.collided = true;
						return true;
					} else {
						return false;
					}
					
				}
			}
		}
		return false;
	}
	
}
