package ans.class06;

public class Code03_JumpGame {







	public static int jump(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int step = 0; // 跳了多少步
		int cur = 0; // step步内，右边界
		int next = 0;// step+1步内，右边界
		for (int i = 0; i < arr.length; i++) {
			if (cur < i) {
				step++;
				cur = next;
			}
			next = Math.max(next, i + arr[i]);
		}
		return step;
	}

	public static void main(String[] args) {
		int[] arr = {3, 4, 2, 1, 2, 5, 8, 1};
		System.out.println(jump(arr));
	}

}
