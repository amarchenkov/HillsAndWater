package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class HillsAndWaterConstantTest {
    private final HillsAndWaterConstant hillsAndWater = new HillsAndWaterConstant();

    @Test
    public void shouldCorrectReturnWaterAmount() {
        assertEquals(0, hillsAndWater.calculateWaterAmount(new int[]{}));
        assertEquals(0, hillsAndWater.calculateWaterAmount(null));
        assertEquals(9, hillsAndWater.calculateWaterAmount(new int[]{5, 2, 3, 4, 5, 4, 0, 3, 1}));
        assertEquals(6, hillsAndWater.calculateWaterAmount(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        assertEquals(0, hillsAndWater.calculateWaterAmount(new int[]{3, 2, 1}));
        assertEquals(0, hillsAndWater.calculateWaterAmount(new int[]{1, 2, 3}));
        assertEquals(0, hillsAndWater.calculateWaterAmount(new int[]{3, 3, 3, 3}));
        assertEquals(2, hillsAndWater.calculateWaterAmount(new int[]{2, 1, 1, 4}));
        assertEquals(2, hillsAndWater.calculateWaterAmount(new int[]{4, 1, 1, 2}));
        assertEquals(9, hillsAndWater.calculateWaterAmount(new int[]{4, 3, 2, 1, 2, 3, 4}));
        assertEquals(6, hillsAndWater.calculateWaterAmount(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        assertEquals(5, hillsAndWater.calculateWaterAmount(new int[]{3, 1, 2, 1, 5}));
        assertEquals(9, hillsAndWater.calculateWaterAmount(new int[]{4, 2, 0, 3, 2, 5}));
    }

    @Test
    public void shouldThrowExceptionOnValidation() {
        IllegalArgumentException expected =
                assertThrows(IllegalArgumentException.class, () -> hillsAndWater.calculateWaterAmount(new int[]{2, -1}));
        assertEquals("Landscape height must be in 0..32000. Incorrect height -1 on position 1", expected.getMessage());

        IllegalArgumentException expectedOnSize =
                assertThrows(IllegalArgumentException.class,
                        () -> hillsAndWater.calculateWaterAmount(new int[HillsAndWater.LIMIT + 1]));
        assertEquals("Max number of positions exceeded. Must be <= 32000. Got 32001", expectedOnSize.getMessage());
    }

}