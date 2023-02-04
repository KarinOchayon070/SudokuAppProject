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

	 public Response(){}
	 
	 public Response(SudokuTemplate sudokuTemplate){
	     this.sudokuTemplate = sudokuTemplate;
	 }
	 
	 public Response(String string, boolean isSuccess){
		 this.message = string;
		 this.success = isSuccess;
	 }
}
