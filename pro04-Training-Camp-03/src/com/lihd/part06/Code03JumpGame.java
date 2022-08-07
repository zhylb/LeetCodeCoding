package com.lihd.part06;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/5 13:34
 */
public class Code03JumpGame {


    public static int minJumpNum(int[] jump) {

        int times = 0;
        int curRange = 0;
        int maxRange = -1;
        for (int i = 0; i < jump.length; i++) {
            if (i <= curRange) {
                //还在范围内
                maxRange = Math.max(maxRange, jump[i] + i);
            } else {
                times ++;
                curRange = maxRange;
                maxRange = -1;
            }
        }
        return times;
    }

    public static int[] randomArr() {
        return null;
    }

    public static void main(String[] args) {
        int[] arr = { 3, 2, 3, 1, 1, 4 };
        int minJumpNum = minJumpNum(arr);
        System.out.println("minJumpNum = " + minJumpNum);
    }

}
