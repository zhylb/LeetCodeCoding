package com.lihd.part06;

import javax.xml.ws.EndpointReference;
import java.util.HashMap;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/7 10:50
 */
public class Code05Split4Parts {

    public static boolean isSplit4PartsEqual(int[] arr) {

        if (arr == null || arr.length < 7) {
            return false;
        }
        HashMap<Integer, Integer> sumToIndexMap = new HashMap<>();
        int sum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sumToIndexMap.put(sum, i);
            sum += arr[i];
        }
        sumToIndexMap.put(sum, arr.length);
        int n = arr.length;
        //第一刀的位置
        int preSum = arr[0];
        for (int i = 1; i < n - 5; i++) {
            Integer a = sumToIndexMap.get(preSum * 2 + arr[i]);
            if (a != null && a != n) {
                Integer b = sumToIndexMap.get(preSum * 3 + arr[i] + arr[a]);
                if (b != null && b!= n) {
                    if (preSum * 4 + arr[i] + arr[a] + arr[b] == sum) {
                        return true;
                    }
                }
            }
            preSum += arr[i];
        }
        return false;

    }

    public static void main(String[] args) {

    }

}
