package com.lihd.follow;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/5 13:50
 */
public class Add02PrintStars {

    public static void printStars(int n) {
        char[][] map = new char[n][n];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                map[i][j] = ' ';
            }
        }
        int b = 0;
        int e = n - 1;
        while (b <= e) {
            fillOneLayer(map, b, b, e, e);
            b += 2;
            e -= 2;
        }
        for (char[] chars : map) {
            for (int j = 0; j < map.length; j++) {
                System.out.print(chars[j] + " ");
            }
            System.out.println();
        }
    }

    private static void fillOneLayer(char[][] map, int rb, int cb, int re, int ce) {
        for (int i = cb; i <= ce; i++) {
            map[rb][i] = '*';
        }
        for (int i = rb; i < re; i++) {
            map[i][ce] = '*';
        }
        for (int i = ce; i > cb; i--) {
            map[re][i] = '*';
        }
        for (int i = re - 1; i > rb + 1; i--) {
            map[i][cb + 1] = '*';
        }
    }

    public static void main(String[] args) {
        printStars(10);
    }

}
