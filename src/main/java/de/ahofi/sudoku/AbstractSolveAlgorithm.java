package de.ahofi.sudoku;

public abstract class AbstractSolveAlgorithm {

  /**
   * Try to find a matching number for the {@link Cell} specified by {@code row}
   * and {@code column} with this algorithm.
   *
   * @param sudoku A {@link Sudoku}
   * @param row    The row index of the {@link Cell}
   * @param column The column index of the {@link Cell}
   * @return {@code true} if a solution was found for the given {@link Cell}
   * with this algorithm.
   */
  protected abstract Cell executeAlgorithm(Sudoku sudoku, int row, int column);

  public Cell solve(Sudoku sudoku, final int row, final int column) {

    if (!sudoku.getCell(row, column).isFilled()) {
      //      sudoku.calculateHints();
      return executeAlgorithm(sudoku, row, column);
    }
    return null;
  }

}
