package com.hit.service;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import com.hit.algorithm.IBacktrackingAlg;
import com.hit.dao.Dao;
import com.hit.dm.SudokuTemplate;


public class SudokuService{
	
	IBacktrackingAlg algo;
	Dao<String, SudokuTemplate> sudokuTemplatesFileDao;
	
	public SudokuService(IBacktrackingAlg algo, Dao<String, SudokuTemplate> sudokuTemplatesFileDao){
		this.algo = algo;
		this.sudokuTemplatesFileDao = sudokuTemplatesFileDao;
	}
	
	
	//The get function gets sudoku template by value - the value is the level of difficulty 
	//of the sudoku.
	public SudokuTemplate getTemplateByDifficulty(String difficulty) throws Exception {
		if(difficulty == null) {
			throw new Exception("Must choose difficulty!");
		}
		
		SudokuTemplate sudokuTemplate = sudokuTemplatesFileDao.getByValue(difficulty);
		
		
		return sudokuTemplate;
	}
	
	
	public SudokuTemplate getTemplateById(String id) {
		SudokuTemplate sudokuTemplate = sudokuTemplatesFileDao.get(id);
		return sudokuTemplate;
	}
	
	
	
	//The solveSudoku function checks if the sudoku template doens't exist in my database - if 
	//it doesnt exist, and the board is valid.
	public SudokuTemplate solveSudoku(int[][] grid) throws Exception {	
    	SudokuTemplate sudokuTemplate =  new SudokuTemplate(grid);

		this.algo.setGrid(sudokuTemplate.getGrid());
		sudokuTemplate.setId(this.algo.getId());
		
		if(!this.algo.isValidGrid()) {
			throw new Exception("Invalid Board!");
		}
		
		SudokuTemplate existingSudokuTemplate = sudokuTemplatesFileDao.get(sudokuTemplate.getId());
		
		if(existingSudokuTemplate == null) {
			sudokuTemplate.setDifficulty(this.algo.getDifficulty());

			sudokuTemplatesFileDao.save(sudokuTemplate);
		}
		
		this.algo.solve();
		sudokuTemplate.setGrid(this.algo.getGrid());
		
		return sudokuTemplate;
	}
	
	
	//The delete function delete sudoku template from my database.
	public void delete(String id) {
		sudokuTemplatesFileDao.delete(id);			
	}



	
}
