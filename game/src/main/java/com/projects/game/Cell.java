package com.projects.game;

public class Cell {
	private boolean isMine;
	private boolean revealed;
	private int adjacentMines;

	public boolean isMine() {
		return isMine;
	}

	public void setMine(boolean mine) {
		isMine = mine;
	}

	public boolean isRevealed() {
		return revealed;
	}

	public void reveal() {
		revealed = true;
	}

	public int getAdjacentMines() {
		return adjacentMines;
	}

	public void setAdjacentMines(int count) {
		this.adjacentMines = count;
	}
}
