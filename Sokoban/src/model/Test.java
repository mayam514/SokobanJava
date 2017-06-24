package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;

import org.apache.commons.io.FilenameUtils;

import commons.Level;
import model.data.ILevelLoader;
import model.data.MyObjectLevelLoader;
import model.data.MyTextLevelLoader;

public class Test {
	private static HashMap <String, ILevelLoader> _loadersMap;

	private static void init(){
		_loadersMap = new HashMap <String, ILevelLoader>();
		_loadersMap.put("txt", new MyTextLevelLoader());
		_loadersMap.put("obj", new MyObjectLevelLoader());
	}

	public static void main(String[] args) {
		try {
			init();

			String typeOfFile = FilenameUtils.getExtension("C:\\Users\\mayam\\Desktop\\level.txt");//Get the .txt/.obj/.xml from the filename that the user typed
			ILevelLoader loader = _loadersMap.get(typeOfFile);//Get the type of loader the user typed
			Level level = loader.loadLevel(new FileInputStream(new File("C:\\Users\\mayam\\Desktop\\level.txt")));
			SokobanModel m = new SokobanModel();
			m.setLevel(level);
			OutputStream os = m.compressLevel();
			PrintStream out = new PrintStream(new FileOutputStream("C:\\Users\\mayam\\Desktop\\shrinkedLevel.obj"));
			out.println(os);
			System.setOut(out);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
