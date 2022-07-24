package com.lihd.part01;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/6/6 11:06
 */
public class Code02EatGrass {


    public static boolean firstMoverVictory(int N) {

        if (N == 1 || N == 3 ) {
            return true;
        }
        if (N == 2 || N == 0) {
            return false;
        }

        //如果现在草的数量大于等于 5
        for (int i = 1; i <= N; i <<= 2) {

            //我先吃这么多草。还剩curN个草
            int curN = N - i;
            //curN个草会给另一个动物吃，如果他先吃返回了 false ,证明他怎么也赢不了我，我就能返回true;
            if (!firstMoverVictory(N - i)) {
                return true;
            }
        }
        //我试过了所有可能性都赢不了，我输了
        return false;
    }

    public static boolean firstMoverVictory2(int N) {
        return N%5 != 0 && N%5 != 2;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 60; i++) {
            System.out.println(i + " : "  + firstMoverVictory(i) + " : " + firstMoverVictory2(i));
        }
    }
}
