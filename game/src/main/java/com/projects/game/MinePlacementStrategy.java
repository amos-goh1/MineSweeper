package com.projects.game;

import java.util.List;

public interface MinePlacementStrategy {
	List<int[]> getMinePositions(int size, int totalMines);
}
