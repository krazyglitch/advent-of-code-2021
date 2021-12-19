package no.kristofferjohansen.adventofcode2021.day2;

import no.kristofferjohansen.adventofcode2021.common.FileUtil;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        return solve(data, new Submarine(false));
    }

    static int solveSecond(List<String> data) {
        return solve(data, new Submarine(true));
    }

    private static int solve(List<String> data, Submarine sub) {
        Pattern movementPattern = Pattern.compile("^\\w+ (\\d+)$");

        for (String entry : data) {
            Matcher matcher = movementPattern.matcher(entry);

            if (matcher.find()) {
                switch (entry.charAt(0)) {
                    case 'f':
                        sub.moveHorizontal(Integer.parseInt(matcher.group(1)));
                        break;
                    case 'b':
                        sub.moveHorizontal(-Integer.parseInt(matcher.group(1)));
                        break;
                    case 'd':
                        sub.moveVertical(Integer.parseInt(matcher.group(1)));
                        break;
                    case 'u':
                        sub.moveVertical(-Integer.parseInt(matcher.group(1)));
                        break;
                    default:
                        break;
                }
            }
        }

        return sub.getMovedArea();
    }

    public static void main(String[] args) {
        new Solver();
    }
}

class Submarine {
    private int x, y, aim;
    private boolean aiming;

    public Submarine(boolean aiming) {
        x = 0;
        y = 0;
        aim = 0;

        this.aiming = aiming;
    }

    public void moveHorizontal(int value) {
        x += value;

        if (aiming) {
            y += aim * value;
        }
    }

    public void moveVertical(int value) {
        if (aiming) {
            aim += value;
        } else {
            y += value;
        }
    }

    public int getMovedArea() {
        return x*y;
    }

    @Override
    public String toString() {
        return "Submarine{" +
                "x=" + x +
                ", y=" + y +
                ", aim=" + aim +
                '}';
    }
}
