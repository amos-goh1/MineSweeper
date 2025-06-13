package com.projects.game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomMinePlacementStrategy implements MinePlacementStrategy {
	@Override
	public List<int[]> getMinePositions(int size, int totalMines) {
		Set<String> used = new HashSet<>();
		List<int[]> mines = new ArrayList<>();
		Random rand = new Random();

		while (mines.size() < totalMines) {
			int row = rand.nextInt(size);
			int col = rand.nextInt(size);
			String key = row + "," + col; // cell coordinate with mine
			if (!used.contains(key)) {
				used.add(key);
				mines.add(new int[] { row, col });
			}
		}

		return mines;
	}
}
