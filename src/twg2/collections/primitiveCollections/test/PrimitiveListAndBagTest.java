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
	private List<Map.Entry<String, int[]>> intGroups = new ArrayList<>();
	private List<Map.Entry<String, String>> strStrs = new ArrayList<>();
	private List<String> properties = new ArrayList<String>();
	private IntArrayList ints = new IntArrayList();


	@Test
	public void testListAndBag() {
		IntListSorted intsSorted = new IntListSorted();
		Random rand = new Random();
		intGroups.add(entry("alpha", randomInts(rand, 10)));
		intGroups.add(entry("beta", randomInts(rand, 10)));
		intGroups.add(entry("gamma", randomInts(rand, 10)));
		intGroups.add(entry("delta", randomInts(rand, 10)));

		StringBuilder strB = new StringBuilder();

		for(int i = 0, size = intGroups.size(); i < size; i++) {
			strStrs.add(entry(intGroups.get(i).getKey(), strB.append(intGroups.get(i).getKey()).reverse().toString()));
			strB.setLength(0);
			properties.add(intGroups.get(i).getKey());
			int[] intAry = intGroups.get(i).getValue();
			ints.addAll(intAry);
			intsSorted.addAll(intAry);

			Arrays.sort(intAry);
			Assert.assertArrayEquals(intsSorted.toArray(), intAry);

			ints.clear();
			intsSorted.clear();
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
