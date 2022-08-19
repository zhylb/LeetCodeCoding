package com.lihd.part05;

import java.util.LinkedList;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/13 22:24
 */
public class Code03ExpressionCompute {

    public static int getValue(String s) {
        return value(s.toCharArray(), 0)[1];
    }

    /**
     * 递归过程,返回最终的结果
     * @param chs 表达式
	 * @param i 表达式所处的位置
     * @return int[]
     * @author lihd
     * @date 2022/8/13 23:47
     */
    public static int[] value(char[] chs, int i) {
        LinkedList<NumAndOP> list = new LinkedList<>();
        int num = 0;
        while(i < chs.length && chs[i] != ')'){
            if (chs[i] >= '0' && chs[i] <= '9') {
                num = num * 10 + chs[i] - '0';
                i ++;
            } else if (chs[i] != '(') {
                //说明是 运算符
                handleExp(list, new NumAndOP(num, chs[i]));
                num = 0;
                i ++;
            } else {
                //左括号,需要递归
                int[] value = value(chs, i + 1);
                i = value[0] + 1;
                num = value[1];
            }
        }
        handleExp(list,new NumAndOP(num , '+'));
        int val = getExpVal(list);
        return new int[]{i, val};
    }

    /**
     * 使用双端队列处理表达式, 会正确的处理
     * @param list 双端队列
	 * @param numAndOP 自定义对象
     * @author lihd
     * @date 2022/8/13 23:46
     */
    public static void handleExp(LinkedList<NumAndOP> list, NumAndOP numAndOP) {
        if (list.size() == 0) {
            list.add(numAndOP);
        }else{
            NumAndOP lastNP = list.peekLast();
            if (lastNP.op == '*') {
                list.pollLast();
                //乘
                numAndOP.val = lastNP.val * numAndOP.val;
                list.addLast(numAndOP);
            } else if (lastNP.op == '/') {
                list.pollLast();
                //除
                numAndOP.val = lastNP.val / numAndOP.val;
                list.addLast(numAndOP);
            } else {
                //加或者减
                list.addLast(numAndOP);
            }
        }
    }
    /**
     * 返回最终的结果,从 list的头部向后面算才正确
     * list其中一定只含有 +, - 操作,不会存在 * /
     * @param list 表达式链表
     * @return int  返回最终的结果
     * @author lihd
     * @date 2022/8/13 23:45
     */
    public static int getExpVal(LinkedList<NumAndOP> list) {
        //list里面肯定有
        int ans = 0;
        char nextOp = '+';
        while (!list.isEmpty()) {
            NumAndOP first = list.pollFirst();
            switch (nextOp) {
                case '+': ans = ans + first.val; break;
                case '-': ans = ans - first.val; break;
            }
            nextOp = first.op;
        }
        return ans;
    }

    public static class NumAndOP{
        int val;
        char op;
        public NumAndOP(int val, char op) {
            this.val = val;
            this.op = op;
        }

    }



    public static void main(String[] args) {
        String exp = null;
        exp = "48*((70-65)-43)+8*1";
        System.out.println(getValue(exp));

        exp = "4*(6+78)+53-9/2+45*8";
        System.out.println(getValue(exp));

        exp = "10-5*3";
        System.out.println(getValue(exp));

        exp = "-3*4";
        System.out.println(getValue(exp));

        exp = "3+1*4";
        System.out.println(getValue(exp));
    }




}
