package de.ahofi.sudoku;


import java.util.Objects;

/**
 * @author Alexander Hofmeister
 */
public class Sudoku {

  /** Size of a square */
  public static final int MAX_SQUARE = 3;

  /** Size of a row or column */
  public static final int MAX_ROW_COL = 9;

  private final Cell[][] cells;

  public static Cell[][] createEmpty() {
    Cell[][] cells = new Cell[MAX_ROW_COL][MAX_ROW_COL];
    for (int i = 0; i < MAX_ROW_COL; i++) {
      for (int j = 0; j < MAX_ROW_COL; j++) {
        cells[i][j] = new Cell(0, i, j);
      }
    }
    return cells;
  }

  public Sudoku() {
    this(createEmpty());
  }

  public Sudoku(Cell[][] cells) {
    Objects.requireNonNull(cells, "cells must not be null!");
    this.cells = cells;
  }

  /**
   * Returns the specific cell at position of rowIndex and columnIndex.
   *
   * @param rowIndex    the index of the row of the cell
   * @param columnIndex the index of the column of the cell
   * @return the found cell
   */
  public Cell getCell(final int rowIndex, final int columnIndex) {
    return this.cells[rowIndex][columnIndex];
  }

  /**
   * Returns an array of nine {@link Cell} objects representing a column in the
   * sudoku board.
   *
   * @param index of column (0-8)
   * @return array of exactly nine {@link Cell} objects or {@code null} if an
   * invalid parameter was passed.
   */
  public Cell[] getColumn(final int index) {
    if (index < 0 || index > 8) {
      throw new IllegalArgumentException("index must be between 0 and 8 but was " + index);
    }
    final Cell[] col = new Cell[MAX_ROW_COL];
    for (int row = 0; row < MAX_ROW_COL; row++) {
      col[row] = getCell(row, index);
    }
    return col;
  }

  /**
   * Returns the specific row.
   *
   * @param rowIndex the index of the row of the sudoku.
   * @return the row as array of {@link Cell cells.
   */
  public Cell[] getRow(final int rowIndex) {
    return this.cells[rowIndex];
  }

  /**
   * Returns a 2D-array containing one 3x3 square of the soduku board.
   *
   * @param rowIndex    index of row (0-8)
   * @param columnIndex index of column (0-8)
   * @return 2D-array of {@link Cell} objects
   */
  public Cell[][] getSquare(final int rowIndex, final int columnIndex) {
    if (rowIndex < 0 || rowIndex >= MAX_ROW_COL || columnIndex < 0 || columnIndex >= MAX_ROW_COL) {
      throw new IllegalArgumentException(
          "indices must be between 0 and 8 but were x: " + rowIndex + "and y: " + columnIndex);
    }
    final Cell[][] square = new Cell[MAX_SQUARE][MAX_SQUARE];

    final int topLeftCornerOfHouseRow = SudokuUtils.getTopLeftCornerOfHouse(rowIndex);
    final int topLeftCornerOfHouseColumn = SudokuUtils.getTopLeftCornerOfHouse(columnIndex);

    for (int row = 0; row < MAX_SQUARE; row++) {
      for (int column = 0; column < MAX_SQUARE; column++) {
        square[row][column] =
            getCell(topLeftCornerOfHouseRow + row, topLeftCornerOfHouseColumn + column);
      }
    }
    return square;
  }

  public boolean valueIsValid(final int rowIndex, final int columnIndex, final int value) {

    for (int k = 0; k < MAX_SQUARE; k++) {
      for (int l = 0; l < MAX_SQUARE; l++) {
        if (getCell(SudokuUtils.getTopLeftCornerOfHouse(rowIndex) + k,
            SudokuUtils.getTopLeftCornerOfHouse(columnIndex) + l).number == value) {
          return false;
        }
      }
    }

    for (int k = 0; k < MAX_ROW_COL; k++) {
      if (getCell(rowIndex, k).number == value) {
        return false;
      }
    }

    for (int k = 0; k < MAX_ROW_COL; k++) {
      if (getCell(k, columnIndex).number == value) {
        return false;
      }
    }

    return true;
  }

  public Cell[][] getCells() {
    return cells;
  }

}
