package com.hit.service;
import com.hit.algorithm.IBacktrackingAlg;
import com.hit.dao.Dao;
import com.hit.dm.SudokuTemplate;


public class SudokuService {
	
	IBacktrackingAlg algo;
	Dao<String, SudokuTemplate> sudokuTemplatesFileDao;
	
	public SudokuService(IBacktrackingAlg algo, Dao<String, SudokuTemplate> sudokuTemplatesFileDao){
		this.algo = algo;
		this.sudokuTemplatesFileDao = sudokuTemplatesFileDao;
	}
	
	
	//The get function gets sudoku template by value - the value is the level of difficulty 
	//of the sudoku.
	
	public SudokuTemplate getTemplateByDifficulty(String difficulty) {
		SudokuTemplate sudokuTemplate = sudokuTemplatesFileDao.get(difficulty);
		
		return sudokuTemplate;
	}
	
	
	//The solveSudoku function checks if the sudoku template doens't exist in my database - if 
	//it doesnt exist, and the board is valid.
	
	public SudokuTemplate solveSudoku(SudokuTemplate sudokuTemplate) {
		
		if(!this.algo.checkBoard()) {
			return null;
		}
		
		if(sudokuTemplate.getId() == null) {
			sudokuTemplate.setDifficulty(this.algo.difficulty());
			sudokuTemplate.setId(this.algo.getId());
			
			sudokuTemplatesFileDao.save(sudokuTemplate);
		}
		
		this.algo.solve();
		sudokuTemplate.setMatrix(this.algo.getGrid());
		
		return sudokuTemplate;
	}
	
	
	//The delete function delete sudoku template from my database.
	
	public void delete(String id) {
		sudokuTemplatesFileDao.delete(id);			
	}
	
}
