package com.lihd.part12;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/19 11:35
 */
public class Code01Hanoi {


    public static void move(String from, String to) {
        System.out.println(from + " -> " + to);
    }

    public static void hanoi(int height, String from, String to, String help) {
        if (height == 1) {
            move(from, to);
        } else {
            hanoi(height - 1, from, help, to);
            move(from, to);
            hanoi(height - 1, help, to, from);
        }
    }

    public static void hanoi(int height) {
        if (height >= 1) {
            hanoi(height, "A","C","B");
        }
    }

    public static void main(String[] args) {
        hanoi(5);
    }

}
