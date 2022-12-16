package com.hit.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import com.hit.dm.SudokuTemplate;

public class SudokuTemplatesFileDao implements Dao<String, SudokuTemplate> {
	
	String path = "sudokuTemplates.txt";
	ObjectOutputStream objectOutputStream;
	ObjectInputStream objectInputStream;
	
	public SudokuTemplatesFileDao() throws FileNotFoundException, IOException {	
		objectOutputStream = new ObjectOutputStream(new FileOutputStream(path));
		objectInputStream = new ObjectInputStream(new FileInputStream(path));
	}

	
	public SudokuTemplate get(String difficulty) {
		List<SudokuTemplate> sudokuTemplates;
		try {
			sudokuTemplates = (List<SudokuTemplate>) objectInputStream.readObject();
			for (SudokuTemplate template : sudokuTemplates) {
				if (template.getDifficulty().equals(difficulty)) {
					return template;
				}
			}
			
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void save(SudokuTemplate sudokuTemplate) {
		try {
			List<SudokuTemplate> sudokuTemplates = (List<SudokuTemplate>) objectInputStream.readObject();
			sudokuTemplates.add(sudokuTemplate);
			
			objectOutputStream.writeObject(sudokuTemplates);
			
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}	
	}

}
