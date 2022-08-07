package com.lihd.part07;

import java.util.*;

/**
 * Rectangle
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/6/1 20:10
 */
public class Code03CoverMax {

    public static class Rectangle{
        int up;
        int down;
        int left;
        int right;

        public Rectangle(int up, int down, int left, int right) {
            this.up = up;
            this.down = down;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "Rectangle{" +
                    "up=" + up +
                    ", down=" + down +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    public static int maxCover(Rectangle[] rectangles) {
        //先根据底边排序 再根据左边界排序
        Arrays.sort(rectangles, (a, b) -> a.down - b.down == 0 ? a.left - b.left : a.down - b.down);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < rectangles.length;) {
            List<Rectangle> list = new ArrayList<>();
            Rectangle temp = rectangles[i];
            //把之前的都放进来 且要满足 之前的顶的高度 > 目前底的高度
            for (int j = 0; j < i; j++) {
                if (rectangles[j].up > temp.down) {
                    list.add(rectangles[j]);
                }
            }
            //必定会进去一次
            while (i < rectangles.length && rectangles[i].down == temp.down) {
                list.add(rectangles[i]);
                i ++;
            }
            // list 放着所有合理的位置，不一定能保持按从左到右排序
            list.sort((a, b) -> a.left - b.left);
            // 现在已经有序 并且合理
            PriorityQueue<Rectangle> heap = new PriorityQueue<>((a, b) -> a.right - b.right);
            for (Rectangle rectangle : list) {
                while (!heap.isEmpty() && rectangle.left >= heap.peek().right) {
                    heap.poll();
                }
                heap.add(rectangle);
                max = Math.max(max, heap.size());
            }
        }
        return max;
    }

    public static Rectangle createRectangle() {
        int left = (int) (Math.random() * 10 - Math.random() * 10);
        int down = (int) (Math.random() * 10 - Math.random() * 10);
        int up = (int) (down + 1 + Math.random() * 5);
        int right = (int) (left + 1 + Math.random() * 5);
        return new Rectangle(up, down, left, right);
    }

    public static Rectangle[] createRectangleArr(int maxLen) {
        int len = (int) (1 + Math.random() * maxLen);
        Rectangle[] ans = new Rectangle[len];
        for (int i = 0; i < len; i++) {
            ans[i] = createRectangle();
        }
        return ans;
    }
    //10万次测试 -> 100万次测试
    //我的 ：878 -> 6588
    //实例 ：2127 -> 20375
    public static void main(String[] args) {
        int testTimes = 1_0_0000;
        int maxLen = 5;
        long start = System.currentTimeMillis();
        for (int i = 0; i < testTimes; i++) {
            Rectangle[] rectangleArr = createRectangleArr(maxLen);
            int maxCover = maxCover(rectangleArr);
            int maxCover2 = maxCover2(rectangleArr);
            if (maxCover != maxCover2) {
                System.out.println(Arrays.toString(rectangleArr));
                System.out.println("maxCover = " + maxCover);
                System.out.println("maxCover2 = " + maxCover2);

            }
        }
        long end = System.currentTimeMillis();
        System.out.println("cost : " + (end - start));
    }

    //老师写的 在下面 我感觉是错的 用因为set会自动去重， 我感觉是把满足一定条件的左边相同的给去重了

    public static class DownComparator implements Comparator<Rectangle> {
		@Override
		public int compare(Rectangle o1, Rectangle o2) {
			return o1.down != o2.down ? (o1.down - o2.down) : o1.toString().compareTo(o2.toString());
		}
	}

	public static class LeftComparator implements Comparator<Rectangle> {
		@Override
		public int compare(Rectangle o1, Rectangle o2) {
			return o1.left != o2.left ? (o1.left - o2.left) : o1.toString().compareTo(o2.toString());
		}
	}

	public static class RightComparator implements Comparator<Rectangle> {
		@Override
		public int compare(Rectangle o1, Rectangle o2) {
			return o1.right != o2.right ? (o1.right - o2.right) : o1.toString().compareTo(o2.toString());
		}
	}

	// 矩形数量是N
	// O(N*LogN)
	// +
	// O(N) * [ O(N) + O(N *LogN) ]
	public static int maxCover2(Rectangle[] recs) {
		if (recs == null || recs.length == 0) {
			return 0;
		}
		// 根据down（底）排序
		Arrays.sort(recs, new DownComparator());
		// 可能会对当前底边的公共局域，产生影响的矩形
		// list -> treeSet(有序表表达)
		TreeSet<Rectangle> leftOrdered = new TreeSet<>(new LeftComparator());
		int ans = 0;
		// O(N)
		for (int i = 0; i < recs.length;) { // 依次考察每一个矩形的底边
			// 同样底边的矩形一批处理
			do {
				leftOrdered.add(recs[i++]);
			} while (i < recs.length && recs[i].down == recs[i - 1].down);
			// 清除顶<=当前底的矩形
			removeLowerOnCurDown(leftOrdered, recs[i - 1].down);
			// 维持了右边界排序的容器
			TreeSet<Rectangle> rightOrdered = new TreeSet<>(new RightComparator());
			for (Rectangle rec : leftOrdered) { // O(N)
				removeLeftOnCurLeft(rightOrdered, rec.left);
				rightOrdered.add(rec);// O(logN)
				ans = Math.max(ans, rightOrdered.size());
			}
		}
		return ans;
	}

	public static void removeLowerOnCurDown(TreeSet<Rectangle> set, int curDown) {
		List<Rectangle> removes = new ArrayList<>();
		for (Rectangle rec : set) {
			if (rec.up <= curDown) {
				removes.add(rec);
			}
		}
		for (Rectangle rec : removes) {
			set.remove(rec);
		}
	}

	public static void removeLeftOnCurLeft(TreeSet<Rectangle> rightOrdered, int curLeft) {
		List<Rectangle> removes = new ArrayList<>();
		for (Rectangle rec : rightOrdered) {
			if (rec.right > curLeft) {
				break;
			}
			removes.add(rec);
		}
		for (Rectangle rec : removes) {
			rightOrdered.remove(rec);
		}
	}

}
