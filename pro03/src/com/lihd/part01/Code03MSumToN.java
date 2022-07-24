package com.lihd.part01;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/6/6 11:06
 */
public class Code03MSumToN {


    public static boolean isNiceNum(int num) {

        for (int i = 1; i <= num; i++) {
            int sum = 0;
            for (int j = i; j <= num; j++) {
                sum += j;
                if (sum == num && j > i) {
                    return true;
                }
                if (sum > num) {
                    break;
                }
            }

        }
        return false;
    }

    public static boolean isNiceNum2(int num) {

        if (num >= 4) {
            //num & (num - 1) == 0 证明是2的某次方
            return (num & (num - 1)) != 0;
        } else {
            return num == 3;
        }
    }


    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(i + " : "  + isNiceNum(i) + " : " + isNiceNum2(i));
        }
    }


}
