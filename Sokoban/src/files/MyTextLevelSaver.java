package files;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import levels.Item;
import levels.Level;


public class MyTextLevelSaver implements ILevelSaver{

	//The method saves level from text file
	@Override
	public void saveLevel(Level level, String fileName) throws IOException {
		
		FileWriter fw = new FileWriter(fileName);
		BufferedWriter bw = new BufferedWriter(fw);
		
		for(ArrayList<Item> a : level.get_items()){
			for(Item item : a){
				switch(item.getClass().toString().substring(13)){//Get the item type class name
				case "Wall":
					bw.write("#");
					break;
				case "Box":
					bw.write("@");
					break;
				case "Character":
					bw.write("A");
					break;
				case "FinalPosition":
					bw.write("o");
					break;
				case "Floor":
					bw.write(" ");
					break;
				}
			}
			bw.write(System.lineSeparator());//Write new line
		}
		fw.close();
		bw.close();
	}
}
