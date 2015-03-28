package com.hkamran.asteroid.game.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

import com.hkamran.asteroid.game.controls.Action;

/**
 * This class is tasked with maintaining a list of 
 * pressed keyboard buttons.
 * 
 * @author hkamran
 */
public class KeyboardListener implements KeyListener {

	public Set<Action> pressedKeys = new HashSet<Action>();
	
	
	/**
	 * When a pressed key event is triggered
	 * add the Keycode to a list.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		Action code = Action.getType(e.getKeyCode());
		System.out.println(e.getKeyCode());
		if (code != null) {
			pressedKeys.add(code);
		}
		
	}

	/**
	 * When a released key event is triggered
	 * remove the Keycode to a list.
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		Action code = Action.getType(e.getKeyCode());
		if (code != null) {
			pressedKeys.remove(code);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
}
