package com.lihd.top;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/2 11:58
 */
public class Code022GenerateParentheses {

    public List<String> generateParenthesis(int n) {
        char[] decision = new char[n << 1];
        List<String> ans = new ArrayList<>();
        process(0, decision, 0, n, ans);
        return ans;
    }


    private void process(int i, char[] decision, int leftMinusRight, int leftRest, List<String> ans) {
        if (i == decision.length) {
            ans.add(String.valueOf(decision));
        } else {

            if (leftRest > 0) {
                decision[i] = '(';
                process(i + 1, decision, leftMinusRight + 1, leftRest - 1, ans);
            }

            if (leftMinusRight > 0) {
                decision[i] = ')';
                process(i + 1, decision, leftMinusRight - 1, leftRest, ans);
            }


        }
    }

}
