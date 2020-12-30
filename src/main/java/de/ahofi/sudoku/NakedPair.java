package de.ahofi.sudoku;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NakedPair extends AbstractHintEliminationAlgorithm {

  @Override
  public void eliminate(final Sudoku sudoku) {

    for (int i = 0; i < SudokuUtils.MAX_ROW_COL; i++) {
      eliminateHints(sudoku.getColumn(i));
      eliminateHints(sudoku.getRow(i));
    }

  }

  private void eliminateHints(final Cell[] line) {
    // copy line
    final List<Cell> lineAsList = Arrays.stream(line).collect(Collectors.toList());
    lineAsList.removeIf(cell -> cell.hints.size() > 2 || cell.isFilled());
    if (lineAsList.size() == 2) {
      if (lineAsList.get(0).hints.equals(lineAsList.get(1).hints)) {
        // operate on the original line and remove all matching hints
        Arrays.stream(line).forEach(cell -> cell.hints.removeAll(lineAsList.get(0).hints));
      }
    }
  }
}
