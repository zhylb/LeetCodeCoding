package ans.class04;

public class Code05_FactorialProblem {

	public static int zeroNum1(int num) {
		if (num < 0) {
			return 0;
		}
		int res = 0;
		int cur = 0;
		for (int i = 5; i < num + 1; i = i + 5) {
			cur = i;
			while (cur % 5 == 0) {
				res++;
				cur /= 5;
			}
		}
		return res;
	}

	public static int zeroNum2(int num) {
		if (num < 0) {
			return 0;
		}
		int res = 0;
		while (num != 0) {
			res += num / 5;
			num /= 5;
		}
		return res;
	}

	public static int rightOne1(int num) {
		if (num < 1) {
			return -1;
		}
		int res = 0;
		while (num != 0) {
			num >>>= 1;
			res += num;
		}
		return res;
	}

	public static int rightOne2(int num) {
		if (num < 1) {
			return -1;
		}
		int ones = 0;
		int rightOne = 0;
		while (num != 0) {
			rightOne = (num & (~num + 1));
			num -= rightOne;
			ones++;
		}
		return num - ones;
	}

	public static void main(String[] args) {
		int num = 1000000000;

		System.out.println(zeroNum2(num));
		System.out.println(zeroNum1(num));

		System.out.println(rightOne2(num));
		System.out.println(rightOne1(num));

	}

}
