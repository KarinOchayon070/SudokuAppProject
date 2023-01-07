package com.hit.controller;
import java.io.IOException;
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


    public SudokuTemplate getTemplateByDifficulty(Map<String,String> body) {
    	String difficulty = body.get("difficulty");
    	
        return service.getTemplateByDifficulty(difficulty);
    }


    public SudokuTemplate solveSudoku(Map<String,String> body){
    	SudokuTemplate sudokuTemplate = (SudokuTemplate) body;
    	service.solveSudoku(sudokuTemplate);
    	
    	return sudokuTemplate;
    }

    public SudokuTemplate getTemplateById(Map<String,String> body){
    	String id = body.get("id");
    	
        return service.getTemplateById(id);
    }
    
    public void delete(Map<String,String> body) {
    	String id = body.get("id");

        service.delete(id);
    }

}
