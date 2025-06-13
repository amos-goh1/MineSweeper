package com.projects.game;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class GameEngine {
	private final int size;
	private final int totalMines;
	private final char HIDDEN = '_';
	private final char MINE = '*';
	private final char EMPTY = '0';

	private Cell[][] board;
	private final MinePlacementStrategy mineStrategy;
	private boolean printedOnce = false;
	private boolean gameOver = false;

	public GameEngine(int size, int totalMines, MinePlacementStrategy mineStrategy) {
		this.size = size;
		this.totalMines = totalMines;
		this.mineStrategy = mineStrategy;
		this.board = new Cell[size][size];
		initializeBoard();
		placeMines();
		calculateAndPopulateMineCounts();
	}

	private void initializeBoard() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				board[i][j] = CellFactory.createCell();
			}
		}
	}

	private void placeMines() {
		List<int[]> mines = mineStrategy.getMinePositions(size, totalMines);
		for (int[] pos : mines) {
			board[pos[0]][pos[1]].setMine(true);
		}
	}

	private void calculateAndPopulateMineCounts() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (board[i][j].isMine())
					continue;
				int count = 0;
				for (int dr = -1; dr <= 1; dr++) { // right-left
					for (int dc = -1; dc <= 1; dc++) { // above-below
						int nr = i + dr; // neighbour cell coordinate (row)
						int nc = j + dc; // neighbour cell coordinate (column)
						if (nr >= 0 && nr < size && nc >= 0 && nc < size && board[nr][nc].isMine()) { // check if neighbouring cell is within grid, and whether it is a mine
							count++;
						}
					}
				}
				board[i][j].setAdjacentMines(count);
			}
		}
	}

	/*private void reveal(int row, int col) {
		if (row < 0 || row >= size || col < 0 || col >= size || board[row][col].isRevealed()) // return if row or column is outside grid, or cell is already revealed.
			return;
		//if (row < 0 || row >= size || col < 0 || col >= size) {
		//	throw new IndexOutOfBoundsException("Invalid coordinates!");
		//}

		board[row][col].reveal();
		if (board[row][col].isMine()) {
			gameOver = true;
			return;
		}
		if (board[row][col].getAdjacentMines() == 0) {// recursively reveal adjacent cells until their neighbouring cell
														// contains a mine
			for (int dr = -1; dr <= 1; dr++) { // right-left
				for (int dc = -1; dc <= 1; dc++) { // above-below
					if (dr != 0 || dc != 0) { // skip checking itself (checked before already)
						reveal(row + dr, col + dc);
					}
				}
			}
		}
	}*/

	private void reveal(int row, int col) {
		Cell start = board[row][col];

		if (row < 0 || row >= size || col < 0 || col >= size || board[row][col].isRevealed()) { // return if row or column is outside grid, or cell is already revealed.
			return;
		}

		if (start.isMine()) {
			start.reveal();
			gameOver = true;
			return;
		}

		LinkedList<int[]> list = new LinkedList<>();
		list.add(new int[] { row, col });

		while (!list.isEmpty()) {
			int[] coordinate = list.pop();
			int r = coordinate[0], c = coordinate[1];
			Cell cell = board[r][c];

			if (cell.isRevealed() || cell.isMine()) {
				continue;
			}

			cell.reveal();

			if (cell.getAdjacentMines() == 0) {
				for (int dr = -1; dr <= 1; dr++) {
					for (int dc = -1; dc <= 1; dc++) {
						if (!(dr == 0 && dc == 0)) { // skip adding itself to the list again
							int nr = r + dr;
							int nc = c + dc;
							if (nr >= 0 && nr < size && nc >= 0 && nc < size) {
								Cell neighbour = board[nr][nc];
								if (!neighbour.isRevealed() && !neighbour.isMine()) {
									list.add(new int[] { nr, nc });
								}
							}
						}

					}
				}
			}
		}
	}

	private boolean checkWin() {

		// iterate through all cells to check if there are non-mine cells not yet revealed. if there are then game has not been won.
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (!board[i][j].isMine() && !board[i][j].isRevealed()) {
					return false;
				}
			}
		}
		return true;
	}

	public void play(Scanner scanner) {
		while (!gameOver) {
			if (!printedOnce) {
				System.out.println("\nHere is your minefield:");
				printedOnce = true;
			} else {
				System.out.println("\nHere is your updated minefield:");
			}
			printBoard();
			System.out.print("\nSelect a square to reveal (e.g. A1): ");
			String input = scanner.nextLine().toUpperCase();
			if (!input.matches("[A-I][1-9]")) {
				System.out.println("Invalid input. Try again.");
				continue;
			}

			int row = input.charAt(0) - 'A';
			int col = input.charAt(1) - '1';

			if (row < 0 || row >= size || col < 0 || col >= size) {
				System.out.println("Invalid input. Try again.");
				continue;
			}

			reveal(row, col);

			if (gameOver) {
				// revealAll();
				// printBoard();
				System.out.println("Oh no, you detonated a mine! Game over.");
				break;
			}

			if (checkWin()) {
				revealAll();
				System.out.println("\nHere is your updated minefield:");
				printBoard();
				System.out.println("Congratulations, you have won the game!");
				break;
			}
			System.out.println("This square contains " + board[row][col].getAdjacentMines() + " adjacent mine(s).");
		}
	}

	private void revealAll() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				board[i][j].reveal();
			}
		}
	}

	public void printBoard() {
		System.out.print("  ");
		for (int j = 0; j < size; j++) {
			System.out.print((j + 1) + " ");
		}
		System.out.println();
		for (int i = 0; i < size; i++) {
			System.out.print((char) ('A' + i) + " ");
			for (int j = 0; j < size; j++) {
				if (!board[i][j].isRevealed()) {
					System.out.print(HIDDEN + " ");
				} else if (board[i][j].isMine()) {
					System.out.print(MINE + " ");
				} else if (board[i][j].getAdjacentMines() == 0) {
					System.out.print(EMPTY + " ");
				} else {
					System.out.print(board[i][j].getAdjacentMines() + " ");
				}
			}
			System.out.println();
		}
	}
}
