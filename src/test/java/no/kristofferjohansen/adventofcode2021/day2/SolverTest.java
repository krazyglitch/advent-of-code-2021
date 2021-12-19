package no.kristofferjohansen.adventofcode2021.day2;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolverTest {

    @Test
    void testFirstSimple() {
        List<String> movement = new ArrayList<>();
        movement.add("forward 5");
        movement.add("down 5");
        movement.add("forward 8");
        movement.add("up 3");
        movement.add("down 8");
        movement.add("forward 2");

        assertEquals(150, Solver.solveFirst(movement));
    }

    @Test
    void testFirstSecond() {
        List<String> movement = new ArrayList<>();
        movement.add("forward 5");
        movement.add("down 5");
        movement.add("forward 8");
        movement.add("up 3");
        movement.add("down 8");
        movement.add("forward 2");

        assertEquals(900, Solver.solveSecond(movement));
    }
}