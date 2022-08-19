package com.lihd.part06;

import java.util.HashMap;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/7 22:16
 */
public class Code02MostEOR {


    public static int mostEOR2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int eor = 0;
        int[] dp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
            if (map.containsKey(eor)) {
                Integer lastIndex = map.get(eor);
                if (lastIndex == -1) {
                    dp[i] = 1;
                } else {
                    dp[i] = dp[lastIndex] + 1;
                }

            }
            if (i > 0) {
                dp[i] = Math.max(dp[i], dp[i - 1]);
            }

            map.put(eor, i);
        }

        return dp[arr.length - 1];
    }








    public static int mostEOR(int[] arr) {

        if (arr == null || arr.length == 0) {
            return 0;
        }
        int eor = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int[] dp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
            if (map.containsKey(eor)) {
                Integer lastIndex = map.get(eor);
                if (lastIndex != -1) {
                    dp[i] = dp[lastIndex] + 1;
                }else{
                    dp[i] = 1;
                }
            }
            if (i != 0) {
                dp[i] = Math.max(dp[i], dp[i - 1]);
            }
            map.put(eor, i);
        }
        return dp[arr.length - 1];
    }
}
