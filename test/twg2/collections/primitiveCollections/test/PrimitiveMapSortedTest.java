package twg2.collections.primitiveCollections.test;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.Test;

import twg2.collections.primitiveCollections.IntMapSorted;

/**
 * @author TeamworkGuy2
 * @since 2015-9-17
 */
public class PrimitiveMapSortedTest {

	@Test
	public void sortOrderTest() {
		List<Entry<Integer, String>> expect = Arrays.asList(et(-2, "A"), et(2, "B"), et(11, "C"), et(39, "D"), et(45, "E"), et(85, "F"), et(93, "G"));

		IntMapSorted<String> map = IntMapSorted.of(et(85, "F"), et(2, "B"), et(93, "G"), et(11, "C"), et(39, "D"), et(45, "E"), et(-2, "A"));
		for(int i = 0, size = map.size(); i < size; i++) {
			int key = map.getKey(i);
			String value = map.getValue(i);
			Assert.assertEquals("iterator key index " + i + ": ", (int)expect.get(i).getKey(), key);
			Assert.assertEquals("iterator value index " + i + ": ", expect.get(i).getValue(), value);
		}
	}


	@Test
	public void indexOfKeyAndLastTest() {
		IntMapSorted<String> list = IntMapSorted.of();
		Assert.assertEquals(-1, list.indexOfKey(5));
		Assert.assertEquals(-1, list.lastIndexOfKey(5));

		list = IntMapSorted.of(et(5, "A"), et(5, "A"), et(6, "B"));
		Assert.assertEquals(-1, list.indexOfKey(7));
		Assert.assertEquals(-1, list.lastIndexOfKey(7));

		list = IntMapSorted.of(et(5, "A"), et(5, "A"), et(6, "B"));
		Assert.assertEquals(-1, list.indexOfKey(4));
		Assert.assertEquals(-1, list.lastIndexOfKey(4));

		list = IntMapSorted.of(et(5, "A"));
		Assert.assertEquals(0, list.indexOfKey(5));
		Assert.assertEquals(0, list.lastIndexOfKey(5));

		list = IntMapSorted.of(et(5, "A"), et(6, "B"));
		Assert.assertEquals(1, list.indexOfKey(6));
		Assert.assertEquals(1, list.lastIndexOfKey(6));

		list = IntMapSorted.of(et(5, "A"), et(6, "B"), et(7, "C"), et(8, "D"), et(9, "E"));
		Assert.assertEquals(1, list.indexOfKey(6));
		Assert.assertEquals(1, list.lastIndexOfKey(6));
	}


	private static <K, V> Entry<K, V> et(K key, V val) {
		return new AbstractMap.SimpleImmutableEntry<K, V>(key, val);
	}

}
