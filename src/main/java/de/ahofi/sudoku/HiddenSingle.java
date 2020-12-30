package de.ahofi.sudoku;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HiddenSingle extends AbstractSolveAlgorithm {

  @Override
  protected Cell executeAlgorithm(final Sudoku sudoku, final int rowIndex, final int columnIndex) {

    final Cell[][] square = sudoku.getSquare(rowIndex, columnIndex);

    // List of cells we will remove later on to determine which cell is already
    // filled out or blocked by a number in an other row or column
    List<Cell> cells = Arrays.stream(square).flatMap(Arrays::stream).collect(Collectors.toList());

    for (final Integer number : SudokuUtils.getSudokuValues()) {
      cells = Arrays.stream(square).flatMap(Arrays::stream).collect(Collectors.toList());
      if (cells.stream().noneMatch(cell -> cell.number == number)) {
        cells.removeIf(Cell::isFilled);
        // remove any cell that is already filled out

        for (int i = 0; i < SudokuUtils.MAX_SQUARE; i++) {
          final int rowIndexTopLeftCorner = SudokuUtils.getTopLeftCornerOfHouse(rowIndex) + i;
          final int columnIndexTopLeftCorner = SudokuUtils.getTopLeftCornerOfHouse(columnIndex) + i;

          // Check row and remove all cells with this index
          removeLine(cells, number, sudoku.getRow(rowIndexTopLeftCorner),
              cell -> cell.row == rowIndexTopLeftCorner);

          // Check column and remove all cells with this index
          removeLine(cells, number, sudoku.getColumn(columnIndexTopLeftCorner),
              cell -> cell.column == columnIndexTopLeftCorner);

        }

        if (cells.size() == 1) {
          final Cell cell = cells.stream().findFirst().get();
          cell.number = number;
          return cell;
        }
      }
    }
    return null;
  }

  private static void removeLine(final List<Cell> cells, final Integer number, final Cell[] line,
      final Predicate<? super Cell> predicate) {
    if (Arrays.stream(line).anyMatch(lineIteration -> lineIteration.number == number)) {
      cells.removeIf(predicate);
    }
  }
}
