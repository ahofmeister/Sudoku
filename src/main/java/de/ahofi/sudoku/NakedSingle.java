package de.ahofi.sudoku;

import java.util.List;

public class NakedSingle extends AbstractSolveAlgorithm {

  @Override
  protected Cell executeAlgorithm(final Sudoku sudoku, final int rowIndex, final int columIndex) {
    final List<Integer> remainingValues = SudokuUtils.getSudokuValues();

    for (int i = 0; i < SudokuUtils.MAX_ROW_COL; i++) {
      final int value = sudoku.getCell(rowIndex, i).number;
      if (value != 0 && remainingValues.contains(value)) {
        remainingValues.remove((Integer) value);
      }
    }
    for (int i = 0; i < SudokuUtils.MAX_ROW_COL; i++) {
      final int value = sudoku.getCell(i, columIndex).number;
      if (value != 0 && remainingValues.contains(value)) {
        remainingValues.remove((Integer) value);
      }
    }

    for (int k = 0; k < SudokuUtils.MAX_SQUARE; k++) {
      for (int l = 0; l < SudokuUtils.MAX_SQUARE; l++) {
        final int value = sudoku.getCell(SudokuUtils.getTopLeftCornerOfHouse(rowIndex) + k,
            SudokuUtils.getTopLeftCornerOfHouse(columIndex) + l).number;
        if (value != 0 && remainingValues.contains(value)) {
          remainingValues.remove((Integer) value);
        }
      }
    }

    if (remainingValues.size() == 1) {
      return new Cell(remainingValues.get(0), rowIndex, columIndex);
    }

    return null;
  }

}
