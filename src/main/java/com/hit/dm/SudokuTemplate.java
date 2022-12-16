package com.hit.dm;

public class SudokuTemplate {
	
	private Long id;
	private String difficulty;
	private int[][] matrix;

	public SudokuTemplate(Long id, int[][] matrix, String difficulty) {
		super();
		this.id = id;
		this.matrix = matrix;
		this.difficulty = difficulty;		
	}
	
	public Long getId() {
		return this.id;
	}
	
	public int[][] getMatrix() {
		return this.matrix;
	}
	
	public String getDifficulty() {
		return this.difficulty;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
		
	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}
	
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	


}
