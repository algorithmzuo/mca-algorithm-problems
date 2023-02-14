package class01;

public class Code03_BinarySearch {

	public static boolean exist(int[] sortedArr, int num) {
		if (sortedArr == null || sortedArr.length == 0) {
			return false;
		}
		int L = 0;
		int R = sortedArr.length - 1;
		int mid = 0;
		// L..R
		while (L < R) { // L..R 至少两个数的时候
			mid = L + ((R - L) >> 1);
			if (sortedArr[mid] == num) {
				return true;
			} else if (sortedArr[mid] > num) {
				R = mid - 1;
			} else {
				L = mid + 1;
			}
		}
		return sortedArr[L] == num;
	}

	// 在arr上，找满足>=value的最左位置
	public static int nearest1(int[] sortedArr, int value) {
		int L = 0;
		int R = sortedArr.length - 1;
		// 0....N-1
		int ans = -1;
		while (L <= R) { // L...R L < R
			int mid = (L + R) / 2;
			if (sortedArr[mid] >= value) {
				ans = mid;
				R = mid - 1;
			} else {
				L = mid + 1;
			}
		}
		return ans;
	}

	// 在arr上，找满足<=value的最右位置
	public static int nearest2(int[] sortedArr, int value) {
		int L = 0;
		int R = sortedArr.length - 1;
		int index = -1; // 记录最右的对号
		while (L <= R) {
			int mid = L + ((R - L) >> 1);
			if (sortedArr[mid] <= value) {
				index = mid;
				L = mid + 1;
			} else {
				R = mid - 1;
			}
		}
		return index;
	}

}
