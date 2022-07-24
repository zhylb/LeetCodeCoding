package com.lihd.part01;

import com.oracle.jrockit.jfr.UseConstantPool;
import org.omg.PortableInterceptor.INACTIVE;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/6/6 11:06
 */
public class Code01AppleMinBags {

    //获取 还剩num个袋子时，最少用几个袋子。
    public static int getMinBags(int num) {
        if (num < 0) {
            return -1;
        }
        if (num == 0) {
            return 0;
        }
        int use8 = getMinBags(num - 8);
        int use6 = getMinBags(num - 6);
        use8 = use8 < 0 ? Integer.MAX_VALUE : use8;
        use6 = use6 < 0 ? Integer.MAX_VALUE : use6;
        int min = Math.min(use8,use6);
        return min == Integer.MAX_VALUE ? -1 : min + 1;
    }

    public static int getMinBags2(int num) {
        if (num % 2 != 0) {
            return -1;
        }
        if (num < 18) {
            if(num == 0) return 0;
            if(num == 6 || num == 8) return 1;
            if(num == 12 || num == 14 || num == 16) return 2;
            return -1;
        } else {
           return  (num - 18) / 8 + 3;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(i + " : " + getMinBags(i) + " : " + getMinBags2(i));
        }
    }

}
