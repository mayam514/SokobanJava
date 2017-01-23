package model.policy;

import commons.Level;
import model.data.Box;
import model.data.FinalPosition;
import model.data.Floor;
import model.data.Item;
import model.data.Position;
import model.data.Character;

public class MoveItemPolicy1 extends MoveItem{
	//Constructor
	public MoveItemPolicy1(Level level) {
		super(new Policy1(), level);
	}
	
	
	//Override methods
	@Override
	public boolean moveCharacter(Character character, Item moveToItem, Item nextItem) {
		//Check if the character can move
		if(!this.get_policy().canCharacterMove(moveToItem, nextItem)){
			return false;
		}
	
		
		Item characterReplace;//Saves the place of the character that should be replace
		
		//Check what needs to be in the items array instead of the characters
		if(character.isItemOnFinalPosition()){
			characterReplace = new FinalPosition(character.get_position());
		}
		else{
			characterReplace=new Floor(character.get_position());
		}
		this.get_level().setItemInItemsArray(characterReplace, character.get_position());//Set the new item instead of the character in the items array
		
		//Move character to the next position
		character.set_isItemOnFinalPosition(moveToItem.isItemOnFinalPosition());//Set isItemOnFinalPosition for the new position of the character
		character.set_position(moveToItem.get_position());
		this.get_level().setItemInItemsArray(character, character.get_position());//Set the character with the new position in the items array
		
		//Move the character's next two items 
		if(moveToItem.isMoveable()){//If the item near the character is movable
			if(moveToItem instanceof Box){
				if(moveToItem.isItemOnFinalPosition()){
					if(!(nextItem instanceof FinalPosition)){//If the item second nearest to the character is not final position
						this.get_level().set_numOfBoxesOnFinalPositions(this.get_level().get_numOfBoxesOnFinalPositions()-1);//Decrease the number of boxes on final position by one
					}
				}
				else{//Box isn't on final position
					if(nextItem instanceof FinalPosition){//If the item second nearest to the character is final position
						this.get_level().set_numOfBoxesOnFinalPositions(this.get_level().get_numOfBoxesOnFinalPositions()+1);//Increase the number of boxes on final position by one
					}
				}
			}
			moveToItem.set_isItemOnFinalPosition(nextItem.isItemOnFinalPosition());//Set isItemOnFinalPosition for the new position of moveToItem
			moveToItem.set_position(nextItem.get_position());
			this.get_level().setItemInItemsArray(moveToItem, moveToItem.get_position());//Set moveToItem with the new position in the items array
		}
		return true;
	}

	@Override
	public Level moveChararcterRight(Character character) {
		Position p1 = new Position(character.get_position().getP_x()+1, character.get_position().getP_y());//The position on the right to the character
		Item i = this.get_level().getItemFromArrayByPosition(p1);
		Position p2 = new Position(character.get_position().getP_x()+2, character.get_position().getP_y());//The position on the right of i
		Item i1 = this.get_level().getItemFromArrayByPosition(p2);
		if(i1!=null){//i must be in the _items array(not null), but i1 might not
			if(this.moveCharacter(character, i, i1)){//If the character can move right
				this.get_level().setLevel_numOfMoves(this.get_level().getLevel_numOfMoves()+1);//Increase numOfMoves by one
				this.get_policy().isWinner(this.get_level());//Check if the user won the game
				//return true;
			}
		}
		System.out.println("in move item");
		return this.get_level();
	}

	@Override
	public Level moveChararcterLeft(Character character) {
		Position p1 = new Position(character.get_position().getP_x()-1, character.get_position().getP_y());//The position on the left to the character
		Item i = this.get_level().getItemFromArrayByPosition(p1);
		Position p2 = new Position(character.get_position().getP_x()-2, character.get_position().getP_y());//The position on the left of i
		Item i1 = this.get_level().getItemFromArrayByPosition(p2);
		if(i1!=null){//i must be in the _items array(not null), but i1 might not
			if(this.moveCharacter(character, i, i1)){//If the character can move left
				this.get_level().setLevel_numOfMoves(this.get_level().getLevel_numOfMoves()+1);//Increase numOfMoves by one
				this.get_policy().isWinner(this.get_level());//Check if the user won the game
				//return true;
			}
		}
		return this.get_level();
	}

	@Override
	public Level moveChararcterUp(Character character) {
		Position p1 = new Position(character.get_position().getP_x(), character.get_position().getP_y()-1);//The position up to the character
		Item i = this.get_level().getItemFromArrayByPosition(p1);
		Position p2 = new Position(character.get_position().getP_x(), character.get_position().getP_y()-2);//The position up to i
		Item i1 = this.get_level().getItemFromArrayByPosition(p2);
		if(i1!=null){//i must be in the _items array(not null), but i1 might not
			if(this.moveCharacter(character, i, i1)){//If the character can move up
				this.get_level().setLevel_numOfMoves(this.get_level().getLevel_numOfMoves()+1);//Increase numOfMoves by one
				this.get_policy().isWinner(this.get_level());//Check if the user won the game
				//return true;
			}
		}
		return this.get_level();
	}

	@Override
	public Level moveChararcterDown(Character character) {
		Position p1 = new Position(character.get_position().getP_x(), character.get_position().getP_y()+1);//The position down to the character
		Item i = this.get_level().getItemFromArrayByPosition(p1);
		Position p2 = new Position(character.get_position().getP_x(), character.get_position().getP_y()+2);//The position down to i
		Item i1 = this.get_level().getItemFromArrayByPosition(p2);
		if(i1!=null){//i must be in the _items array(not null), but i1 might not
			if(this.moveCharacter(character, i, i1)){//If the character can move down
				this.get_level().setLevel_numOfMoves(this.get_level().getLevel_numOfMoves()+1);//Increase numOfMoves by one
				this.get_policy().isWinner(this.get_level());//Check if the user won the game
				//return true;
			}
		}
		return this.get_level();
	}
}
