package com.hit.controller;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.hit.algorithm.BitMaskAlgo;
import com.hit.algorithm.DFSAlgo;
import com.hit.dao.SudokuTemplatesFileDao;
import com.hit.dm.SudokuTemplate;
import com.hit.service.SudokuService;

public class SudokuController {
	
	DFSAlgo dfsAlgo = new DFSAlgo();	
	//BitMaskAlgo bitMaskAlgo = new BitMaskAlgo();
	SudokuService service = new SudokuService(dfsAlgo, new SudokuTemplatesFileDao());


    public SudokuTemplate getTemplateByDifficulty(Map<String,Object> body) throws Exception {
    	String difficulty = (String) body.get("difficulty");
  	
        return service.getTemplateByDifficulty(difficulty);
    }


    public SudokuTemplate solveSudoku(Map<String,Object> body) throws Exception{ 	
    	List<List<Double>> gridList = (List<List<Double>>) body.get("grid");
    	int[][] grid = new int[gridList.size()][gridList.get(0).size()];
    	for (int i = 0; i < gridList.size(); i++) {
    	    for (int j = 0; j < gridList.get(i).size(); j++) {
    	        grid[i][j] = gridList.get(i).get(j).intValue();
    	    }
    	}
    	
    	SudokuTemplate sudokuTemplate = service.solveSudoku(grid);
    	
    	return sudokuTemplate;
    }

    public SudokuTemplate getTemplateById(Map<String,Object> body){
    	String id = (String) body.get("id");
    	
        return service.getTemplateById(id);
    }
    
    public List<SudokuTemplate> getAllTemplates(){
    	
        return service.getAllTemplates();
    }
    
    public void delete(Map<String,Object> body) {
    	String id = (String) body.get("id");

        service.delete(id);
    }

}
