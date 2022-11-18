package org.example;

import java.text.MessageFormat;

public class HillsAndWaterConstant {
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
        int leftMax = 0;
        int rightMax = 0;
        int left = 0;
        int right = landscape.length - 1;
        while (left < right) {
            if (landscape[left] < landscape[right]) {
                if (landscape[left] > leftMax) {
                    leftMax = landscape[left];
                } else {
                    water += leftMax - landscape[left];
                }
                left++;
            } else {
                if (landscape[right] > rightMax) {
                    rightMax = landscape[right];
                } else {
                    water += rightMax - landscape[right];
                }
                right--;
            }
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

}
