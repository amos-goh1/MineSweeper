package com.projects.game;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class MinesweeperTest {

	@Test
	void testMinePlacementCorrectness() {
		List<int[]> mines = Arrays.asList(new int[] { 0, 0 }, new int[] { 1, 1 });
		GameEngine game = new GameEngine(3, 2, new FixedMinePlacementStrategy(mines));

		// Ensure mines are placed correctly
		assertTrue(getCell(game, 0, 0).isMine());
		assertTrue(getCell(game, 1, 1).isMine());
		assertFalse(getCell(game, 0, 1).isMine());
	}

	@Test
	void testAdjacentMineCount() {
		List<int[]> mines = Collections.singletonList(new int[] { 1, 1 });
		GameEngine game = new GameEngine(3, 1, new FixedMinePlacementStrategy(mines));

		assertEquals(1, getCell(game, 0, 0).getAdjacentMines());
		assertEquals(1, getCell(game, 0, 1).getAdjacentMines());
		assertEquals(1, getCell(game, 0, 2).getAdjacentMines());
		assertEquals(1, getCell(game, 1, 0).getAdjacentMines());
		assertEquals(1, getCell(game, 1, 2).getAdjacentMines());
		assertEquals(1, getCell(game, 2, 0).getAdjacentMines());
		assertEquals(1, getCell(game, 2, 1).getAdjacentMines());
		assertEquals(1, getCell(game, 2, 2).getAdjacentMines());
	}

	@Test
	void testRevealEmptyCellAndCascade() {
		List<int[]> mines = Collections.singletonList(new int[] { 2, 2 });
		GameEngine game = new GameEngine(3, 1, new FixedMinePlacementStrategy(mines));

		// simulate reveal on top-left corner
		invokeReveal(game, 0, 0);

		// all empty cells should be revealed
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (!(i == 2 && j == 2)) {
					assertTrue(getCell(game, i, j).isRevealed());
				}
			}
		}
	}

	@Test
	void testRevealMineEndsGame() {
		List<int[]> mines = Collections.singletonList(new int[] { 0, 0 });
		GameEngine game = new GameEngine(3, 1, new FixedMinePlacementStrategy(mines));

		invokeReveal(game, 0, 0);
		assertTrue(getCell(game, 0, 0).isRevealed());
	}

	@Test
	void testBoundaryRevealDoesNotCrash() {
		List<int[]> mines = Collections.singletonList(new int[] { 0, 0 });
		GameEngine game = new GameEngine(3, 1, new FixedMinePlacementStrategy(mines));

		// Test boundary edges
		invokeReveal(game, 0, 2);
		invokeReveal(game, 2, 0);
		invokeReveal(game, 2, 2);

		// Ensure no crash and correct reveal
		assertTrue(getCell(game, 0, 2).isRevealed());
		assertTrue(getCell(game, 2, 0).isRevealed());
		assertTrue(getCell(game, 2, 2).isRevealed());
	}

	// Helper method to access private cells using reflection
	private Cell getCell(GameEngine game, int row, int col) {
		try {
			java.lang.reflect.Field boardField = GameEngine.class.getDeclaredField("board");
			boardField.setAccessible(true);
			Cell[][] board = (Cell[][]) boardField.get(game);
			return board[row][col];
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// Helper method to invoke private reveal method using reflection
	public void invokeReveal(GameEngine game, int row, int col) {
		try {
			java.lang.reflect.Method revealMethod = GameEngine.class.getDeclaredMethod("reveal", int.class, int.class);
			revealMethod.setAccessible(true);
			revealMethod.invoke(game, row, col);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
