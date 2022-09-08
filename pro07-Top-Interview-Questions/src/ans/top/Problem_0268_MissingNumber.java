package ans.top;

public class Problem_0268_MissingNumber {

	public static int missingNumber(int[] arr) {
		int l = 0;
		int r = arr.length;
		while (l < r) {
			if (arr[l] == l) {
				l++;
			} else if (arr[l] < l || arr[l] >= r || arr[arr[l]] == arr[l]) {
				swap(arr, l, --r);
			} else {
				swap(arr, l, arr[l]);
			}
		}
		return l;
	}

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

}
