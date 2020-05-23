package com.java.scogen.dataGenerator;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class EmployeeNameList {
    static int counter = 0;

    public Set<String> setofUniqueNames() {
        Set<String> nameSet = new HashSet<>();
        while (nameSet.size() < 1_000)
        {
            nameSet.add(randomNameGenerator());
        }

        return nameSet;
    }

    public static String randomNameGenerator() {
        int leftLimit = 48;
        int rightLimit = 122;
        Random random = new Random();
        int targetStringLength = random.nextInt(10)+3;

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static String randomIDGenerator() {
        return Integer.toString(counter++);
    }

    public static int randomAgeGenerator() {
        int leftLimit = 18;
        int rightLimit = 42;
        Random random = new Random();

        return random.nextInt(rightLimit + 1)+leftLimit;
    }

}
