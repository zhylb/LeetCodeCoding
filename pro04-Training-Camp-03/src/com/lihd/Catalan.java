package com.lihd;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/9 23:33
 */
public class Catalan {



    public static List<BigInteger> getCatalanList(int val) {
        List<BigInteger> list = new ArrayList<>(val + 1);
        list.add(BigInteger.valueOf(1));
        list.add(BigInteger.valueOf(1));
        for (int i = 2; i <= val; i++) {
            //通项公式 f(n) = f(n - 1) * (4n - 2) / (n + 1)
            BigInteger ans = list.get(i - 1).multiply(BigInteger.valueOf(4 * i - 2)).divide(BigInteger.valueOf(i + 1));
            list.add(ans);
        }
        return list;
    }

    public static void main(String[] args) {
        List<BigInteger> list = getCatalanList(100);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + " : " + list.get(i));
        }
    }

}
