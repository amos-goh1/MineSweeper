package com.projects.game;

import java.util.Scanner;

public class MineSweeper {
	public static void main(String[] args) {
		MinePlacementStrategy strategy = new RandomMinePlacementStrategy();
		MineSweeper mineSweeper = new MineSweeper();
		System.out.println("Welcome to Minesweeper!\n");
		
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			int size = 0;
			int mines = 0;
			
			size = mineSweeper.getGridSize(scanner);

			int maxMines = (int) Math.floor(size * size * 0.35);

			mines = mineSweeper.getNoOfMines(scanner, maxMines);
			
			GameEngine game = new GameEngine(size, mines, strategy);
			game.play(scanner);

			System.out.print("\nPress any key to play again... (type 'end' to stop) ");

			String input = scanner.nextLine();

			if (input.equals("end")) {
				System.out.println("Thank you for playing. See you again!");
				break;
			}
		}
		
		scanner.close();
	}
	
	// prompts player for grid size between 2 and 9.
	public int getGridSize(Scanner scanner) {

		while (true) {
			System.out.println("Enter the size of the grid (e.g. 4 for a 4x4 grid): ");
			String input = scanner.nextLine();
			
			if (!input.matches("[2-9]")) {
				System.out.println("Invalid input. Choose a value between 2 and 9.");
				continue;
			}
			
			return Integer.valueOf(input);
		}
	}
	
	// prompts player for number of mines. value must be 1 <= x <= maxMines
	public int getNoOfMines(Scanner scanner, int maxMines) {
		
		while (true) {
			System.out.println("Enter the number of mines to place on the grid (maximum is " + maxMines + ", 35% of the total squares): ");
			String input = scanner.nextLine();
			if (!input.matches("^\\d+$")) {
				System.out.println("Invalid input. Try again.");
				continue;
			}
			int mines = Integer.valueOf(input);
			if (mines >= 1 && mines <= maxMines) {
				return mines;
			} else {
				System.out.println("Please enter a valid positive value. Maximum value is: " + maxMines + ".");
				
			}
		}
	}
}
