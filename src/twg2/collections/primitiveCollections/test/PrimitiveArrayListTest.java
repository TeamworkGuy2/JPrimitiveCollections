package twg2.collections.primitiveCollections.test;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import twg2.collections.primitiveCollections.IntArrayList;

/**
 * @author TeamworkGuy2
 * @since 2015-7-3
 */
public class PrimitiveArrayListTest {

	class ArrayListRemoveData {
		int[] originalData;
		int removeOff;
		int removeLen;
		int[] removedData;
		int[] resultData;

		public ArrayListRemoveData(int[] originalData, int removeOff, int removeLen, int[] removedData, int[] resultData) {
			this.originalData = originalData;
			this.removeOff = removeOff;
			this.removeLen = removeLen;
			this.removedData = removedData;
			this.resultData = resultData;
		}

	}




	@Test
	public void testPrimitiveArrayListRemoveRangeTest() {
		List<List<Integer>> inputs = Arrays.asList(
				Arrays.asList(1, 2, 3, 4, 5),
				Arrays.asList(0, 5, 10, 15, 20),
				Arrays.asList(3)
		);

		List<ArrayListRemoveData> testData = Arrays.asList(
				new ArrayListRemoveData(IntArrayList.toArray(inputs.get(0)), 2, 2, new int[] { 3, 4 }, new int[] { 1, 2, 5 }),
				new ArrayListRemoveData(IntArrayList.toArray(inputs.get(1)), 4, 1, new int[] { 20 }, new int[] { 0, 5, 10, 15 }),
				new ArrayListRemoveData(IntArrayList.toArray(inputs.get(2)), 0, 1, new int[] { 3 }, new int[] { })
		);


		int i = 0;
		for(ArrayListRemoveData entry : testData) {
			int len = entry.removeLen;
			int[] dstAry = new int[len];
			IntArrayList dataList = IntArrayList.of(inputs.get(i));
			dataList.getSubArray(entry.removeOff, dstAry, 0, len);
			dataList.removeRange(entry.removeOff, len);

			Assert.assertEquals(IntArrayList.toList(entry.removedData), IntArrayList.toList(dstAry));
			Assert.assertEquals(IntArrayList.toList(entry.resultData), dataList.toList());
			i++;
		}
	}

}
