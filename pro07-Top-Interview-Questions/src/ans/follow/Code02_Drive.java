package ans.follow;

public class Code02_Drive {

	/*
	 * 司机调度 时间限制： 3000MS 内存限制： 589824KB 题目描述：
	 * 正值下班高峰时期，现有可载客司机数2N人，调度中心将调度相关司机服务A、B两个出行高峰区域。 第 i 个司机前往服务A区域可得收入为
	 * income[i][0]，前往服务B区域可得收入为 income[i][1]。
	 * 返回将每位司机调度完成服务后，所有司机总可得的最高收入金额，要求每个区域都有 N 位司机服务。 输入描述 10 20 20 40 # 如上：
	 * 第一个司机服务 A 区域，收入为 10元 第一个司机服务 B 区域，收入为 20元 第二个司机服务 A 区域，收入为 20元 第二个司机服务 B
	 * 区域，收入为 40元 输入参数以 '#' 结束输入 输出描述 最高总收入为 10 + 40= 50，每个区域都有一半司机服务
	 * 参数及相关数据异常请输出：error 样例输入 : 10 30 100 200 150 50 60 20 # 样例输出 440
	 */

	// 给定一个N*2的正数矩阵matix，N一定是偶数，可以保证。
	// 一定要让A区域分到N/2个司机，让B区域也分到N/2个司机
	// 返回最大的总收益
	public static int maxMoney(int[][] matrix) {
		// 0.....
		// N   A  N/2   B  N/2
		return process2(matrix, 0, matrix.length / 2);
	}

	// int[][] matrix  N * 2 大小的
	// i   A : matrix[i][0]  B : matrix[i][1]
	// 0..i-1司机，已经做完选择了，不用再操心了，
	// 从i开始到最后所有的司机，在A区域还有aRest个名额的情况下，返回最优分配的收益
	public static int process(int[][] matrix, int i, int aRest) {
		if (aRest < 0) {
			return -1;
		}
		// aRest >= 0  N  A 耗尽    A  B
		if (i == matrix.length) {
			return aRest == 0 ? 0 : -1;
		}
		// aRest >= 0 && 还有司机选
		int goToA = -1;
		// 当前司机  i   决定去A区域之后，后续过程最好的收益，nextA
		int nextA = process(matrix, i + 1, aRest - 1);
		if (nextA != -1) {
			goToA = matrix[i][0] + nextA;
		}
		int goToB = -1;
		int nextB = process(matrix, i + 1, aRest);
		if (nextB != -1) {
			goToB = matrix[i][1] + nextB;
		}
		return Math.max(goToA, goToB);
	}
	
	
	
	public static int process2(int[][] matrix, int i, int aRest) {
		if (i == matrix.length) {
			return aRest == 0 ? 0 : -1;
		}
		int goToA = -1;
		if(aRest > 0) {
			goToA = matrix[i][0] + process2(matrix, i+1, aRest - 1);
		}
		int goToB = -1;
		int goAs = (matrix.length / 2) - aRest;
		int goBs = i - goAs;
		if(goBs < (matrix.length / 2)) {
			goToB = matrix[i][1] + process2(matrix, i+1, aRest);
		}
		return Math.max(goToA, goToB);
	}

	public static void main(String[] args) {
		int[][] matrix = { { 10, 20 }, { 20, 40 } };

		System.out.println(maxMoney(matrix));
	}

}
