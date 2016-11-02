package Sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SudokuGenerator {
	
	private final static int SIZE = 4;
	public static ArrayList<Integer> possibleValForRow(int sudoku[][], ArrayList<Integer> values, int row, int col){
		for(int j=0;j<col;j++){
			values.remove(new Integer(sudoku[row][j]));
		}
		return values;
	}
	
	public static ArrayList<Integer> possibleValForColumn(int sudoku[][], ArrayList<Integer> values, int row, int col){
		for(int i=0;i<row;i++){
			values.remove(new Integer(sudoku[i][col]));
		}
		return values;
	}
	
	public static ArrayList<Integer> possibleValForBlock(int sudoku[][], ArrayList<Integer> values, int row, int col){
		int blockNum = getBlockNum(row, col);
		switch(blockNum){
			case 1:
				for(int i=0;i<SIZE/2;i++){
					for(int j=0;j<SIZE/2;j++){
						values.remove(new Integer(sudoku[i][j]));
					}
				}
				break;

			case 2:
				for(int i=0;i<SIZE/2;i++){
					for(int j=SIZE/2;j<SIZE;j++){
						values.remove(new Integer(sudoku[i][j]));
					}
				}
				break;
			case 3:
				for(int i=SIZE/2;i<SIZE;i++){
					for(int j=0;j<SIZE/2;j++){
						values.remove(new Integer(sudoku[i][j]));
					}
				}
				break;
			case 4:
				for(int i=SIZE/2;i<SIZE;i++){
					for(int j=SIZE/2;j<SIZE;j++){
						values.remove(new Integer(sudoku[i][j]));
					}
				}
				break;
			default:
				System.out.println("getBlockNum() is not working properly");
		}
		return values;
	}
	
	public static int getBlockNum(int row, int col){
		if(row<SIZE/2&&col<SIZE/2)
			return 1;
		else if(row<SIZE/2&&col>=SIZE/2){
			return 2;
		}
		else if(row>=SIZE/2&&col<SIZE/2){
			return 3;
		}
		else{
			return 4;
		}
	}
	
	public Board generateSudoku(int[][] sudoku){
		Random rand = new Random();
		ArrayList<Integer> all;
		ArrayList<Integer> possible;
		String num = "";
		boolean valid = true;
		for(int i=0;i<SIZE;i++){
			for(int j=0;j<SIZE;j++){
				all = new ArrayList<>(Arrays.asList(1,2,3,4));
				possible = possibleValForRow(sudoku, all, i, j);
				possible = possibleValForColumn(sudoku, possible, i, j);
				possible = possibleValForBlock(sudoku, possible, i, j);
				if(possible.size()==0){
					valid = false;
					break;
				}
				sudoku[i][j] =  possible.get(rand.nextInt(possible.size()));
				num += sudoku[i][j];
			}	
		}
		Board w = new Board();
		w.isValid = valid;		
		w.sudoku = sudoku;
		return w;
	}
	
	public int[][] getValidSudokuSolution(){
		Board w = generateSudoku(getEmptySudokuBoard());
		while(!w.isValid){
			w = generateSudoku(getEmptySudokuBoard());
		}
		return w.sudoku;
	}
	public void printSudoku(int[][] sudoku){
		for(int i=0;i<SIZE;i++){
			for(int j=0;j<SIZE;j++){
				if(sudoku[i][j] == 0){ 
					System.out.print("-  ");
				    continue;
			    }
				System.out.print(sudoku[i][j]+ "  ");
			}
			System.out.println();
		}
	}
	
	public int[][] getEmptySudokuBoard(){
		int emptyBoard[][]= new int[SIZE][SIZE];
		return emptyBoard;
	}
	
	public int[][] generateSudokuQuestion(int[][] sudokuSolution){
		int[][] sudokuQuestion = getEmptySudokuBoard();
		Random rand = new Random();
		int count = 0;
		int row, col;
		while(count < 6){
			row = rand.nextInt(SIZE);
			col = rand.nextInt(SIZE);
			if(sudokuQuestion[row][col]!=0)
				continue;
			sudokuQuestion[row][col] = sudokuSolution[row][col];
			count++;
		}
		return sudokuQuestion;
	}
}
