package edu.wit.cs.comp2350.tests;

import edu.wit.cs.comp2350.LAB1;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * Tests for the lab
 */
public class Tests {
    //region helper methods
    private static int[] generateUnsortedArray() {
        Random r = new Random();
        int[] test = new int[256];
        for (int i = 0; i < 256; i++) {
            test[i] = r.nextInt(LAB1.MAX_INPUT);
        }
        return test;
    }

    @Test
    public void testCountingSort() {
        Assert.assertArrayEquals(new int[]{1, 10, 100, 1000}, LAB1.countingSort(new int[]{1000, 100, 10, 1}));
    }

    @Test
    public void testRadixSort() {
        Assert.assertArrayEquals(new int[]{1, 10, 100, 1000}, LAB1.radixSort(new int[]{1000, 100, 10, 1}));
    }

    @Test
    public void testRandomCountingSort() {
        int[] rand = generateUnsortedArray();
        int[] sorted = Arrays.copyOf(rand, 256);
        Arrays.sort(sorted);
        int[] out = LAB1.countingSort(rand);
        Assert.assertArrayEquals(sorted, out);
    }

    @Test
    public void testRandomRadixSort() {
        int[] rand = generateUnsortedArray();
        int[] sorted = Arrays.copyOf(rand, 256);
        Arrays.sort(sorted);
        int[] out = LAB1.radixSort(rand);
        Assert.assertArrayEquals(sorted, out);
    }
    //endregion
}
