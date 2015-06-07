package test;

import java.util.ArrayList;

import primitiveCollections.IntArrayList;

/**
 * @author TeamworkGuy2
 * @since 2015-0-0
 */
public class PerformanceTests {


	// TODO convert to Assert test
	public int testIntListDefaultPerformance() {
		ArrayList<Integer> list = new ArrayList<Integer>(256);
		IntArrayList ints = new IntArrayList(256);
		int result = 0;
		int loops = 10000;
		int size = 1000;
		long timeList = 0;
		long timeInts = 0;
		long time = 0;
		for(int i = 0; i < size; i++) {
			list.add(i);
			ints.add(i);
		}
		time = System.nanoTime();
		for(int i = 0; i < loops; i++) {
			size = ints.size();
			for(int a = 0; a < size; a++) {
				result = ints.get(a);
			}
			timeInts -= time - (time = System.nanoTime());
			size = list.size();
			for(int a = 0; a < size; a++) {
				result = list.get(a);
			}
			timeList -= time - (time = System.nanoTime());
		}
		System.out.println("List time: " + timeList + ", ints time: " + timeInts);
		return result;
	}


}
