package no.kristofferjohansen.adventofcode2021.day4;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolverTest {

    @Test
    void testFirstSimple() {
        ArrayList<String> data = new ArrayList<>();
        data.add("7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1");
        data.add("");
        data.add("22 13 17 11  0");
        data.add("8  2 23  4 24");
        data.add("21  9 14 16  7");
        data.add("6 10  3 18  5");
        data.add("1 12 20 15 19");
        data.add("");
        data.add("3 15  0  2 22");
        data.add("9 18 13 17  5");
        data.add("19  8  7 25 23");
        data.add("20 11 10 24  4");
        data.add("14 21 16 12  6");
        data.add("");
        data.add("14 21 17 24  4");
        data.add("10 16 15  9 19");
        data.add("18  8 23 26 20");
        data.add("22 11 13  6  5");
        data.add("2  0 12  3  7");

        assertEquals(4512, Solver.solveFirst(data));
    }

    @Test
    void testSecondSimple() {
        ArrayList<String> data = new ArrayList<>();
        data.add("7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1");
        data.add("");
        data.add("22 13 17 11  0");
        data.add("8  2 23  4 24");
        data.add("21  9 14 16  7");
        data.add("6 10  3 18  5");
        data.add("1 12 20 15 19");
        data.add("");
        data.add("3 15  0  2 22");
        data.add("9 18 13 17  5");
        data.add("19  8  7 25 23");
        data.add("20 11 10 24  4");
        data.add("14 21 16 12  6");
        data.add("");
        data.add("14 21 17 24  4");
        data.add("10 16 15  9 19");
        data.add("18  8 23 26 20");
        data.add("22 11 13  6  5");
        data.add("2  0 12  3  7");

        assertEquals(1924, Solver.solveSecond(data));
    }
}