package com.lihd.part08;

import ans.class08.Code03_OneNumber;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/9 12:16
 */
public class Code03OneNumber {


    public static int getOneNumber(int num) {
        //这一行不能少 但是为什么 因为num == 10 | num == 20 ...
        if (num < 1) {
            return 0;
        }


        //排除不合理情况
        int bitNum = getBitNum(num);
        if (bitNum == 1) {
            return 1;
        }

        // 原来的数是 89654 的话 这个值就是 10000
        int unitNum = (int) Math.pow(10, bitNum - 1);
        //最高位的值
        int high = num / unitNum;
        //最高位一的数量
        int highOneNum = high == 1 ? num % unitNum + 1 : unitNum;
        //其他位的一数量
        //int otherOneNum = high * (bitNum - 1) * (int) Math.pow(10, bitNum - 2);
        int otherOneNum = high * (bitNum - 1) * unitNum / 10;
        return highOneNum + otherOneNum + getOneNumber(num % unitNum);
    }


    public static int getBitNum(int num) {
        int ans = 0;
        while (num != 0) {
            num /= 10;
            ans ++;
        }
        return ans;
    }

    public static void main(String[] args) {
        int num = 109000;
        int solution1 = Code03_OneNumber.solution1(num);
        int oneNumber = getOneNumber(num);
        System.out.println(solution1);
        System.out.println(oneNumber);
    }


}
