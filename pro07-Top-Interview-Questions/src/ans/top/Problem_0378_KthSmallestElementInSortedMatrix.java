package ans.top;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Problem_0378_KthSmallestElementInSortedMatrix {

	public static class Node {
		public int value;
		public int row;
		public int col;

		public Node(int v, int r, int c) {
			value = v;
			row = r;
			col = c;
		}

	}

	public static class NodeComparator implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {
			return o1.value - o2.value;
		}

	}

	public static int kthSmallest1(int[][] matrix, int k) {
		int N = matrix.length;
		int M = matrix[0].length;
		PriorityQueue<Node> heap = new PriorityQueue<>(new NodeComparator());
		boolean[][] set = new boolean[N][M];
		heap.add(new Node(matrix[0][0], 0, 0));
		set[0][0] = true;
		int count = 0;
		Node ans = null;
		while (!heap.isEmpty()) {
			ans = heap.poll();
			if (++count == k) {
				break;
			}
			int row = ans.row;
			int col = ans.col;
			if (row + 1 < N && !set[row + 1][col]) {
				heap.add(new Node(matrix[row + 1][col], row + 1, col));
				set[row + 1][col] = true;
			}
			if (col + 1 < M && !set[row][col + 1]) {
				heap.add(new Node(matrix[row][col + 1], row, col + 1));
				set[row][col + 1] = true;
			}
		}
		return ans.value;
	}

	public static int kthSmallest2(int[][] matrix, int k) {
		int N = matrix.length;
		int M = matrix[0].length;
		int left = matrix[0][0];
		int right = matrix[N - 1][M - 1];
		int ans = 0;
		while (left <= right) {
			int mid = left + ((right - left) >> 1);
			Info info = noMoreNum(matrix, mid);
			if (info.num < k) {
				left = mid + 1;
			} else {
				ans = info.near;
				right = mid - 1;
			}
		}
		return ans;
	}

	public static class Info {
		public int near;
		public int num;

		public Info(int n1, int n2) {
			near = n1;
			num = n2;
		}
	}

	public static Info noMoreNum(int[][] matrix, int value) {
		int near = Integer.MIN_VALUE;
		int num = 0;
		int N = matrix.length;
		int M = matrix[0].length;
		int row = 0;
		int col = M - 1;
		while (row < N && col >= 0) {
			if (matrix[row][col] <= value) {
				near = Math.max(near, matrix[row][col]);
				num += col + 1;
				row++;
			} else {
				col--;
			}
		}
		return new Info(near, num);
	}

}
