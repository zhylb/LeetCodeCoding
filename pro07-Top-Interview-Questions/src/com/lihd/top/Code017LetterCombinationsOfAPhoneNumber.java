package com.lihd.top;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/2 13:59
 */
public class Code017LetterCombinationsOfAPhoneNumber {

    private final char[][] keyBoard = {
            {},
            {},
            {'a', 'b', 'c'},
            {'d', 'e', 'f'},
            {'g', 'h', 'i'},
            {'j', 'k', 'l'},
            {'m', 'n', 'o'},
            {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'},
            {'w', 'x', 'y', 'z'},
    };

    /**
     * 就是一个暴力递归
     * 1 ms, faster than 95.92%,  42.5 MB, less than 69.21%
     * @param digits 拨号字符串
     * @return java.util.List<java.lang.String>
     * @author lihd
     * @date 2022/9/2 14:07
     */
    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits.length() == 0) {
            return ans;
        }
        char[] decision = new char[digits.length()];
        process(0, decision, digits, ans);
        return ans;
    }

    private void process(int i, char[] decision, String digits, List<String> ans) {
        if (i == decision.length) {
            ans.add(String.valueOf(decision));
        } else {
            char ch = digits.charAt(i);
            for (char c : keyBoard[ch - '0']) {
                decision[i] = c;
                process(i + 1, decision, digits, ans);
            }
        }
    }


}
