package com.lihd.part02;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/24 16:01
 */
public class Code02Candy {

    /**
     * 5 ms, faster than 51.78%
     * 51.8 MB, less than 51.02%
     * 怎么使用有限空间做到 ?
     * @param ratings 小孩数组
     * @return int
     * @author lihd
     * @date 2022/8/24 16:08
     */
    public static int candy1(int[] ratings) {
        int n = ratings.length;
        int[] prev = new int[n];
        int[] last = new int[n];

        prev[0] = 1;
        for (int i = 1; i < n; i++) {
            prev[i] = ratings[i] > ratings[i - 1] ? prev[i - 1] + 1 : 1;
        }

        last[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            last[i] = ratings[i] > ratings[i + 1] ? last[i + 1] + 1 : 1;
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += Math.max(prev[i], last[i]);
        }
        return ans;
    }

    /**
     * 3 ms, faster than 89.25%
     * 51.3 MB, less than 77.27%
     * @param ratings 小孩数组
     * @return int
     * @author lihd
     * @date 2022/8/31 21:22
     */

    public static int candy(int[] ratings) {
        int index = 0;
        int lbase = 1;
        int candy = 0;
        // 先处理第一个下坡
        index = findNextMin(index, ratings);
        // 获取第一个下坡的糖果数量, 并且index 向后移动了一位
        candy += getCandies(0, index ++);
        while (index < ratings.length) {
            if (ratings[index] > ratings[index - 1]) {
                // 说明是上坡
                candy += ++lbase;
                index ++;
            } else if (ratings[index] < ratings[index - 1]) {
                // 说明迎来了一个下坡
                int before = index - 1;
                // index 来到 最低点的位置
                index = findNextMin(index, ratings);
                candy += getCandies(before, index);
                int rightAdd = (index - before + 1);
                int leftAdd = lbase;
                candy -= Math.min(rightAdd, leftAdd);
                lbase = 1;
                index ++;
            } else {
                // 说明两者相等
                lbase = 0;
                candy += ++lbase;
                index ++;
            }

        }
        return candy;
    }

    private static int findNextMin(int index, int[] ratings) {
        for (int i = index; i < ratings.length - 1; i++) {
            if (ratings[i] <= ratings[i + 1]) {
                return i;
            }
        }
        return ratings.length - 1;
    }


    private static int getCandies(int begin, int end) {
        int n = end - begin + 1;
        return (n + 1) * n / 2;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 1, 8, 6, 3, 5, 8,6,7,8,2,3,4};
        System.out.println(candy(arr));
        System.out.println(candy1(arr));

        System.out.println(findNextMin(3, arr));
    }




}
