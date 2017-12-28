package com.hkamran.asteroid.game.controls;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The purpose of this enum to is to map keycodes to actions within the game.
 * 
 * @author hkamran
 */
public enum Action {
	left(65,37), right(68,39), up(87,38), down(83,40), space(32), enter(10), increase(107, 61), decrease(109, 45);
	
	public ArrayList<Integer> keycodes = new ArrayList<>();
	
	/**
	 * Constructor
	 * @param keycodes A list of keycodes
	 */
	Action(Integer... keycodes) {
		this.keycodes = new ArrayList<Integer>(Arrays.asList(keycodes));
	}

	/**
	 * Determine if a certain keycode is contained in our list.
	 * @param code Keycode in question.
	 * @return True if the keyboard in question is in the list, otherwise false.
	 */
	public boolean isType(Integer code) {
		return keycodes.contains(code);
	}
	
	/**
	 * From a keycode determine the type of action that is being done.
	 * @param value Keycode in question.
	 * @return Return an action type.
	 */
	public static Action getType(Integer value) {
		for (Action code : Action.values()) {
			if (code.keycodes.contains(value)){
				return code;
			}
		}
		return null;
	}
}