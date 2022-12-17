package com.hit.algo;
import com.hit.algorithm.DFSAlgo;
import com.hit.dao.SudokuTemplatesFileDao;
import com.hit.dm.SudokuTemplate;
import com.hit.service.SudokuService;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;

public class SudokuServiceTest {


	
	
//	int[][] gridHard =  { { 0, 3, 0, 6, 0, 0, 4, 0, 0 },
//				      	  { 0, 0, 0, 0, 0, 0, 0, 6, 0 },
//				          { 0, 0, 0, 0, 0, 9, 0, 0, 8 },
//				          { 0, 0, 1, 0, 2, 6, 0, 4, 0 },
//				          { 3, 0, 0, 0, 5, 0, 7, 0, 0 },
//				          { 2, 0, 6, 0, 0, 3, 0, 0, 1 },
//				          { 0, 8, 0, 1, 9, 0, 0, 0, 0 },
//				          { 0, 0, 5, 3, 4, 0, 0, 0, 7 },
//				          { 4, 2, 7, 0, 0, 0, 9, 0, 0 } };
	
	@Test
	public void solveNewTemplate() throws IOException {
		
		int [][] gridEasy = {    { 4, 3, 0, 0, 7, 0, 0, 0, 0 },
	        	{ 7, 0, 1, 0, 3, 5, 9, 0, 6 },
	            { 5, 0, 0, 0, 8, 0, 0, 7, 3 },
	            { 2, 4, 7, 0, 9, 3, 5, 0, 0 },
	            { 0, 0, 0, 8, 0, 7, 0, 0, 0 },
	            { 0, 0, 0, 5, 2, 0, 1, 0, 7 },
	            { 0, 0, 2, 7, 4, 0, 6, 0, 0 },
	            { 6, 0, 4, 0, 5, 8, 2, 0, 0 },
	            { 0, 0, 0, 6, 1, 2, 0, 3, 0 } };
		
		DFSAlgo dfsAlgo = new DFSAlgo(gridEasy);
		SudokuService sudokuService = new SudokuService(dfsAlgo, new SudokuTemplatesFileDao());
//		sudokuService.
		
		
		sudokuService.getTemplateByDifficulty("Hard");
		
//		SudokuTemplate sudokuTemplate = new SudokuTemplate("Easy", gridEasy);
//		sudokuTemplate.add(new SudokuTemplate("Hard", gridHard));
//		sudokuService.add(sudokuTemplate);
		Assert.assertEquals(book, bookService.getBookById(222l));
		Assert.assertEquals(null, bookService.getBookById(444l));
	}
	
	
//	@Test
//	public void getSudokuTemplateByDifficultyDFSTest() throws IOException{
//		
//		int [][] gridEasy = {    { 4, 3, 0, 0, 7, 0, 0, 0, 0 },
//	        	{ 7, 0, 1, 0, 3, 5, 9, 0, 6 },
//	            { 5, 0, 0, 0, 8, 0, 0, 7, 3 },
//	            { 2, 4, 7, 0, 9, 3, 5, 0, 0 },
//	            { 0, 0, 0, 8, 0, 7, 0, 0, 0 },
//	            { 0, 0, 0, 5, 2, 0, 1, 0, 7 },
//	            { 0, 0, 2, 7, 4, 0, 6, 0, 0 },
//	            { 6, 0, 4, 0, 5, 8, 2, 0, 0 },
//	            { 0, 0, 0, 6, 1, 2, 0, 3, 0 } };
//		
//		DFSAlgo dfsAlgo = new DFSAlgo(gridEasy);
//		dfsAlgo.solve();
//		SudokuService sudokuService = new SudokuService(dfsAlgo, new SudokuTemplatesFileDao());
//		
//		sudokuService.getTemplateByDifficulty("Hard");
//		
////		SudokuTemplate sudokuTemplate = new SudokuTemplate("Easy", gridEasy);
////		sudokuTemplate.add(new SudokuTemplate("Hard", gridHard));
////		sudokuService.add(sudokuTemplate);
//		Assert.assertEquals(book, bookService.getBookById(222l));
//		Assert.assertEquals(null, bookService.getBookById(444l));
//	}
}
