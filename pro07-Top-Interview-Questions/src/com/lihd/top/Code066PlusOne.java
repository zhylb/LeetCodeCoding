package com.lihd.top;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/5 20:24
 */
public class Code066PlusOne {

    /**
     * 就是+1, 注意 arr[0] 是最高位 <br/>
     * 这个过程还可能导致数组扩容, 注意一下细节即可 <br/>
     * 0 ms, faster than 100.00%, 42.6 MB, less than 35.29%
     * @param digits 数组
     * @return int[]
     * @author lihd
     * @date 2022/9/5 20:31
     */
    public int[] plusOne(int[] digits) {

        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            } else {
                digits[i] = 0;
            }
        }
        // 到这里都没有进位, 说明有最高位的进位
        int[] ans = new int[digits.length + 1];
        // 没有必要拷贝, 因为能进位前面一定全是9, +1后全是0
        // System.arraycopy(digits, 0, ans, 1, digits.length - 1 + 1);
        ans[0] = 1;
        return ans;
    }
}
