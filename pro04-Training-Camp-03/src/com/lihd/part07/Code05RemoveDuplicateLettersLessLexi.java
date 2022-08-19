package com.lihd.part07;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/9 13:33
 */
public class Code05RemoveDuplicateLettersLessLexi {

    public static String removeDuplicateLetters(String s) {
        if (s.length() < 2) {
            return s;
        }
        char[] chs = s.toCharArray();
        int[] map = new int[256];
        //map初始化
        for (char ch : chs) {
            map[ch]++;
        }
        int smallIndex = 0;
        for (int i = 0; i < chs.length; i++) {
            map[chs[i]] --;
            if (chs[smallIndex] > chs[i]) {
                smallIndex = i;
            }
            if (map[chs[i]] == 0) {
                break;
            }
        }

        String ans = s.substring(smallIndex).replaceAll(String.valueOf(chs[smallIndex]), "");
        return chs[smallIndex] + removeDuplicateLetters(ans);
    }

    public static void main(String[] args) {
        String s = "dbcacabca";
        System.out.println(removeDuplicateLetters(s));
    }

}
