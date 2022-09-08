package com.lihd.follow;

import java.util.HashMap;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/5 19:20
 */
public class Add03AddDivideNum {


    private final static HashMap<Integer, Integer> map = new HashMap<>();

    private final static int[] nums = {1, 10, 100, 1000, 10000, 10_0000, 100_0000, 1000_0000, 10000_0000};

    public static int ways(int n) {
        if (map.isEmpty()) {
            process(0, 123456789);
        }
        return map.getOrDefault(n, 0);
    }

    private static void process(int index, int num) {
        if (index == 9) {
            for (int add = 8; add >= 2; add--) {
                int p1 = num / nums[add];
                int rest = num % nums[add];
                for (int div = add / 2; div >= 1; div--) {
                    int p2 = rest / nums[div];
                    int p3 = rest % nums[div];
                    if (p2 % p3 == 0) {
                        int val = p1 + p2 / p3;
                        map.put(val, map.getOrDefault(val, 0) + 1);
                    }
                }
            }

        } else {
            for (int swap = index; swap <= 8; swap ++) {
                process(index + 1, swap(num, index, swap));
            }
        }
    }

    private static int swap(int num, int i, int j) {
        int bitI = (num / nums[i]) % 10;
        int bitJ = (num / nums[j]) % 10;
        return num + (bitJ - bitI) * nums[i] + (bitI - bitJ) * nums[j];
    }

    public static void main(String[] args) {
        System.out.println(ways(10));
        System.out.println(ways(100));
    }


}
