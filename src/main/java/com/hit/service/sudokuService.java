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
	public SudokuTemplate getTemplateByDifficulty(String difficulty) {
		SudokuTemplate sudokuTemplate = sudokuTemplatesFileDao.getByValue(difficulty);
		return sudokuTemplate;
	}
	
	
	public SudokuTemplate getTemplateById(String id) {
		SudokuTemplate sudokuTemplate = sudokuTemplatesFileDao.get(id);
		return sudokuTemplate;
	}
	
	
	
	//The solveSudoku function checks if the sudoku template doens't exist in my database - if 
	//it doesnt exist, and the board is valid.
	public boolean solveSudoku(SudokuTemplate sudokuTemplate) {	
		this.algo.setGrid(sudokuTemplate.getGrid());
		
		if(!this.algo.isValidGrid()) {
			return false;
		}
		if(sudokuTemplate.getId() == null) {
			sudokuTemplate.setDifficulty(this.algo.getDifficulty());
			sudokuTemplate.setId(this.algo.getId());
			sudokuTemplatesFileDao.save(sudokuTemplate);
		}
		this.algo.solve();
		sudokuTemplate.setGrid(this.algo.getGrid());
		
		return true;
	}
	
	
	//The delete function delete sudoku template from my database.
	public void delete(String id) {
		sudokuTemplatesFileDao.delete(id);			
	}



	
}
