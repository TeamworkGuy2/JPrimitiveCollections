package twg2.collections.primitiveCollections.test;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import twg2.collections.primitiveCollections.IntArrayList;
import twg2.collections.primitiveCollections.IntListSorted;

/**
 * @author TeamworkGuy2
 * @since 2015-0-0
 */
public class PrimitiveListAndBagTest {


	@Test
	public void testListAndBag() {
		IntArrayList ints = new IntArrayList();
		IntListSorted intsSorted = new IntListSorted();
		ArrayList<Integer> intsColl = new ArrayList<>();

		Random rand = new Random();
		List<Map.Entry<String, int[]>> intGroups = new ArrayList<>();
		intGroups.add(entry("alpha", randomInts(rand, 10)));
		intGroups.add(entry("beta", randomInts(rand, 10)));
		intGroups.add(entry("gamma", randomInts(rand, 10)));
		intGroups.add(entry("delta", randomInts(rand, 10)));

		for(int i = 0, size = intGroups.size(); i < size; i++) {
			int[] intAry = intGroups.get(i).getValue();
			ints.addAll(intAry);
			intsSorted.addAll(intAry);

			// toArray()
			intAry = ints.toArray();
			Arrays.sort(intAry);
			Assert.assertArrayEquals(intAry, intsSorted.toArray());

			// addToCollection(), toList()
			ints.addToCollection(intsColl);
			Assert.assertArrayEquals(ints.toList().toArray(new Integer[0]), intsColl.toArray(new Integer[0]));

			// contains()
			Assert.assertTrue(ints.contains(intAry[0]));
			Assert.assertTrue(intsSorted.contains(intAry[0]));

			ints.clear();
			intsSorted.clear();
			intsColl.clear();

			Assert.assertTrue(ints.isEmpty());
			Assert.assertTrue(intsSorted.isEmpty());
			Assert.assertEquals(0, ints.size());
			Assert.assertEquals(0, intsSorted.size());
		}
	}


	private static int[] randomInts(final Random rand, final int count) {
		int[] val = new int[count];
		for(int i = 0; i < count; i++) {
			val[i] = (short)rand.nextInt();
		}
		return val;
	}


	// copy from JCollectionUtil
	private static <K, V> Map.Entry<K, V> entry(K key, V value) {
		return new AbstractMap.SimpleImmutableEntry<>(key, value);
	}

}
