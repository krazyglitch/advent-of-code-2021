package no.kristofferjohansen.adventofcode2021.day1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SolverTest {

    @Test
    void testFirstSimple() {
        int[] data = new int[]{199, 200, 208, 210, 200, 207, 240, 269, 260, 263};

        assertEquals(7, Solver.solveFirst(data));
    }

    @Test
    void testSecondSimple() {
        int[] data = new int[]{199, 200, 208, 210, 200, 207, 240, 269, 260, 263};

        assertEquals(5, Solver.solveSecond(data));
    }
}