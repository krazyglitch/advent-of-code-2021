package no.kristofferjohansen.adventofcode2021.day3;

import no.kristofferjohansen.adventofcode2021.common.FileUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solver {

    public Solver() {
        try {
            List<String> dataLines = FileUtil.readInputFile(this.getClass());
            Date start = new Date();
            System.out.println(solveFirst(dataLines));
            System.out.printf("First puzzle took %d ms\n", new Date().getTime()-start.getTime());
            start = new Date();
            System.out.println(solveSecond(dataLines));
            System.out.printf("Second puzzle took %d ms\n", new Date().getTime()-start.getTime());
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    static int solveFirst(List<String> data) {
        int entryLength = data.size();
        int bitLength = data.get(0).length();
        int halfwayPoint = (int) Math.floor(entryLength/2.0);

        char[] gammaArr = new char[bitLength];
        Arrays.fill(gammaArr, '0');

        int[] bitCount = new int[bitLength];
        boolean[] countDone = new boolean[bitLength];

        for (int i = 0; i < entryLength; i++) {
            String entry = data.get(i);

            for (int j = 0; j < bitLength; j++) {
                if (countDone[j]) continue;

                if (entry.charAt(j) == '1') {
                    bitCount[j]++;
                }

                if (bitCount[j] > halfwayPoint) {
                    countDone[j] = true;
                    gammaArr[j] = '1';
                } else if (i - bitCount[j] > halfwayPoint) {
                    countDone[j] = true;
                }
            }
        }

        int bitMask = Integer.parseInt("1".repeat(bitLength), 2);

        String gammaStr = new String(gammaArr);

        int gamma = Integer.parseInt(gammaStr, 2);
        int epsilon = ~gamma & bitMask;

        return gamma * epsilon;
    }

    static int solveSecond(List<String> data) {
        String oxygenString = reduceList(data, 0, true).get(0);
        String co2String = reduceList(data, 0, false).get(0);

        return Integer.parseInt(oxygenString, 2) * Integer.parseInt(co2String, 2);
    }

    private static boolean bitIsAtPosition(String byteStr, char bit, int position) {
        return byteStr.charAt(position) == bit;
    }

    private static List<String> reduceList(List<String> input, int position, boolean useMostCommon) {
        if (input.size() == 1) {
            return input;
        }

        List<StringBuilder> verticalList = buildVerticalList(input);
        boolean mostCommonIsOne = isMostCommonBitOne(verticalList.get(position).toString());
        char selectedBit;
        if (useMostCommon) {
            selectedBit = mostCommonIsOne ? '1' : '0';
        } else {
            selectedBit = mostCommonIsOne ? '0' : '1';
        }

        int finalPosition = position;
        List<String> reducedList = input.stream()
                .filter(s -> bitIsAtPosition(s, selectedBit, finalPosition))
                .collect(Collectors.toList());

        return reduceList(reducedList, ++position, useMostCommon);
    }

    private static List<StringBuilder> buildVerticalList(List<String> input) {
        List<StringBuilder> verticalData = new ArrayList<>();
        IntStream.range(0, input.get(0).length()).forEach(i -> verticalData.add(new StringBuilder()));

        for (String entry : input) {
            for (int j = 0; j < entry.length(); j++) {
                verticalData.get(j).append(entry.charAt(j));
            }
        }

        return verticalData;
    }

    private static boolean isMostCommonBitOne(String byteStr) {
        return byteStr.chars().filter(c -> c == '1').count() >= (Math.ceil(byteStr.length()/2.0));
    }

    public static void main(String[] args) {
        new Solver();
    }
}