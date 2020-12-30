package de.ahofi.sudoku;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;

public class FullHouse extends AbstractSolveAlgorithm {

  @Inject
  SudokuUtils sudokuUtils;

  @Override
  protected Cell executeAlgorithm(final Sudoku sudoku, final int rowIndex, final int columnIndex) {

    final List<Cell> cells = Arrays.stream(sudoku.getSquare(rowIndex, columnIndex)).flatMap(Arrays::stream).collect(Collectors.toList());

    final List<Integer> numbersOfSquare = cells.stream().map(c -> c.number).collect(Collectors.toList());
    cells.removeIf(Cell::isFilled);

    if (cells.size() == 1) {
      final List<Integer> possibleValues = SudokuUtils.getSudokuValues();
      possibleValues.removeAll(numbersOfSquare);
      final Cell cell = cells.get(0);
      cell.number = possibleValues.get(0);
      return cell;
    }
    return null;
  }
}
