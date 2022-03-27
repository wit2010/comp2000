package test;

import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

import dictionary.ArrayDictionary;
import dictionary.Dictionary;
import dictionary.SortedLinkedDictionary;

public class Example5 {

	public static void test_1(int size) {
		Random rand = new Random();

		long t1 = System.currentTimeMillis();
		
		Dictionary<Integer,Object> dictionary = new ArrayDictionary<>(size);
		for (int i = 0; i < size; i++)
			dictionary.add(rand.nextInt(size), new Object());

		long t2 = System.currentTimeMillis();

		for (int i = 0; i < size; i++)
			dictionary.getValue(rand.nextInt(size));

		long t3 = System.currentTimeMillis();

		System.out.println("ArrayDictionary: " + (t2 - t1) + ", " + (t3 - t2) + " msec");
	}
	
	public static void test_2(int size) {
		Random rand = new Random();

		long t1 = System.currentTimeMillis();

		Dictionary<Integer,Object> dictionary = new SortedLinkedDictionary<>();
		for (int i = 0; i < size; i++)
			dictionary.add(rand.nextInt(size), new Object());
		
		long t2 = System.currentTimeMillis();

		for (int i = 0; i < size; i++)
			dictionary.getValue(rand.nextInt(size));

		long t3 = System.currentTimeMillis();

		System.out.println("SortedLinkedDictionary: " + (t2 - t1) + ", " + (t3 - t2) + " msec");
	}

	public static void test_3(int size) {
		Random rand = new Random();

		long t1 = System.currentTimeMillis();

		Map<Integer,Object> dictionary = new Hashtable<>(size);
		for (int i = 0; i < size; i++)
			dictionary.put(rand.nextInt(size), new Object());
		
		long t2 = System.currentTimeMillis();

		for (int i = 0; i < size; i++)
			dictionary.get(rand.nextInt(size));

		long t3 = System.currentTimeMillis();

		System.out.println("Hashtable: " + (t2 - t1) + ", " + (t3 - t2) + " msec");
	}

	public static void main(String[] args) {
		int size = 100000;

		test_1(size);
		test_2(size);
		test_3(size);
	}

}
