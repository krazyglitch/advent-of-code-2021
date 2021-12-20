package no.kristofferjohansen.adventofcode2021.day4;

import no.kristofferjohansen.adventofcode2021.common.FileUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

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
        return runGame(data, true);
    }

    static int solveSecond(List<String> data) {
        return runGame(data, false);
    }

    static int runGame(List<String> data, boolean getFirstWinner) {
        List<Integer> numbers = Arrays.stream(data.get(0).split(","))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());

        List<Card> gameCards = new ArrayList<>((data.size() - 1) / 6);
        for (int i = 6; i < data.size(); i += 6) {
            gameCards.add(new Card(i/6, data.subList(i - 4, i + 1)));
        }

        Card winningCard = getFirstWinner ? getFirstWinner(numbers, gameCards) : getLastWinner(numbers, gameCards);

        return winningCard != null ? winningCard.getUnpickedSum() * winningCard.getLastNumber() : 0;
    }

    public static void main(String[] args) {
        new Solver();
    }

    private static Card getFirstWinner(List<Integer> numbers, List<Card> gameCards) {
        for (int number : numbers) {
            for (Card gameCard : gameCards) {
                if (gameCard.pickNumber(number)) {
                    return gameCard;
                }
            }
        }

        return null;
    }

    private static Card getLastWinner(List<Integer> numbers, List<Card> gameCards) {
        HashSet<Integer> winningCardIds = new HashSet<>(gameCards.size());

        for (int number : numbers) {
            for (Card gameCard : gameCards) {
                if (winningCardIds.contains(gameCard.getId())) {
                    continue;
                }

                if (gameCard.pickNumber(number)) {
                    if (winningCardIds.size() == gameCards.size()-1) {
                        return gameCard;
                    }

                    winningCardIds.add(gameCard.getId());
                }
            }
        }

        return null;
    }
}

class Card {
    private final Square[][] squares;
    private final HashMap<Integer, Square> squareHashMap;
    private int lastNumber;
    private final int id;

    public Card(int id, List<String> cardString) {
        this.id = id;
        squares = new Square[5][5];
        squareHashMap = new HashMap<>(5*5);
        lastNumber = 0;

        for (int i = 0; i < cardString.size(); i++) {
            String[] splitRow = cardString.get(i).split("(?<!^) +");

            for (int j = 0; j < splitRow.length; j++) {
                Square square = new Square(splitRow[j], i, j);
                squares[i][j] = square;
                squareHashMap.put(square.getValue(), square);
            }
        }
    }

    public int getLastNumber() {
        return lastNumber;
    }

    public int getId() {
        return id;
    }

    private List<Square> getRow(int rowNum) {
        return Arrays.asList(squares[rowNum]);
    }

    private List<Square> getColumn(int colNum) {
        return Arrays.stream(squares).map(sg -> sg[colNum]).collect(Collectors.toList());
    }

    // Returns true if card wins
    public boolean pickNumber(int number) {
        if (squareHashMap.containsKey(number)) {
            Square square = squareHashMap.get(number);
            square.pick();
            lastNumber = number;

            return getColumn(square.getColumn()).stream().allMatch(Square::isPicked) ||
                    getRow(square.getRow()).stream().allMatch(Square::isPicked);
        }

        return false;
    }

    public int getUnpickedSum() {
        return squareHashMap.values().stream()
                .filter(s -> !s.isPicked())
                .mapToInt(Square::getValue)
                .sum();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Square[] row : squares) {
            for (Square square : row) {
                sb.append(square.toString()).append('\t');
            }

            sb.append("\r\n");
        }

        return sb.toString();
    }
}

class Square {
    private final int value;
    private final int row;
    private final int column;
    private boolean picked;

    public Square(String s, int row, int column) {
        this.value = Integer.parseInt(s.trim());
        this.row = row;
        this.column = column;
        this.picked = false;
    }

    public int getValue() {
        return value;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean isPicked() {
        return picked;
    }

    public void pick() {
        picked = true;
    }

    public String toString() {
        if (picked) {
            return value + "*";
        }

        return String.valueOf(value);
    }
}