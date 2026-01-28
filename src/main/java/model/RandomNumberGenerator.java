package model;

import java.util.*;

public class RandomNumberGenerator {

    private final Random random;

    public RandomNumberGenerator() {
        this.random = new Random();
    }

    public BaseballNumber generate() {
        Set<Integer> uniqueNumbers = new LinkedHashSet<>();
        while (uniqueNumbers.size() < 3) {
            uniqueNumbers.add(random.nextInt(9) + 1);
        }

        return new BaseballNumber(new ArrayList<>(uniqueNumbers));
    }
}
