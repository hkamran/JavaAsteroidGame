package com.hkamran.asteroid.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComponent;

import com.hkamran.asteroid.game.listeners.KeyboardListener;
import com.hkamran.asteroid.game.objects.Asteriod;
import com.hkamran.asteroid.game.objects.Bullet;
import com.hkamran.asteroid.game.objects.GameObject;
import com.hkamran.asteroid.game.objects.Player;
import com.hkamran.asteroid.game.objects.components.Point;

/**
 * This class is tasked with managing all the game objects.
 * 
 * @author hkamran
 */
public class Game extends JComponent {

	private static final long serialVersionUID = 1L;

	public Dimension screenSize;
	
	private Player player;
	private ArrayList<Asteriod> asteroids = new ArrayList<Asteriod>();
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private Integer score = 0;
	private static Integer highscore = 0;
	
	public Boolean isGameOver = false;
	public KeyboardListener keyboard;

	/**
	 * Constructor.
	 * @param screenSize screensize of the window.
	 */
	public Game(Dimension screenSize) {
		this.screenSize = screenSize;
		this.setPreferredSize(screenSize);
		GameObject.screenSize = screenSize;

		// Add Player
		keyboard = new KeyboardListener();
		player = new Player(new Point(screenSize.getWidth() / 2.0,
				screenSize.getHeight() / 2.0), keyboard);
		this.addKeyListener(keyboard);

		asteroids.add(new Asteriod(new Point(50.0, 50.0), 10.0));
		asteroids.add(new Asteriod(new Point(180.0, 150.0), 10.0));
	}

	/**
	 * Draw all objects in the game.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		//Draw black background.
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.BLACK);
		g.fillRect(1, 1, (int) Math.floor(this.getWidth()) - 2, (int) Math.floor(this.getHeight()) - 2);

		player.draw(g);

		//TODO: Stop concurrent modification

		//Draw bullets.
		for (GameObject bullet : bullets) {
			bullet.draw(g);
		}
		
		//Draw asteroids.
		for (GameObject asteriod : asteroids) {
			asteriod.draw(g);
		}
		
		//Draw Scores.
		g.setColor(Color.WHITE);
		g.drawString("HighScore: " + highscore, this.getWidth() - 100, 30);
		g.drawString("Score: " + score, 20, 30);
		
		
		if (isGameOver) {
			//Draw Game over sign.
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.PLAIN, 24)); 
			g.drawString("GAME OVER", this.getWidth()/2 - 80, this.getHeight()/2);
			
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.PLAIN, 12)); 
			g.drawString("(Press Space to reset)", this.getWidth()/2 - 70, this.getHeight()/2 + 20);
		}
	}

	/**
	 * Actions calculated during a game tick.
	 */
	public void tick() {
		
		if (!isGameOver) {
			player.tick();
		}
		if (score > highscore) {
			highscore = score;
		}
		
		ArrayList<Bullet> newBullets = player.bullets;
		for (Bullet bullet : newBullets) {
			bullets.add(bullet);
		}
		player.bullets = new ArrayList<Bullet>();
		
		ArrayList<Bullet> oldBullets = new ArrayList<>();
		ArrayList<Asteriod> destroyedAsteroid = new ArrayList<>();

		//Manage bullets.
		for (Bullet bullet : bullets) {
			for (Asteriod asteroid : asteroids) {
				Boolean result = bullet.isColliding(asteroid);
				if (result) {
					destroyedAsteroid.add(asteroid);
					oldBullets.add(bullet);
				}
			}
			bullet.tick();
			if (bullet.duration <= 0) {
				oldBullets.add(bullet);
			}
		}
       
		//Remove any old bullets
		for (Bullet bullet : oldBullets) {
			bullets.remove(bullet);
		}

		ArrayList<Asteriod> newAsteroids = new ArrayList<>();
		
		//Remove destroyed asteroids and add new smaller asteroids.
		for (Asteriod asteroid : destroyedAsteroid) {
			score += 25;
			asteroid.dies();
			newAsteroids.addAll(asteroid.childrens);
			asteroids.remove(asteroid);
		}
		asteroids.addAll(newAsteroids);
		
		//Manage asteroids.
		for (GameObject asteriod : asteroids) {
			asteriod.tick();
			if (asteriod.isColliding(player)) {
				isGameOver = true;
				player.poly.points = new ArrayList<>();
			}
		}
	}
}