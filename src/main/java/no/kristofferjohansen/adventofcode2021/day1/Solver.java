package no.kristofferjohansen.adventofcode2021.day1;

import no.kristofferjohansen.adventofcode2021.common.FileUtil;

import java.util.Date;
import java.util.List;

public class Solver {

    public Solver() {
        try {
            List<String> dataLines = FileUtil.readInputFile(this.getClass());
            int[] values = dataLines.stream().mapToInt(Integer::parseInt).toArray();
            Date start = new Date();
            System.out.println(solveFirst(values));
            System.out.printf("First puzzle took %d ms\n", new Date().getTime()-start.getTime());
            start = new Date();
            System.out.println(solveSecond(values));
            System.out.printf("Second puzzle took %d ms\n", new Date().getTime()-start.getTime());
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    static int solveFirst(int[] data) {
        int descents = 0;
        int lastValue = 0;

        for (int entry : data) {
            if (entry > lastValue) descents++;

            lastValue = entry;
        }

        return descents-1;
    }

    static int solveSecond(int[] data) {
        int descents = 0;
        int lastValue = 0;

        for (int i = 2; i < data.length; i++) {
            int newValue = data[i]+data[i-1]+data[i-2];

            if (newValue > lastValue) descents++;

            lastValue = newValue;
        }

        return descents-1;
    }

    public static void main(String[] args) {
        new Solver();
    }
}
