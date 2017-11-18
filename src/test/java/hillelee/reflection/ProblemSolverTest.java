package hillelee.reflection;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Created by JavaEE on 28.10.2017.
 */
public class ProblemSolverTest {

    @Test
    public void solvePuzzle() throws Exception {
        String result = new ProblemSolver().solve(new Puzzle());
        assertThat(result, is("Correct answer"));
    }

}