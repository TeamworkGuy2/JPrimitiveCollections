package twg2.collections.primitiveCollections.test;

import org.junit.Assert;
import org.junit.Test;

import twg2.collections.primitiveCollections.IntListSorted;

/**
 * @author TeamworkGuy2
 * @since 2015-9-17
 */
public class PrimitiveListSortedTest {

	@Test
	public void sortOrderTest() {
		int[] input = new int[] { 85, 2, 93, 11, 39, 45, -2 };
		int[] expect = new int[] { -2, 2, 11, 39, 45, 85, 93 };

		IntListSorted list = IntListSorted.of(input);
		int i = 0;
		for(int val : list) {
			Assert.assertEquals("iterator index " + i + ": ", expect[i], val);
			i++;
		}

		Assert.assertArrayEquals(expect, list.toArray());
	}


	@Test
	public void indexOfAndLastTest() {
		IntListSorted list = IntListSorted.of();
		Assert.assertEquals(-1, list.indexOf(5));
		Assert.assertEquals(-1, list.lastIndexOf(5));

		list = IntListSorted.of(5, 5, 6);
		Assert.assertEquals(-1, list.indexOf(7));
		Assert.assertEquals(-1, list.lastIndexOf(7));

		list = IntListSorted.of(5, 5, 6);
		Assert.assertEquals(-1, list.indexOf(4));
		Assert.assertEquals(-1, list.lastIndexOf(4));

		list = IntListSorted.of(5, 5, 6, 7);
		Assert.assertEquals(0, list.indexOf(5));
		Assert.assertEquals(1, list.lastIndexOf(5));

		list = IntListSorted.of(5);
		Assert.assertEquals(0, list.indexOf(5));
		Assert.assertEquals(0, list.lastIndexOf(5));

		list = IntListSorted.of(5, 6);
		Assert.assertEquals(1, list.indexOf(6));
		Assert.assertEquals(1, list.lastIndexOf(6));

		list = IntListSorted.of(5, 5, 6, 6, 6);
		Assert.assertEquals(2, list.indexOf(6));
		Assert.assertEquals(4, list.lastIndexOf(6));

		list = IntListSorted.of(5, 6, 6, 6, 7, 8, 9);
		Assert.assertEquals(1, list.indexOf(6));
		Assert.assertEquals(3, list.lastIndexOf(6));

		list = IntListSorted.of(5, 5, 6, 6, 6, 7, 8, 9);
		Assert.assertEquals(2, list.indexOf(6));
		Assert.assertEquals(4, list.lastIndexOf(6));
	}


	public void binarySearchTest() {
		IntListSorted list = IntListSorted.of();
		Assert.assertEquals(-1, list.binarySearch(-1));
		Assert.assertEquals(-1, list.binarySearch(0));
		Assert.assertEquals(-1, list.binarySearch(5));

		list = IntListSorted.of(5, 4, 2); // [2, 4, 5]
		Assert.assertEquals(-1, list.binarySearch(-1));
		Assert.assertEquals(-2, list.binarySearch(3));
		Assert.assertEquals(1, list.binarySearch(4));
		Assert.assertEquals(2, list.binarySearch(5));
		Assert.assertEquals(-3, list.binarySearch(6));
	}

}
