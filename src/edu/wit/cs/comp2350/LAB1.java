package edu.wit.cs.comp2350;

/* Sorts integers from command line using various algorithms 
 * 
 * Wentworth Institute of Technology
 * COMP 2350
 * Programming Assignment 0
 * 
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class LAB1 {
    ;
    public final static int MAX_INPUT = 524287;
    public final static int MIN_INPUT = 0;

    /**
     * A counting sort implementation
     *
     * @param a an array of integers
     * @return a sorted array of integers
     */
    public static int[] countingSort(int[] a) {

        int[] count = new int[MAX_INPUT];
        for (int i = 0; i < a.length; i++) {
            count[a[i]]++;
        }
        int[] sorted = new int[a.length];
        int index = 0;
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0) {
                for (int j = 0; j < count[i]; j++) {
                    sorted[index] = i;
                    index++;
                }
            }
        }
        return sorted;
        //return null;
    }

    /**
     * @param input The input to sort
     * @param pos   the position to sort from
     * @return a sorted array given the position
     */
    private static int[] stableCountingSort(int[] input, int pos) {
        int[] rtn = new int[input.length];  //return array
        int[] cnt = new int[10];            //the count array
        for (int anInput : input) {
            cnt[((anInput / pos) % 10)] += 1;
        }
        for (int i = 1; i < cnt.length; i++) {
            cnt[i] += cnt[i - 1];
        }
        for (int i = input.length - 1; i >= 0; i--) {
            int dig = ((input[i] / pos) % 10);
            rtn[cnt[dig] - 1] = input[i];
            cnt[dig]--;
        }
        return rtn;
    }

    /**
     * sorts an array using radix sort
     *
     * @param a The int array to sort
     * @return The sorted int array
     */
    public static int[] radixSort(int[] a) {
        for (int pos = 1; pos <= MAX_INPUT; pos *= 10) {
            a = stableCountingSort(a, pos);
        }
        return a;
    }

	/********************************************
	 * 
	 * You shouldn't modify anything past here
	 * 
	********************************************/
	
	// example sorting algorithm
	public static int[] insertionSort(int[] a) {

		for (int i = 1; i < a.length; i++) {
			int tmp = a[i];
			int j;
			for (j = i-1; j >= 0 && tmp < a[j]; j--)
				a[j+1] = a[j];
			a[j+1] = tmp;
		}
		
		return a;
	}

	/* Implementation note: The sorting algorithm is a Dual-Pivot Quicksort by Vladimir Yaroslavskiy,
	 *  Jon Bentley, and Joshua Bloch. This algorithm offers O(n log(n)) performance on many data 
	 *  sets that cause other quicksorts to degrade to quadratic performance, and is typically 
	 *  faster than traditional (one-pivot) Quicksort implementations. */
	public static int[] systemSort(int[] a) {
		Arrays.sort(a);
		return a;
	}

	// read ints from a Scanner
	// returns an array of the ints read
	private static int[] getInts(Scanner s) {
		ArrayList<Integer> a = new ArrayList<Integer>();

		while (s.hasNextInt()) {
			int i = s.nextInt();
			if ((i <= MAX_INPUT) && (i >= MIN_INPUT))
				a.add(i);
		}

		return toIntArray(a);
	}

	// copies an ArrayList to an array
	private static int[] toIntArray(ArrayList<Integer> a) {
		int[] ret = new int[a.size()];
		for(int i = 0; i < ret.length; i++)
			ret[i] = a.get(i);
		return ret;
	}

	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		System.out.printf("Enter the sorting algorithm to use ([c]ounting, [r]adix, [i]nsertion, or [s]ystem): ");
		char algo = s.next().charAt(0);
		
		System.out.printf("Enter the integers that you would like sorted: ");
		int[] unsorted_values = getInts(s);
		int[] sorted_values = {};

		s.close();

		switch (algo) {
		case 'c':
			sorted_values = countingSort(unsorted_values);
			break;
		case 'r':
			sorted_values = radixSort(unsorted_values);
			break;
		case 'i':
			sorted_values = insertionSort(unsorted_values);
			break;
		case 's':
			sorted_values = systemSort(unsorted_values);
			break;
		default:
			System.out.println("Invalid sorting algorithm");
			System.exit(0);
			break;
		}
		
		System.out.println(Arrays.toString(sorted_values));
		
	}

}
