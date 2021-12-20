package no.kristofferjohansen.adventofcode2021.day3;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolverTest {

    @Test
    void testFirstSimple() {
        List<String> data = new ArrayList<>();
        data.add("00100");
        data.add("11110");
        data.add("10110");
        data.add("10111");
        data.add("10101");
        data.add("01111");
        data.add("00111");
        data.add("11100");
        data.add("10000");
        data.add("11001");
        data.add("00010");
        data.add("01010");

        assertEquals(198, Solver.solveFirst(data));
    }

    @Test
    void testSecondSimple() {
        List<String> data = new ArrayList<>();
        data.add("00100");
        data.add("11110");
        data.add("10110");
        data.add("10111");
        data.add("10101");
        data.add("01111");
        data.add("00111");
        data.add("11100");
        data.add("10000");
        data.add("11001");
        data.add("00010");
        data.add("01010");

        assertEquals(230, Solver.solveSecond(data));
    }
}