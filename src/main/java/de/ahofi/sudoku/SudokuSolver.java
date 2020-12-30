package de.ahofi.sudoku;

import static de.ahofi.sudoku.SudokuUtils.MAX_ROW_COL;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SudokuSolver {

  //  public SudokuSolver(final int[][] matrix) {
  //    this.cells = new Cell[MAX_ROW_COL][MAX_ROW_COL];
  //    for (int i = 0; i < MAX_ROW_COL; i++) {
  //      for (int j = 0; j < MAX_ROW_COL; j++) {
  //        this.cells[i][j] = new Cell(matrix[i][j], i, j);
  //      }
  //    }
  //    calculateHints();
  //  }

  //  /** Calculates all hints for all cells. */
  //  public void calculateHints(Cell[][] cells) {
  //    for (int i = 0; i < MAX_ROW_COL; i++) {
  //      for (int j = 0; j < MAX_ROW_COL; j++) {
  //        for (final Integer number : SudokuUtils.getSudokuValues()) {
  //          if (!valueIsValid(cells, i, j, number)) {
  //            cells[i][j].hints.remove(number);
  //          }
  //        }
  //      }
  //
  //    }
  //  }

  public Sudoku createNew() {
    Sudoku sudoku = new Sudoku();

    solve(sudoku);

    for (int countZeros = 0; countZeros < 40; countZeros++) {
      int rowRandom;
      int columnRandom;
      do {
        rowRandom = ThreadLocalRandom.current().nextInt(0, MAX_ROW_COL);
        columnRandom = ThreadLocalRandom.current().nextInt(0, MAX_ROW_COL);
      } while (sudoku.getCell(rowRandom, columnRandom).number == 0);
      sudoku.getCell(rowRandom, columnRandom).number = 0;
    }
    return sudoku;
    //    calculateHints(cells);
  }

  /** Prints the sudoku as input for our tests */
  public void printDebug(Cell[][] cells) {

    System.out.println("final int[][] matrix = new int[][] { //");

    for (int k = 0; k < MAX_ROW_COL; k++) {
      System.out.print("{ ");
      for (int l = 0; l < MAX_ROW_COL; l++) {
        System.out.print(cells[k][l].number);
        if (l != MAX_ROW_COL - 1) {
          System.out.print(", ");
        }
      }
      System.out.print("}");
      if (k != MAX_ROW_COL - 1) {
        System.out.print(", //");
      } else {
        System.out.println(" };");
      }
      System.out.println();
    }

  }

  /**
   * Solves the current sudoku.
   *
   * @param sudoku
   * @return {@code true}, if the sudoku could be solved.
   */
  public boolean solve(Sudoku sudoku) {
    return solve(sudoku, 0, 0);
  }

  private boolean solve(Sudoku sudoku, final int row, final int column) {
    int rowIndex = row;
    int columnIndex = column;
    if (rowIndex == MAX_ROW_COL) {
      rowIndex = 0;
      if (++columnIndex == MAX_ROW_COL) {
        return true;
      }
    }

    if (sudoku.getCell(rowIndex, columnIndex).number != 0) {
      return solve(sudoku, rowIndex + 1, columnIndex);
    }

    final List<Integer> values = SudokuUtils.getSudokuValues();
    final Random random = new Random();
    while (values.size() > 0) {
      final int value = values.get(random.nextInt(values.size()));
      if (sudoku.valueIsValid(rowIndex, columnIndex, value)) {
        sudoku.getCell(rowIndex, columnIndex).number = value;

        if (solve(sudoku, rowIndex + 1, columnIndex)) {
          return true;
        }
      }
      values.remove(new Integer(value));
    }

    sudoku.getCell(rowIndex, columnIndex).number = 0;
    return false;
  }

}
