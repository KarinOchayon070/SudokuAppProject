package com.hit.service;

import com.hit.algorithm.DFSAlgo;
import com.hit.dao.SudokuTemplatesFileDao;
import com.hit.dm.SudokuTemplate;
import org.junit.Assert;
import org.junit.Test;

public class SudokuServiceTest {
	
	static String gridId;
	
	public static boolean isBothGridsTheSame(int solvedByAlg[][], int solvedGrid[][]) {
		
		for (int i = 0; i < solvedByAlg.length; i++) {
			for (int j = 0; j < solvedByAlg[i].length; j++) {
				
			int solvedByAlgGridItem = solvedByAlg[i][j];
			int solvedGridItem = solvedGrid[i][j];
			
				if(solvedByAlgGridItem != solvedGridItem) {
					return false;
				}		
			}
		}
		return true;
	}

	
	@Test
	public void solveAndInsertToDBNewTemplate() {
		
		int grid[][] = { { 3, 0, 6, 5, 0, 8, 4, 0, 0 },
				{ 5, 2, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 8, 7, 0, 0, 0, 0, 3, 1 },
				{ 0, 0, 0, 0, 1, 0, 0, 8, 0 },
				{ 9, 0, 0, 8, 6, 3, 0, 0, 5 },
				{ 0, 5, 0, 0, 9, 0, 6, 0, 0 },
				{ 1, 3, 0, 0, 0, 0, 2, 5, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 7, 4 },
				{ 0, 0, 5, 2, 0, 6, 3, 0, 0 } };
		
		int solvedGrid[][] = { {3, 1, 6, 5, 7, 8, 4, 9, 2 }, 
				{5, 2, 9, 1, 3, 4, 7, 6, 8 }, 
				{4, 8, 7, 6, 2, 9, 5, 3, 1 }, 
				{2, 6, 3, 4, 1, 5, 9, 8, 7 }, 
				{9, 7, 4, 8, 6, 3, 1, 2, 5 }, 
				{8, 5, 1, 7, 9, 2, 6, 4, 3 }, 
				{1, 3, 8, 9, 4, 7, 2, 5, 6 }, 
				{6, 9, 2, 3, 5, 1, 8, 7, 4 }, 
				{7, 4, 5, 2, 8, 6, 3, 1, 9 }  };
		
		
		DFSAlgo dfsAlgo = new DFSAlgo();	
		
		//Creating new instance of my sudoku's service - notice that I implements the DFS algorithm 
		//instead of the IBacktracing interface.
		SudokuService sudokuService = new SudokuService(dfsAlgo, new SudokuTemplatesFileDao());
		SudokuTemplate sudokuTemplate = new SudokuTemplate(grid);
		sudokuService.solveSudoku(sudokuTemplate);
		gridId = sudokuTemplate.getId();
		
		Assert.assertTrue(isBothGridsTheSame(solvedGrid, sudokuTemplate.getGrid()));
	}
	

	@Test
	public void solveInvalidGrid() {
		
		int grid[][] = { { 3, 0, 6, 5, 0, 0, 4, 0, 0 },
				{ 5, 2, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 8, 7, 0, 0, 0, 0, 3, 1 },
				{ 0, 0, 3, 0, 1, 0, 0, 8, 0 },
				{ 9, 0, 0, 8, 6, 3, 0, 0, 5 },
				{ 0, 5, 0, 0, 9, 0, 6, 0, 0 },
				{ 1, 3, 0, 0, 0, 0, 2, 5, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 7, 4 },
				{ 0, 0, 5, 2, 0, 6, 3, 3, 0 } };
		
		
		
		DFSAlgo dfsAlgo = new DFSAlgo();	
		SudokuService sudokuService = new SudokuService(dfsAlgo, new SudokuTemplatesFileDao());
		SudokuTemplate sudokuTemplate = new SudokuTemplate(grid);
		Assert.assertFalse(sudokuService.solveSudoku(sudokuTemplate));
	}
	
	
	@Test
	public void getTemplateByDifficullty() {
		DFSAlgo dfsAlgo = new DFSAlgo();	
		SudokuService sudokuService = new SudokuService(dfsAlgo, new SudokuTemplatesFileDao());
		SudokuTemplate sudokuTemplate = sudokuService.getTemplateByDifficulty("Easy");
		Assert.assertEquals(sudokuTemplate.getDifficulty(), "Easy");
	}
	
	@Test
	public void getTemplateById() {
		
		DFSAlgo dfsAlgo = new DFSAlgo();	
		SudokuService sudokuService = new SudokuService(dfsAlgo, new SudokuTemplatesFileDao());
		SudokuTemplate sudokuTemplate = sudokuService.getTemplateById(gridId);
		Assert.assertEquals(sudokuTemplate.getId(), gridId);
	}
	
	@Test
	public void deleteTemplateById() {
		
		DFSAlgo dfsAlgo = new DFSAlgo();	
		SudokuService sudokuService = new SudokuService(dfsAlgo, new SudokuTemplatesFileDao());
		sudokuService.delete(gridId);
		SudokuTemplate sudokuTemplate = sudokuService.getTemplateById(gridId);
		Assert.assertNull(sudokuTemplate);
	}
}