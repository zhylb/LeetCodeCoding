package com.lihd.part03;



import java.util.ArrayList;
import java.util.Arrays;

/**结构
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/27 16:55
 */
public class Code03SameStructTree {

    public static boolean isSameStructTree(Node large, Node small) {

//        if (large == null && small == null) {
//            return true;
//        }
//        if (large == null ^ small == null) {
//            return false;
//        }
        if (small == null) {
            return true;
        }
        if (large == null) {
            return false;
        }
        //两个都不是null
        if (isSameTree(large, small)) {
            return true;
        }
        return isSameStructTree(large.left, small) || isSameStructTree(large.right, small);
    }


    public static boolean isSameTree(Node a, Node b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null ^ b == null) {
            return false;
        }
        //两个都不是null
        if (a.val != b.val) {
            return false;
        }
        return isSameTree(a.left, b.left) && isSameTree(a.right, b.right);
    }

    public static boolean containsTree(Node large, Node small) {
        String[] largeArr = preSerial(large);
        String[] smallArr = preSerial(small);
        return KMP.indexOf(largeArr, smallArr) != -1;
    }

    public static String[] preSerial(Node head) {
        ArrayList<String> list = new ArrayList<>();
        preSerial(head, list);
        String[] ans = new String[list.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }


    //序列化
    public static void preSerial(Node head, ArrayList<String > ans) {
        if (head == null) {
            ans.add(null);
        } else {
            ans.add("" + head.val);
            preSerial(head.left, ans);
            preSerial(head.right, ans);
        }
    }

    // for test
	public static Node generateRandomBST(int maxLevel, int maxValue) {
		return generate(1, maxLevel, maxValue);
	}

	// for test
	public static Node generate(int level, int maxLevel, int maxValue) {
		if (level > maxLevel || Math.random() < 0.5) {
			return null;
		}
		Node head = new Node((int) (Math.random() * maxValue));
		head.left = generate(level + 1, maxLevel, maxValue);
		head.right = generate(level + 1, maxLevel, maxValue);
		return head;
	}

	public static void main(String[] args) {
		int bigTreeLevel = 7;
		int smallTreeLevel = 4;
		int nodeMaxValue = 5;
		int testTimes = 100000;
		System.out.println("test begin");
		for (int i = 0; i < testTimes; i++) {
			Node big = generateRandomBST(bigTreeLevel, nodeMaxValue);
			Node small = generateRandomBST(smallTreeLevel, nodeMaxValue);
			boolean ans1 = isSameStructTree(big, small);
			boolean ans2 = containsTree(big, small);
			if (ans1 != ans2) {

                System.out.println("ans1 = " + ans1);
                System.out.println("ans2 = " + ans2);
				System.out.println("Oops!");
                break;
			}
		}
		System.out.println("test finish!");

	}

}
