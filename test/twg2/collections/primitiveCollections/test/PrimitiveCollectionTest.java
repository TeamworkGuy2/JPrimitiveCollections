package twg2.collections.primitiveCollections.test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

import twg2.collections.primitiveCollections.CharArrayList;
import twg2.collections.primitiveCollections.CharBag;
import twg2.collections.primitiveCollections.CharListSorted;
import twg2.collections.primitiveCollections.CharMapSorted;
import twg2.collections.primitiveCollections.FloatArrayList;
import twg2.collections.primitiveCollections.FloatBag;
import twg2.collections.primitiveCollections.FloatListSorted;
import twg2.collections.primitiveCollections.FloatMapSorted;
import twg2.collections.primitiveCollections.IntArrayList;
import twg2.collections.primitiveCollections.IntBag;
import twg2.collections.primitiveCollections.IntList;
import twg2.collections.primitiveCollections.IntListSorted;
import twg2.collections.primitiveCollections.IntMapSorted;

/**
 * @author TeamworkGuy2
 * @since 2014-12-26
 */
public class PrimitiveCollectionTest {

	@Test
	public void primitiveArrayListTest() {
		{
			CharArrayList cList = new CharArrayList();
			char[] chs = new char[] { 0, 5, 22, 105 };
			char[] chs2 = new char[] { 1, 5, 3, 2 };
			cList.addAll(chs);
			for(char ch : chs) {
				Assert.assertTrue(cList.contains(ch));
				cList.removeValue(ch);
			}
			Assert.assertTrue(cList.size() == 0);
			cList.add(0, chs2[0]);
			cList.add(1, chs2[1]);
			cList.add(2, chs2[2]);
			cList.set(1, chs2[3]);
			Assert.assertTrue(cList.indexOf(chs2[2]) == 2);
			Assert.assertTrue(cList.get(1) == chs2[3]);
		}

		{
			IntArrayList iList = new IntArrayList();
			int[] ins = new int[] { 0, 5, 22, 105 };
			int[] ins2 = new int[] { 1, 5, 3, 2 };
			iList.addAll(ins);
			for(int in : ins) {
				Assert.assertTrue(iList.contains(in));
				iList.removeValue(in);
			}
			Assert.assertTrue(iList.size() == 0);
			iList.add(0, ins2[0]);
			iList.add(1, ins2[1]);
			iList.add(2, ins2[2]);
			iList.set(1, ins2[3]);
			Assert.assertTrue(iList.indexOf(ins2[2]) == 2);
			Assert.assertTrue(iList.get(1) == ins2[3]);
		}

		{
			FloatArrayList fList = new FloatArrayList();
			float[] fls = new float[] { 0, 5, 22, 105 };
			float[] fls2 = new float[] { 1, 5, 3, 2 };
			fList.addAll(fls);
			for(float fl : fls) {
				Assert.assertTrue(fList.contains(fl));
				fList.removeValue(fl);
			}
			Assert.assertTrue(fList.size() == 0);
			fList.add(0, fls2[0]);
			fList.add(1, fls2[1]);
			fList.add(2, fls2[2]);
			fList.set(1, fls2[3]);
			Assert.assertTrue(fList.indexOf(fls2[2]) == 2);
			Assert.assertTrue(fList.get(1) == fls2[3]);
		}
	}


	@Test
	public void primitiveListSortedTest() {
		{
			CharListSorted cList = new CharListSorted();
			char[] chs = new char[] { 0, 5, 22, 105 };
			cList.addAll(chs);
			Assert.assertTrue(cList.indexOf(chs[2]) == 2);
			for(char ch : chs) {
				Assert.assertTrue(cList.contains(ch));
				cList.removeValue(ch);
			}
			Assert.assertTrue(cList.size() == 0);
		}

		{
			IntListSorted iList = new IntListSorted();
			int[] ins = new int[] { 0, 5, 22, 105 };
			iList.addAll(ins);
			Assert.assertTrue(iList.indexOf(ins[2]) == 2);
			for(int in : ins) {
				Assert.assertTrue(iList.contains(in));
				iList.removeValue(in);
			}
			Assert.assertTrue(iList.size() == 0);
		}

		{
			FloatListSorted fList = new FloatListSorted();
			float[] fls = new float[] { 0, 5, 22, 105 };
			fList.addAll(fls);
			Assert.assertTrue(fList.indexOf(fls[2]) == 2);
			for(float fl : fls) {
				Assert.assertTrue(fList.contains(fl));
				fList.removeValue(fl);
			}
			Assert.assertTrue(fList.size() == 0);
		}
	}


	@Test
	public void primitiveBagTest() {
		{
			CharBag cBag = new CharBag();
			char[] chs = new char[] { 0, 5, 22, 105 };
			char[] chs2 = new char[] { 1, 5, 3, 2 };
			cBag.addAll(chs);
			for(char ch : chs) {
				Assert.assertTrue(cBag.contains(ch));
				cBag.removeValue(ch);
			}
			Assert.assertTrue(cBag.size() == 0);
			cBag.add(0, chs2[0]);
			cBag.add(1, chs2[1]);
			cBag.add(2, chs2[2]);
			cBag.set(1, chs2[3]);
			Assert.assertTrue(cBag.indexOf(chs2[2]) == 2);
			Assert.assertTrue(cBag.get(1) == chs2[3]);
		}

		{
			IntBag iBag = new IntBag();
			int[] ins = new int[] { 0, 5, 22, 105 };
			int[] ins2 = new int[] { 1, 5, 3, 2 };
			iBag.addAll(ins);
			for(int in : ins) {
				Assert.assertTrue(iBag.contains(in));
				iBag.removeValue(in);
			}
			Assert.assertTrue(iBag.size() == 0);
			iBag.add(0, ins2[0]);
			iBag.add(1, ins2[1]);
			iBag.add(2, ins2[2]);
			iBag.set(1, ins2[3]);
			Assert.assertTrue(iBag.indexOf(ins2[2]) == 2);
			Assert.assertTrue(iBag.get(1) == ins2[3]);
		}

		{
			FloatBag fBag = new FloatBag();
			float[] fls = new float[] { 0, 5, 22, 105 };
			float[] fls2 = new float[] { 1, 5, 3, 2 };
			fBag.addAll(fls);
			for(float fl : fls) {
				Assert.assertTrue(fBag.contains(fl));
				fBag.removeValue(fl);
			}
			Assert.assertTrue(fBag.size() == 0);
			fBag.add(0, fls2[0]);
			fBag.add(1, fls2[1]);
			fBag.add(2, fls2[2]);
			fBag.set(1, fls2[3]);
			Assert.assertTrue(fBag.indexOf(fls2[2]) == 2);
			Assert.assertTrue(fBag.get(1) == fls2[3]);
		}
	}


	@Test
	public void sortedMapTest() {
		{
			CharMapSorted<String> cMap = new CharMapSorted<String>();

			cMap.put((char)5, "5");

			char[] chars = { 0, 5, 22, 105 };
			String[] strs = { "zero", "twenty two", "five", "one hundred and five" };
			Assert.assertTrue("array lengths", chars.length == strs.length);
			for(int i = 0, size = chars.length; i < size; i++) {
				cMap.put(chars[i], strs[i]);
			}
			Assert.assertTrue(cMap.size() == strs.length);

			for(int i = 0; i < strs.length; i++) {
				Assert.assertTrue("contains key: " + chars[i], cMap.contains(chars[i]));
				Assert.assertTrue("contains value: " + strs[i], cMap.containsValue(strs[i]));
				cMap.removeValue(strs[i]);
			}
			Assert.assertTrue(cMap.size() == 0);
		}

		{
			IntMapSorted<String> iMap = new IntMapSorted<String>();

			iMap.put(5, "5");

			int[] ints = { 0, 5, 22, 105 };
			String[] strs = { "zero", "twenty two", "five", "one hundred and five" };
			Assert.assertTrue("array lengths", ints.length == strs.length);
			for(int i = 0, size = ints.length; i < size; i++) {
				iMap.put(ints[i], strs[i]);
			}
			Assert.assertTrue(iMap.size() == strs.length);

			for(int i = 0; i < strs.length; i++) {
				Assert.assertTrue("contains key: " + ints[i], iMap.contains(ints[i]));
				Assert.assertTrue("contains value: " + strs[i], iMap.containsValue(strs[i]));
				iMap.removeValue(strs[i]);
			}
			Assert.assertTrue(iMap.size() == 0);
		}

		{
			FloatMapSorted<String> fMap = new FloatMapSorted<String>();

			fMap.put(5f, "5");

			float[] floats = { 0.0f, 5f, 22.22f, 105.2f };
			String[] strs = { "zero", "twenty two", "five", "one hundred and five" };
			Assert.assertTrue("array lengths", floats.length == strs.length);
			for(int i = 0, size = floats.length; i < size; i++) {
				fMap.put(floats[i], strs[i]);
			}
			Assert.assertTrue(fMap.size() == strs.length);

			for(int i = 0; i < strs.length; i++) {
				Assert.assertTrue("contains key: " + floats[i], fMap.contains(floats[i]));
				Assert.assertTrue("contains value: " + strs[i], fMap.containsValue(strs[i]));
				fMap.removeValue(strs[i]);
			}
			Assert.assertTrue(fMap.size() == 0);
		}
	}


	@Test
	public void addAll() {
		IntArrayList aryList1 = IntArrayList.of(3, 4, 5);
		IntArrayList aryList2 = IntArrayList.of(1, 2);
		IntListSorted sortedList1 = IntListSorted.of(8, 9);
		IntListSorted sortedList2 = IntListSorted.of(10);
		IntList tmp = null;

		tmp = aryList1.copy();
		tmp.addAll(sortedList2);
		assertArrayLooseEqual(tmp.toArray(), concatCopy(aryList1, sortedList2));

		tmp = aryList1.copy();
		tmp.addAll(aryList2);
		assertArrayLooseEqual(tmp.toArray(), concatCopy(aryList1, aryList2));

		tmp = sortedList2.copy();
		tmp.addAll(sortedList1);
		assertArrayLooseEqual(tmp.toArray(), concatCopy(sortedList2, sortedList1));

		tmp = sortedList1.copy();
		tmp.addAll(aryList2);
		assertArrayLooseEqual(tmp.toArray(), concatCopy(sortedList1, aryList2));
	}


	@Test
	public void addValues() {
		addValuesTest(IntArrayList.of(3, 4, 5));
		addValuesTest(IntListSorted.of(3, 4, 5));
	}


	public void addValuesTest(IntList list1) {
		IntArrayList aryList2 = IntArrayList.of(1, 2);
		IntListSorted sortedList1 = IntListSorted.of(8, 9);
		IntListSorted sortedList2 = IntListSorted.of(10);
		Collection<Integer> col1 = new LinkedList<Integer>();
		col1.add(8);
		col1.add(9);
		IntList tmp = null;

		tmp = list1.copy();
		tmp.addValues(sortedList1);
		assertArrayLooseEqual(tmp.toArray(), concatCopy(list1, sortedList1));

		tmp = list1.copy();
		tmp.addValues(aryList2);
		assertArrayLooseEqual(tmp.toArray(), concatCopy(list1, aryList2));

		tmp = list1.copy();
		tmp.addValues(sortedList2.toList());
		assertArrayLooseEqual(tmp.toArray(), concatCopy(list1, sortedList2));

		tmp = list1.copy();
		tmp.addValues(new Iterable<Integer>() {
			@Override public Iterator<Integer> iterator() {
				return aryList2.iterator();
			}
		});
		assertArrayLooseEqual(tmp.toArray(), concatCopy(list1, aryList2));

		tmp = list1.copy();
		tmp.addValues(col1);
		assertArrayLooseEqual(tmp.toArray(), concatCopy(list1, IntArrayList.of(col1)));
	}


	@Test
	public void pop() {
		popTest(IntArrayList.of(40, 41, 42));
		popTest(IntBag.of(40, 41, 42));
	}


	public void popTest(IntArrayList list) {
		Assert.assertEquals(42, list.pop());
		Assert.assertEquals(2, list.size());

		list.set(1, 99);
		Assert.assertEquals(99, list.pop());
		Assert.assertEquals(40, list.pop());
		Assert.assertEquals(0, list.size());
		assertException(() -> list.pop());
		assertException(() -> list.getLast());
		assertException(() -> list.set(0, 99));
	}


	@Test
	public void intArrayListTest() {
		IntArrayList list = new IntArrayList(8);
		int[] items = new int[] {5, 9, 12, 3, 4, 3, 2, 1, 6};

		list.add(items[0]);
		list.add(items[1]);
		list.add(items[2]);
		Assert.assertTrue(list.remove(1) == 9);
		Assert.assertTrue(list.remove(1) == 12);
		Assert.assertTrue(list.removeValue(5));
		Assert.assertTrue(list.size() == 0);

		list.add(items[3]);
		list.add(items[4]);
		list.add(items[5]);
		Assert.assertEquals(0, list.indexOf(3));
		Assert.assertEquals(2, list.indexOf(3, 2));
		Assert.assertEquals(2, list.lastIndexOf(3));
		int temp = list.get(list.size() - 1);
		list.add(0, 567);
		list.add(0, 765);
		Assert.assertTrue(list.get(0) == 765 && list.get(1) == 567 && temp == list.get(list.size() - 1));

		list.remove(1);
		list.remove(0);
		Assert.assertTrue(list.removeValue(items[3]));
		Assert.assertTrue(list.removeValue(items[4]));
		Assert.assertTrue(list.size() == 1);
		Assert.assertTrue(list.get(0) == 3);

		list.add(items[6]);
		list.add(items[7]);
		list.add(items[8]);
		Assert.assertTrue(list.removeValue(1));

		list.clear();
		list.addAll(items, 0, items.length);
		Assert.assertEquals(items.length, list.size());
	}


	@Test
	public void intListSortedTest() {
		IntListSorted list = new IntListSorted(8);
		int[] items = new int[] {5, 9, 12, 3, 4, 3, 2, 1, 6};

		list.add(items[0]);
		list.add(items[1]);
		list.add(items[2]);
		Assert.assertTrue(list.remove(1) == 9);
		Assert.assertTrue(list.remove(1) == 12);
		Assert.assertTrue(list.removeValue(5));
		Assert.assertTrue(list.size() == 0);

		list.add(items[3]);
		list.add(items[4]);
		list.add(items[5]);
		Assert.assertEquals(0, list.indexOf(3));
		Assert.assertEquals(1, list.lastIndexOf(3));
		Assert.assertTrue(list.removeValue(items[3]));
		Assert.assertTrue(list.removeValue(items[4]));
		Assert.assertTrue(list.size() == 1);
		Assert.assertTrue(list.get(0) == 3);

		list.add(items[6]);
		list.add(items[7]);
		list.add(items[8]);
		Assert.assertTrue(list.removeValue(1));
	}


	private void assertArrayLooseEqual(int[] ary1, int[] ary2) {
		Arrays.sort(ary1);
		Arrays.sort(ary2);
		Assert.assertArrayEquals(ary1, ary2);
	}


	private static int[] concatCopy(IntList a, IntList b) {
		int[] r = Arrays.copyOf(a.toArray(), a.size() + b.size());
		System.arraycopy(b.toArray(), 0, r, a.size(), b.size());
		return r;
	}


	/** Assert that the task throws an {@link Exception}
	 * @param task the action to perform
	 */
	public static void assertException(Runnable task) {
		assertException(null, task);
	}


	/** Assert that the task throws an {@link Exception}
	 * @param errMsg a message to include with the assertion
	 * @param task the action to perform
	 */
	public static void assertException(String errMsg, Runnable task) {
		Throwable error = null;
		try {
			task.run();
		} catch(Exception e) {
			error = e;
		} finally {
			Assert.assertTrue("task " + (errMsg != null ? "'" + errMsg + "' " : "") + "was expected to throw exception but did not", error != null);
		}
	}

}
