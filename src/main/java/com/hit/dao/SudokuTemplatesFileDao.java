package com.hit.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.hit.dm.SudokuTemplate;

public class SudokuTemplatesFileDao implements Dao<String, SudokuTemplate> {
	
	String path = "sudokuTemplates.txt";
	ObjectOutputStream objectOutputStream;
	ObjectInputStream objectInputStream;
	
	public SudokuTemplatesFileDao() throws FileNotFoundException, IOException {	
		objectOutputStream = new ObjectOutputStream(new FileOutputStream(path));
		objectInputStream = new ObjectInputStream(new FileInputStream(path));
	}
	
	
	//The get if function checks if specific id is in the database.
	//If the id exists, great give me back the sudoku template the id stands for.
	//If the id doesn't exist, return null.
	
	@Override
	public SudokuTemplate get(String id) {
		List<SudokuTemplate> sudokuTemplates;
		
		try {
			sudokuTemplates = (List<SudokuTemplate>) objectInputStream.readObject();
			for (SudokuTemplate template : sudokuTemplates) {
				if (template.getId().equals(id)) {
					return template;
				}
			}
			
			return null;
			
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public SudokuTemplate getByValue(String difficulty) {
		List<SudokuTemplate> sudokuTemplates;
		List<SudokuTemplate> sameDifficultySudokuTemplates = new ArrayList<>();
		
		try {
			sudokuTemplates = (List<SudokuTemplate>) objectInputStream.readObject();
			for (SudokuTemplate template : sudokuTemplates) {
				if (template.getDifficulty().equals(difficulty)) {
					sameDifficultySudokuTemplates.add(template);
				}
			}
			
			Random random = new Random();
			int randomNumber = random.nextInt(sameDifficultySudokuTemplates.size() -1);
			
			return sameDifficultySudokuTemplates.get(randomNumber);
			
			
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
				
	
	@Override
	public void save(SudokuTemplate sudokuTemplate) {
		try {
			List<SudokuTemplate> sudokuTemplates = (List<SudokuTemplate>) objectInputStream.readObject();
			sudokuTemplates.add(sudokuTemplate);
			objectOutputStream.writeObject(sudokuTemplates);
			
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}	
	}


	@Override
	public void delete(String id) {
		try {
			List<SudokuTemplate> sudokuTemplates = (List<SudokuTemplate>) objectInputStream.readObject();
			
			for(int i=0; i<sudokuTemplates.size(); i++) {
				 
				if(sudokuTemplates.get(i).getId().equals(id)) {
					sudokuTemplates.remove(i);
				}
			}
			
			objectOutputStream.writeObject(sudokuTemplates);
			
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}	
		
	}

}
