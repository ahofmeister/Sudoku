package de.ahofi.sudoku;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/sudokus")
public class SudokuResource {

  SudokuSolver sudokuSolver = new SudokuSolver();

  @POST
  @Path("create")
  @Produces(MediaType.APPLICATION_JSON)
  public Cell[][] create() {
    return sudokuSolver.createNew().getCells();
  }

  @POST
  @Path("solve")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Cell[][] solve(Cell[][] cells) {
    Sudoku sudoku = new Sudoku(cells);
    this.sudokuSolver.solve(sudoku);
    return sudoku.getCells();
  }
}
