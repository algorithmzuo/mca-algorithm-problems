package class01;

// 找到数组中的水王数
// 本题来自，大厂刷题班，23节
public class Problem01_WaterKing {

	public static int waterKing(int[] arr) {
		int cand = 0;
		int hp = 0;
		for (int i = 0; i < arr.length; i++) {
			if (hp == 0) {
				cand = arr[i];
				hp = 1;
			} else if (arr[i] == cand) {
				hp++;
			} else {
				hp--;
			}
		}
		if (hp == 0) {
			return -1;
		}
		hp = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == cand) {
				hp++;
			}
		}
		return hp > arr.length / 2 ? cand : -1;
	}

}
