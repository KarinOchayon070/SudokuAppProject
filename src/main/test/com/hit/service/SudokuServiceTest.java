package com.hit.service;

import com.hit.algorithm.BitMaskAlgo;
import com.hit.algorithm.DFSAlgo;
import com.hit.dao.SudokuTemplatesFileDao;
import com.hit.dm.SudokuTemplate;

import util.BackupAndRestore;

import static org.junit.Assert.assertThrows;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

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
	public void solveAndInsertToDBNewTemplate() throws Exception {
		
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
		BitMaskAlgo bitMaskAlgo = new BitMaskAlgo();
		
		//Creating new instance of my sudoku's service - notice that I implements the DFS algorithm 
		//instead of the IBacktracing interface.
		SudokuService sudokuService = new SudokuService(bitMaskAlgo, new SudokuTemplatesFileDao());
		SudokuTemplate sudokuTemplate = sudokuService.solveSudoku(grid);
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

		assertThrows(Exception.class, () -> sudokuService.solveSudoku(grid));
	}
	
	
	@Test
	public void getTemplateByDifficullty() throws Exception {
		DFSAlgo dfsAlgo = new DFSAlgo();	
		BitMaskAlgo bitMaskAlgo = new BitMaskAlgo();
		SudokuService sudokuService = new SudokuService(dfsAlgo, new SudokuTemplatesFileDao());
		SudokuTemplate sudokuTemplate = sudokuService.getTemplateByDifficulty("Easy");
		Assert.assertEquals(sudokuTemplate.getDifficulty(), "Easy");
	}
	
	@Test
	public void getTemplateById() {
		
		DFSAlgo dfsAlgo = new DFSAlgo();	
		BitMaskAlgo bitMaskAlgo = new BitMaskAlgo();
		SudokuService sudokuService = new SudokuService(dfsAlgo, new SudokuTemplatesFileDao());
		SudokuTemplate sudokuTemplate = sudokuService.getTemplateById(gridId);
		Assert.assertEquals(sudokuTemplate.getId(), gridId);
	}
	
	@Test
	public void deleteTemplateById() {
		
		DFSAlgo dfsAlgo = new DFSAlgo();	
		BitMaskAlgo bitMaskAlgo = new BitMaskAlgo();
		SudokuService sudokuService = new SudokuService(dfsAlgo, new SudokuTemplatesFileDao());
		sudokuService.delete(gridId);
		SudokuTemplate sudokuTemplate = sudokuService.getTemplateById(gridId);
		Assert.assertNull(sudokuTemplate);
	}
	
	@Test
	public void backupTest() throws IOException, InterruptedException {
		String filePath = System.getProperty("user.dir") + File.separator + "sudokuTemplates.txt";	
		String copyFilePath = System.getProperty("user.dir") + File.separator + "sudokuTemplates_backup.txt";
		
		BackupAndRestore.backup(filePath, copyFilePath, 1000, 1000);
		
        Thread.sleep(3000);
		
        File testFile = new File(filePath);
        File backUpTestFile = new File(copyFilePath);

        Assert.assertEquals(new String(Files.readAllBytes(testFile.toPath()), StandardCharsets.UTF_8), new String(Files.readAllBytes(backUpTestFile.toPath()), StandardCharsets.UTF_8));
	}
	
	@Test
	public void restoreTest() {	
		String filePath = System.getProperty("user.dir") + File.separator + "sudokuTemplates_backup.txt";
        
        String fileContent = BackupAndRestore.restore(filePath);
        
        Assert.assertNotNull(fileContent);
	}
}
