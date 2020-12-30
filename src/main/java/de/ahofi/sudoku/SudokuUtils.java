package de.ahofi.sudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SudokuUtils {

  /** Size of a square */
  public static final int MAX_SQUARE = 3;

  /** Size of a row or column */
  public static final int MAX_ROW_COL = 9;

  /** Size of the SudokuSolver */
  public static final int MAX_SIZE = MAX_ROW_COL * MAX_ROW_COL;

  private static final List<Integer> VALUES = IntStream.range(1, MAX_ROW_COL + 1).boxed().collect(Collectors.toList());

  /**
   * Provides a new list with all possible {@link #VALUES} for a SudokuSolver.
   *
   * @return the list of {@link #VALUES} as copy.
   */
  public static List<Integer> getSudokuValues() {
    return new ArrayList<>(SudokuUtils.VALUES);
  }

  /**
   * Returns the top left corner (index) based on the given index. <br/ > <br/ >
   * Example: <br/ > <br/ > index: 3 <br/ > top left corner: 0
   *
   * @param index
   *          the index we want to top left corner for
   * @return the top left corner of the house (0-2)
   */
  public static int getTopLeftCornerOfHouse(final int index) {
    return index / MAX_SQUARE * MAX_SQUARE;
  }

  public static <R> void iterateThroughMatrix(final R[][] matrix, final Consumer<R> function) {
    for (final R[] row : matrix) {
      for (final R element : row) {
        function.accept(element);
      }
    }
  }
}
