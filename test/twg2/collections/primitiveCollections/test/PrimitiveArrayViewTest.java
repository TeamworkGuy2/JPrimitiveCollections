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
		Assert.assertTrue("invalid length " + view.size() + " expected " + len, view.size() == len);

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
		DoubleArrayViewHandle viewHdl = new DoubleArrayViewHandle(vals, 0, vals.length, true);
		DoubleArrayView view = viewHdl.getDoubleArrayView();
		double insertVal = Math.PI;
		view.set(2, insertVal);
		vals[2] = insertVal;
		Assert.assertTrue(view.get(2) == insertVal);
	}

}
