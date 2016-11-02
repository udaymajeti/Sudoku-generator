package Sudoku;

import java.util.Scanner;

public class SudokuDemo {
	public static void main(String[] args) {
		SudokuGenerator obj = new SudokuGenerator();
		Scanner scan = new Scanner(System.in);
		String answer;
		int[][] sudokuSolution;
		int[][] sudokuQuestion; 
		
		do{
			sudokuSolution = obj.getValidSudokuSolution();
			sudokuQuestion = obj.generateSudokuQuestion(sudokuSolution);
			System.out.println("A tricky 4x4 sudoku for you! ");
			obj.printSudoku(sudokuQuestion);
			System.out.println("Want to go with solution?");
			System.out.print("Press any character:");
			answer = scan.next();
			System.out.println("Sudoku solution generated: ");
			obj.printSudoku(sudokuSolution);
			System.out.println("Want to generate again??. Check it out by running code. Thank you :) ");
     		System.out.println("Want to try again!(y or n)");
			answer = scan.next();
		}while(answer.equals("y") || answer.equals("Y"));
		scan.close();
		System.out.println("See you again! Have a great day!");
	}
}