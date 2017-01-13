package move;

import levels.Character;
import levels.Item;
import levels.Level;
import policy.MySokobanPolicy;

public abstract class MoveItem {
	//Data members
	private MySokobanPolicy _policy;
	private Level _level;
	
	//Constructor
	public MoveItem(MySokobanPolicy policy, Level level) {
		this._policy=policy;
		this._level=level;
	}
	
	//Get and set methods
	public MySokobanPolicy get_policy() {
		return _policy;
	}
	
	public Level get_level() {
		return _level;
	}

	public void set_level(Level _level) {
		this._level = _level;
	}
	
	//Abstract method
	/**
	 * The method moves the character
	 * @param character the character that we want to move
	 * @param moveToItem the item that is next to the character
	 * @param nextItem the item that is second next to the character
	 * @return if we moved the character 
	 */
	public abstract boolean moveCharacter(Character character, Item moveToItem, Item nextItem);
	
	/**
	 * The method moves the character right
	 * @param character the character that we want to move
	 * @return if we moved the character 
	 */
	public abstract boolean moveChararcterRight(Character character);
	
	/**
	 * The method moves the character left
	 * @param character the character that we want to move
	 * @return if we moved the character 
	 */
	public abstract boolean moveChararcterLeft(Character character);
	
	/**
	 * The method moves the character up
	 * @param character the character that we want to move
	 * @return if we moved the character 
	 */
	public abstract boolean moveChararcterUp(Character character);
	
	/**
	 * The method moves the character down
	 * @param character the character that we want to move
	 * @return if we moved the character 
	 */
	public abstract boolean moveChararcterDown(Character character);
	
}