package com.lihd.part03;

/**
 * 这个代码有局限性，不过容易理解
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/12 12:49
 */
public class Code04KTimesOneTime {

    /**
     * 一个数组中，只有一个数字出现了1次，其余数字出现了k 次 （k > 1）
     * 请使用O(1)的空间找出这个数。
     * @param arr 数组
	 * @param k 其他数字出现的次数 > 1
     * @return int 返回唯一出现一次的数字
     * @author lihd
     * @date 2022/8/12 12:50
     */
    public static int selectOneNumInKTimes(int[] arr, int k) {
        int[] eor = new int[32];
        for (int num : arr) {
            arrAdd(eor, convertBase10ToK(num, k), k);
        }
        return convertBaseKTo10(eor, k);
    }


    /**
     * 数组相加，把后一个结果加到前一个上面去
     * @param res 结果存到的数组，也是加数
	 * @param other 加数
	 * @param k k进制，这里是为了防止溢出。
     * @author lihd
     * @date 2022/8/12 13:13
     */
    public static void arrAdd(int[] res, int[] other, int k) {
        for (int i = 0; i < res.length; i++) {
            res[i] += other[i];
            //这一步是为了防止溢出，最后一步统一%k和每一步都 %k效果是一样的。
            res[i] %= k;
        }
    }

    /**
     * 10进制转，k进制
     * @param num 要转换的数字
     * @param k 机制数 > 1
     * @return int[] 返回32长度的数字，arr[0]代表k进制的最低位，此次类推。
     * @author lihd
     * @date 2022/8/12 12:54
     */
    public static int[] convertBase10ToK(int num,int k) {
        int[] ans = new int[32];
        int index = 0;
        while (num != 0) {
            ans[index++] = num % k;
            num /= k;
        }
        return ans;
    }
    /**
     * k进制数组转 10进制
     * @param num 代表k进制
	 * @param k k进制的k
     * @return int 返回对应的10进制
     * @author lihd
     * @date 2022/8/12 13:08
     */
    public static int convertBaseKTo10(int[] num, int k) {
        int ans = 0;
        int exp = 1;
        for (int i = 0; i < 32; i++) {
            ans += exp * num[i];
            exp *= k;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] test1 = { 1, 1, 1, 2, 6, 6, 2, 2, 10, 10, 10, 12, 12, 12, 6, 9 };
        System.out.println(selectOneNumInKTimes(test1, 3));

        int[] test2 = { -1, -1, -1, -1, -1, 2, 2, 2, 4, 2, 2 };
        System.out.println(selectOneNumInKTimes(test2, 5));
    }


}
