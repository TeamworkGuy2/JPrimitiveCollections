package twg2.collections.primitiveCollections.test;

import org.junit.Assert;
import org.junit.Test;

import twg2.collections.primitiveCollections.IntListSorted;

/**
 * @author TeamworkGuy2
 * @since 2015-9-17
 */
public class PrimitiveSortedListTest {

	@Test
	public void testSortedOrder() {
		int[] input = new int[] { 85, 2, 93, 11, 39, 45, -2 };
		int[] expect = new int[] { -2, 2, 11, 39, 45, 85, 93 };

		IntListSorted sorted = IntListSorted.of(input);
		int i = 0;
		for(int val : sorted) {
			Assert.assertEquals("iterator index " + i + ": ", expect[i], val);
			i++;
		}

		Assert.assertArrayEquals(expect, sorted.toArray());
	}

}
