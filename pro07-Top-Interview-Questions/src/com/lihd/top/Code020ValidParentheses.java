package com.lihd.top;

import java.util.Stack;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/2 12:49
 */
public class Code020ValidParentheses {

    /**
     * 使用一个栈即可, 控制进栈出栈的时机
     * 4 ms, faster than 30.64%, 42 MB, less than 52.12%
     * @param s 字符串
     * @return boolean
     * @author lihd
     * @date 2022/9/2 12:55
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case '(' :
                    stack.push(')');
                    break;
                case '[':
                    stack.push(']');
                    break;
                case '{' :
                    stack.push('}');
                    break;
                default:
                    // 如果不能正确配对, 返回false
                    if (stack.isEmpty() || stack.pop() != s.charAt(i)) {
                        return false;
                    }
            }
        }
        // 最终栈必须是空, 否则返回false
        return stack.isEmpty();


    }
}
