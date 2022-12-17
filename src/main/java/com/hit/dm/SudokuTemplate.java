package com.hit.dm;

public class SudokuTemplate {
	
	private String id;
	private String difficulty;
	private int[][] matrix;

	public SudokuTemplate(String id, int[][] matrix, String difficulty) {
		super();
		this.id = id;
		this.matrix = matrix;
		this.difficulty = difficulty;		
	}
	
	public String getId() {
		return this.id;
	}
	
	public int[][] getMatrix() {
		return this.matrix;
	}
	
	public String getDifficulty() {
		return this.difficulty;
	}
	
	public void setId(String id) {
		this.id = id;
	}
		
	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}
	
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	


}
