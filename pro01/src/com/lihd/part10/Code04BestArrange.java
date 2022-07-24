package com.lihd.part10;

import java.util.Arrays;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/17 23:24
 */
public class Code04BestArrange {

    public static class Program{
        int b;
        int e;

        public Program(int b, int e) {
            this.b = b;
            this.e = e;
        }
    }


    public static int getBestArrangeNum(Program[] pros) {
        Arrays.sort(pros, (a, b) -> a.e - b.e);
        int curTime = 0;
        int ans = 0;
        for (int i = 0; i < pros.length; i++) {
            if (curTime <= pros[i].b) {
                //当前时间 < 会议开始时间
                //可以安排
                curTime = pros[i].e;
                ans ++;
            }
        }
        return ans;
    }


}
