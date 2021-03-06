package model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.Observable;
import java.util.zip.GZIPOutputStream;

import commons.Level;
import model.data.Character;
import model.policy.MoveItem;
import model.policy.MoveItemPolicy1;

public class SokobanModel extends Observable implements IModel{
	//Data members
	private MoveItem _moveFunctionality;
	private Level _level;
	
	//Constructors
	public SokobanModel() {
		this._moveFunctionality = new MoveItemPolicy1();
	}
	
	public SokobanModel(Level level, MoveItem moveFunctionality) {
		this._level = level;
		this._moveFunctionality = moveFunctionality;
	}
	
	/**
	 * The method compresses the level before we send it to the server
	 * @return OutputStream to send to the server
	 */
	public OutputStream compressLevel(){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		GZIPOutputStream gzipOut;
		try {
			gzipOut = new GZIPOutputStream(baos);
			ObjectOutputStream objectOut = new ObjectOutputStream(gzipOut);
			objectOut.writeObject(_level);
			objectOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return baos;
	}
	
	@Override
	public void moveCharacter(String direction, Character character){
		switch(direction){
		case "Right":
		case "right":
		case "RIGHT":
			this.setLevel(this._moveFunctionality.moveChararcterRight(this._level, character));
			break;
		case "Left":
		case "left":
		case "LEFT":
			this.setLevel(this._moveFunctionality.moveChararcterLeft(this._level, character));
			break;
		case "Up":
		case "up":
		case "UP":
			this.setLevel(this._moveFunctionality.moveChararcterUp(this._level, character));
			break;
		case "Down":
		case "down":
		case "DOWM":
			this.setLevel(this._moveFunctionality.moveChararcterDown(this._level, character));
			break;
		default:
			throw new IllegalArgumentException("Invalid direction");
		}
		
		LinkedList<String> params = new LinkedList<String>();
		params.add("Display");//After we moved the character, we want to display the game with the changes
		this.setChanged();//Set an indication that there was a change
		this.notifyObservers(params);//Notify observers about the change
	}

	@Override
	public Level getLevel() {
		return this._level;
	}

	@Override
	public void setLevel(Level level) {
		this._level = level;
	}
	
}
