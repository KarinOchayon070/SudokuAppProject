package com.hit.server;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.hit.dm.SudokuTemplate;

public class Response {
	
	 public boolean success = true;
	 public String message;
	 public SudokuTemplate sudokuTemplate;
	 public List<SudokuTemplate> sudokuTemplates;
	 
	 
	 public Response(){}
	 
	 public Response(SudokuTemplate sudokuTemplate){
	     this.sudokuTemplate = sudokuTemplate;
	 }
	 
	 public Response(List<SudokuTemplate> sudokuTemplates){
	     this.sudokuTemplates = sudokuTemplates;
	 }
	 
	 public Response(String string, boolean isSuccess){
		 this.message = string;
		 this.success = isSuccess;
	 }
}
