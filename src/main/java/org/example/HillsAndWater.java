package org.example;

import java.text.MessageFormat;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Stream;

public class HillsAndWater {

    public static final int LIMIT = 32000;

    /**
     * Memory - O(n), Time - O(n)
     */
    long calculateWaterAmount(int[] landscape) {
        if (landscape == null || landscape.length == 0) {
            return 0;
        }
        validateLandscape(landscape);
        int water = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < landscape.length; i++) {
            while (!stack.isEmpty() && landscape[stack.peek()] < landscape[i]) {
                int head = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int width = i - stack.peek() - 1;
                int height = Math.min(landscape[stack.peek()], landscape[i]) - landscape[head];
                water += width * height;
            }
            stack.push(i);
        }
        return water;
    }

    private void validateLandscape(int[] landscape) {
        if (landscape.length > LIMIT) {
            throw new IllegalArgumentException("Max number of positions exceeded. Must be <= 32000. Got " + landscape.length);
        }
        for (int i = 0; i < landscape.length; i++) {
            if (landscape[i] < 0 || landscape[i] > LIMIT) {
                throw new IllegalArgumentException(
                        MessageFormat.format("Landscape height must be in 0..32000. Incorrect height {0} on position {1}",
                                landscape[i], i));
            }
        }
    }

    public static void main(String[] args) {
        try {
            int[] landscape = Stream.of(args).mapToInt(Integer::parseInt).toArray();
            System.out.println(new HillsAndWater().calculateWaterAmount(landscape));
        } catch (IllegalArgumentException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
