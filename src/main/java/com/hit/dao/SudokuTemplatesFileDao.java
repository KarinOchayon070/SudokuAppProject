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

public class SudokuTemplatesFileDao implements IDao<String, SudokuTemplate> {
	
	String path = "sudokuTemplates.txt";

	
	//The get function checks if a specific id is in the database (sudokuTemplates.txt)
	//If the id exists, great - give me back the sudoku's template the id stands for
	//If the id doesn't exist, return null
	@Override
	public SudokuTemplate get(String id) {
		
		List<SudokuTemplate> sudokuTemplates;
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path));

			sudokuTemplates = (List<SudokuTemplate>) objectInputStream.readObject();
			for (SudokuTemplate template : sudokuTemplates) {
				if (template.getId().equals(id)) {
					objectInputStream.close();
					
					return template;
				}
			}
			objectInputStream.close();
			
			return null;
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	
	
	@Override
	public List<SudokuTemplate> getAll() {
		
		List<SudokuTemplate> sudokuTemplates;
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path));

			sudokuTemplates = (List<SudokuTemplate>) objectInputStream.readObject();

			objectInputStream.close();
			
			return sudokuTemplates;
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		return null;	
	}
	
	
	
	//The getByValue function takes a level of difficulty and returns a sudoku's template
	//That it's difficulty is the same as the one I passed
	//If it doesn't exist, return null
	@Override
	public SudokuTemplate getByValue(String difficulty) throws Exception {
		//Making this list for randomly choose
		List<SudokuTemplate> sameDifficultySudokuTemplates = new ArrayList<>();
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path));

			List<SudokuTemplate> sudokuTemplates = (List<SudokuTemplate>) objectInputStream.readObject();
			for (SudokuTemplate template : sudokuTemplates) {
				if (template.getDifficulty().equals(difficulty)) {
					sameDifficultySudokuTemplates.add(template);
				}
			}
			
			if(sameDifficultySudokuTemplates.size() == 0) {
				throw new Exception("No board with this difficulty");
			}
			
			Random random = new Random();
			int randomNumber = random.nextInt(sameDifficultySudokuTemplates.size());
			
			objectInputStream.close();
			
			return sameDifficultySudokuTemplates.get(randomNumber);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}
				
	
	//The save function save given sudoku's template to the database (sudokuTemplates.txt)
	@Override 
	public void save(SudokuTemplate sudokuTemplate) {
			try {
				
				List<SudokuTemplate> sudokuTemplates;
				ObjectInputStream objectInputStream = null;
				
				try {
					objectInputStream = new ObjectInputStream(new FileInputStream(path));
					sudokuTemplates = (List<SudokuTemplate>) objectInputStream.readObject();
				}
				catch(Exception e) {
		            sudokuTemplates = new ArrayList<>();
				}
				
				
				sudokuTemplates.add(sudokuTemplate);

				ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path));
				objectOutputStream.writeObject(sudokuTemplates);
				
				
				objectOutputStream.close();
				
				if(objectInputStream != null) {
					objectInputStream.close();	
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}	

	}


	//The delete function receives an ID that represents the ID of a sudoku's template to be deleted
	//It goes through all the sudoku's templates, and if the ID of one of the sudoku's templates is equal
	//To the ID we passed to the function - we will delete it
	@Override
	public void delete(String id) {
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path));
			List<SudokuTemplate> sudokuTemplates = (List<SudokuTemplate>) objectInputStream.readObject();
		
			for(int i=0; i<sudokuTemplates.size(); i++) {
				if(sudokuTemplates.get(i).getId().equals(id)) {
					sudokuTemplates.remove(i);
				}
			}
			
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path));
			objectOutputStream.writeObject(sudokuTemplates);
			
			objectOutputStream.close();
			objectInputStream.close();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}	
	}
}
