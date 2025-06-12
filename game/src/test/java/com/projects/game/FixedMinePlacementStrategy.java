package com.projects.game;

import java.util.Arrays;
import java.util.List;

class FixedMinePlacementStrategy implements MinePlacementStrategy {
	private final List<int[]> fixedMines;

	public FixedMinePlacementStrategy(List<int[]> mines) {
		this.fixedMines = mines;
	}

	public FixedMinePlacementStrategy() {
		this.fixedMines = Arrays.asList(new int[] { 0, 0 }, new int[] { 1, 1 });
	}

	@Override
	public List<int[]> getMinePositions(int size, int totalMines) {
		return fixedMines;
	}
}
