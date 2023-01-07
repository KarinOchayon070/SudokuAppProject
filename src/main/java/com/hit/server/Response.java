package com.hit.server;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.hit.dm.SudokuTemplate;

public class Response {
	
	 public String json;
	 public SudokuTemplate sudokuTemplate;

	 public Response(){
	 }
	 
	 public Response(SudokuTemplate sudokuTemplate){
	     this.sudokuTemplate = sudokuTemplate;
	 }
	 
	 
//	 public Response(Map<String,String> sudokuTemplateMap){
//		 SudokuTemplate template = (SudokuTemplate) sudokuTemplateMap.values();
//		 
//	     this.sudokuTemplate = template;
//	 }
	 

//	 public Response(String string){
//		 json = string;
//	 }

//	 public String toString() {
//		 return  "{'Songs':" + songs + "', 'json':'" + json + "'}";
//	  }
}
