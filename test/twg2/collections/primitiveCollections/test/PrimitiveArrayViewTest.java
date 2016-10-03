package twg2.collections.primitiveCollections.test;

import java.util.ListIterator;

import org.junit.Assert;
import org.junit.Test;

import twg2.collections.primitiveCollections.DoubleArrayView;
import twg2.collections.primitiveCollections.DoubleArrayViewHandle;

/**
 * @author TeamworkGuy2
 * @since 2015-0-0
 */
public class PrimitiveArrayViewTest {

	@Test
	public void arrayDoubleViewTest() {
		double[] objs = new double[] { 10, 20, 30, 40, 50, 60 };
		double[] viewAry = new double[] { 30, 40, 50 };
		int off = 2;
		int len = 3;
		DoubleArrayViewHandle viewHdl = new DoubleArrayViewHandle(objs, off, len);
		DoubleArrayView view = viewHdl.getDoubleArrayView();

		// test array view length
		Assert.assertEquals(len, view.size());

		// test of array view values
		for(int loopI = 0; loopI < len; loopI++) {
			int i = 0;
			for(double val : view) {
				Assert.assertTrue("found '" + val + "' expected '" + objs[off + i] + "'", val == objs[off + i]);
				i++;
			}
			Assert.assertArrayEquals(viewAry, view.toArray(new double[len], 0), 0);
		}

		// test of setArrayView()
		objs = new double[] { 1.1, 2.2, 3.3, 4.4 };
		viewAry = new double[] { 2.2, 3.3, 4.4 };
		off = 1;
		len = 3;
		viewHdl.setDoubleArrayView(objs, off, len);

		for(int loopI = 0; loopI < len; loopI++) {
			int i = 0;
			for(double val : view) {
				Assert.assertTrue("found '" + val + "' expected '" + objs[off + i] + "'", val == objs[off + i]);
				i++;
			}
			Assert.assertArrayEquals(viewAry, view.toArray(new double[len], 0), 0);
		}

		// test the array view list iterator (and by inheritance, iterator)
		ListIterator<Double> itr = view.listIterator(0);
		for(int i = 0; itr.hasNext(); i++) {
			double str = itr.next();
			Assert.assertTrue("index unexpected: " + itr.nextIndex() + ", expected: " + i, i == itr.nextIndex() - 1);
			Assert.assertTrue(i + ": " + str + " != " + viewAry[i], str == viewAry[i]);
			Assert.assertTrue("invalid last index " + i + ", expected " + (viewAry.length - 1),
					(!itr.hasNext() && i == viewAry.length - 1) || itr.hasNext());
		}
	}


	@Test
	public void arrayDoubleViewModifyTest() {
		double[] vals = new double[] { Math.E, 234.23, 845743 };
		// TODO test set() on ArrayView with allowSet = true
		DoubleArrayView view = new DoubleArrayViewHandle(vals, 0, vals.length, true).getDoubleArrayView();
		double insertVal = Math.PI;
		view.set(2, insertVal);
		vals[2] = insertVal;
		Assert.assertTrue(view.get(2) == insertVal);
	}


	@Test
	public void sumTest() {
		Assert.assertEquals(100, doubleView(new double[] { 5, 10, 20, 30, 25, 15 }, 1, 5).sum(), 0.0);
		Assert.assertEquals(10, doubleView(new double[] { 3, 5, 2 }).sum(), 0.0);
		Assert.assertEquals(0, doubleView(new double[] { }).sum(), 0.0);
	}


	@Test
	public void avgTest() {
		Assert.assertEquals(20, doubleView(new double[] { 5, 10, 20, 30, 25, 15 }, 1, 5).average(), 0.0);
		Assert.assertEquals(0, doubleView(new double[] { }).average(), 0.0);
		Assert.assertEquals(10 / 3.0, doubleView(new double[] { 1, 3, 5, 2, 1 }, 1, 3).average(), 0.0);
		Assert.assertEquals(10 / 3.0, doubleView(new double[] { 6, 3, 5, 2, 1 }, 1, 3).average(), 0.0);
		Assert.assertEquals(10 / 3.0, doubleView(new double[] { 1, 3, 5, 2, 6 }, 1, 3).average(), 0.0);
		Assert.assertEquals(10 / 3.0, doubleView(new double[] { 6, 3, 5, 2, 6 }, 1, 3).average(), 0.0);
	}


	@Test
	public void maxTest() {
		Assert.assertEquals(30, doubleView(new double[] { 5, 10, 20, 30, 25, 15 }, 1, 5).max(), 0.0);
		Assert.assertEquals(0, doubleView(new double[] { }).max(), 0.0);
		Assert.assertEquals(5, doubleView(new double[] { 1, 3, 5, 2, 1 }, 1, 3).max(), 0.0);
		Assert.assertEquals(5, doubleView(new double[] { 6, 3, 5, 2, 1 }, 1, 3).max(), 0.0);
		Assert.assertEquals(5, doubleView(new double[] { 1, 3, 5, 2, 6 }, 1, 3).max(), 0.0);
		Assert.assertEquals(5, doubleView(new double[] { 6, 3, 5, 2, 6 }, 1, 3).max(), 0.0);
	}


	@Test
	public void minTest() {
		Assert.assertEquals(10, doubleView(new double[] { 5, 10, 20, 30, 25, 15 }, 1, 5).min(), 0.0);
		Assert.assertEquals(0, doubleView(new double[] { }).min(), 0.0);
		Assert.assertEquals(2, doubleView(new double[] { 1, 3, 5, 2, 1 }, 1, 3).min(), 0.0);
		Assert.assertEquals(2, doubleView(new double[] { 6, 3, 5, 2, 1 }, 1, 3).min(), 0.0);
		Assert.assertEquals(2, doubleView(new double[] { 1, 3, 5, 2, 6 }, 1, 3).min(), 0.0);
		Assert.assertEquals(2, doubleView(new double[] { 6, 3, 5, 2, 6 }, 1, 3).min(), 0.0);
	}


	private static DoubleArrayView doubleView(double[] vals) {
		return doubleView(vals, 0, vals.length);
	}


	private static DoubleArrayView doubleView(double[] vals, int off, int len) {
		return new DoubleArrayViewHandle(vals, off, len).getDoubleArrayView();
	}

}
