package com.lihd.part08;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/18 20:24
 */
public class Code04HanoiProblem {


    public static int step(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        return process(arr, arr.length - 1, 1, 2, 3);
    }

    /**
     *
     * @param arr 汉诺塔数组
	 * @param index 第几层
	 * @param from 开始位置
	 * @param help 借助位置
	 * @param to 目标位置
     * @return int 是最优解中的第几步, -1代表不是最优解
     * @author lihd
     * @date 2022/8/19 22:41
     */
    public static int process(int[] arr, int index, int from, int help, int to) {

        if (index == -1) {
            return 0;
        }
        // from to help
        // from -> to
        // help from to
        if (arr[index] == help) {
            // 不可能出现
            return -1;
        }

        if (arr[index] == from) {
            //说明在 第二步 没有执行
            return process(arr, index - 1, from, to, help);
        } else {
            //说明前两步执行完毕
            int otherStep = process(arr, index - 1, help, from, to);
            if (otherStep == -1) {
                return otherStep;
            }
            return otherStep + (1 << index);
        }
    }

    public static void main(String[] args) {
        int[] arr = { 3, 3, 2, 1 };
        System.out.println(step(arr));
    }


}
