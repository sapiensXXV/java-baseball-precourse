package model;

import java.util.*;

public class RandomNumberGenerator {

    private static RandomNumberGenerator instance = new RandomNumberGenerator();
    private static Random random = new Random();
    private HashSet<Integer> numbers = new HashSet<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));


    private RandomNumberGenerator() {
    }

    public static RandomNumberGenerator getInstance() {
        return instance;
    }

    public BaseballNumber generate() {
        Set<Integer> uniqueNumbers = new LinkedHashSet<>();
        while (uniqueNumbers.size() < 3) {
            uniqueNumbers.add(random.nextInt(9) + 1);
        }

        return new BaseballNumber(new ArrayList<>(uniqueNumbers));
    }
}
