package de.ahofi.sudoku;

import java.util.List;
import de.ahofi.sudoku.SudokuUtils;

public class Cell {

  public int number;

  public final int row;

  public final int column;

  public List<Integer> hints;

  /**
   * Creates a new Cell with the given number.
   *
   * @param number the number to set
   */
  public Cell(final int number, final int row, final int column) {
    this.number = number;
    this.row = row;
    this.column = column;
    this.hints = SudokuUtils.getSudokuValues();
  }

  public String getHintsLabel() {
    List<Integer> hints = this.hints;

    StringBuilder label = new StringBuilder();

    for (int i = 0; i < hints.size(); i++) {
      Integer hint = hints.get(i);
      if (i == 4) {
        label.append("\n");
      }
      label.append(hint);
    }
    return label.toString();
  }

  /**
   * Checks whether the cell is solved or not
   *
   * @return {@code true} if the cell is solved
   */
  public boolean isFilled() {
    return this.number != 0;
  }

  /**
   * Prints the number of the cell.
   *
   * @return the number or an empty string if the number is not filled yet
   */
  public String getNumberAsString() {
    return isFilled() ? String.valueOf(this.number) : "";
  }

  @Override
  public String toString() {
    return "\nrow: " + this.row + "\ncolumn: " + this.column + "\nnumber: " + this.number;
  }

}
