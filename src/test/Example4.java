package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import sortedlist.SortedArrayList;

public class Example4 {

	public static void test_1(int size) {
		Random rand = new Random();

		long start = System.currentTimeMillis();
		
		SortedArrayList<Integer> list = new SortedArrayList<Integer>(size);
		for (int i = 0; i < size; i++)
			list.addEntry(rand.nextInt(size));

		long stop = System.currentTimeMillis();
		System.out.println("SortedArrayList: " + (stop - start) + " msec");
	}
	
	public static void test_2(int size) {
		Random rand = new Random();

		long start = System.currentTimeMillis();

		ArrayList<Integer> list = new ArrayList<Integer>(size);
		for (int i = 0; i < size; i++)
			list.add(rand.nextInt(size));
		
		Collections.sort(list);
		
		long stop = System.currentTimeMillis();
		System.out.println("ArrayList+Sort: " + (stop - start) + " msec");
	}

	public static void main(String[] args) {
		int size = 100000;

		test_1(size);
		test_2(size);
	}

}
