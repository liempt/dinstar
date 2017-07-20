package sn.mas.http;

public class ArrayUtil {
	
	/**
	 * Array translate to string
	 * 
	 * @param array
	 * @param join
	 * @return
	 */
	public static String arrayJoin(Object[] array, String join) {
		String result = null;
		for (int i = 0; i < array.length; i++) {
			if (result == null) {
				result = "" + array[i].toString();
			} else {
				result = result + join + array[i].toString();
			}
		}
		return result;
	}
	
	/**
	 * Integer Array translate to String
	 * 
	 * @param array
	 * @param join
	 * @return
	 */
	public static String intArrayJoin(int[] array, String join) {
		String result = null;
		for (int i = 0; i < array.length; i++) {
			if (result == null) {
				result = "" + array[i];
			} else {
				result = result + join + array[i];
			}
		}
		return result;
	}
}
