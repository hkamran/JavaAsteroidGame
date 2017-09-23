package com.hkamran.asteroid.game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import com.hkamran.asteroid.game.controls.Action;

public class Main {
	
	/**
	 * Entry point of the game.
	 */
	public static void main(String[] args) throws InterruptedException {

		JFrame frame = new JFrame("Java Asteroid Game");
		frame.setSize(new Dimension(500, 500));
		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(screenDimension.width/2-frame.getSize().width/2, screenDimension.height/2-frame.getSize().height/2);
		
		// Add Game
		Game game = new Game(new Dimension(500, 500));

		frame.getContentPane().add(game, BorderLayout.CENTER);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		game.setFocusable(true);
		game.requestFocusInWindow();

		while (true) {
			game.repaint();
			game.tick();
			
			if (game.isGameOver) {
				if (game.keyboard.pressedKeys.contains(Action.space)) {
					frame.remove(game);
					game = new Game(new Dimension(500, 500));
					
					frame.getContentPane().add(game, BorderLayout.CENTER);
					frame.setResizable(false);
					frame.setVisible(true);
					
					game.setFocusable(true);
					game.requestFocusInWindow();
				}
			}

			Thread.sleep(10);
		}
	}
}
