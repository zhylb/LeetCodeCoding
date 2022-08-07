package com.lihd.part10;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/17 23:23
 */
public class Code02Light {


    public static void getAllLight(char[] chs, int index, String prefix, List<String > ans) {
        if (chs.length == index) {
            ans.add(prefix);
            return ;
        }
        if (chs[index] == 'X') {
            getAllLight(chs, index + 1, prefix + 0, ans);
        } else {
            getAllLight(chs, index + 1, prefix + '1', ans);
            getAllLight(chs, index + 1, prefix + '0', ans);
        }
    }

    public static void getMeaningLight(String road, List<String > checkList) {
        char[] roadChs = road.toCharArray();

        Iterator<String> iterator = checkList.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            char[] temp = s.toCharArray();
            for (int i = 0; i < road.length(); i++) {
                if (roadChs[i] == '.' && temp[i] == '0') {
                    if ((i != 0 && temp[i - 1] == '0') &&(i != road.length() - 1 && temp[i + 1] == '0' ) ) {
                        iterator.remove();
                        break;
                    }
                }
            }
        }
    }

    public static int getMinValue(List<String > checkList) {
        int min = Integer.MAX_VALUE;
        for (String s : checkList) {
            char[] chars = s.toCharArray();
            int curMin = 0;
            for (char aChar : chars) {
                if (aChar == '1') {
                    curMin++;
                }
            }
            min = Math.min(min, curMin);
        }
        return min;
    }


    public static int getMinLightNum(String road) {
        //贪心思路 : 当前来到的节点 不会被上个节点影响到 即 我不会被上一灯点亮
        char[] chs = road.toCharArray();

        int i = 0;
        int ans = 0;
        while (i < chs.length) {
            if (chs[i] == 'X') {
                i++;
            } else {
                ans ++;//无论如何都能放一个灯
                //是可以放灯的
                if (i + 1 < chs.length) {
                    //下一个位置存在
                    if (chs[i + 1] == 'X') {
                        i += 2;
                    } else {
                        //无论 i + 2是否存在 是否是X 是否是. 放在 i + 1处没有任何问题
                        i += 3;
                    }
                } else {
                    //没有下一个位置
                    i ++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String road = "X..XX.....XX.X.....X....XX.X";
        ArrayList<String> ans = new ArrayList<>();
        getAllLight(road.toCharArray(),0,"",ans);
        getMeaningLight(road,ans);
        int minValue = getMinValue(ans);
        int minLightNum = getMinLightNum(road);
        System.out.println("minValue = " + minValue);
        System.out.println("minLightNum = " + minLightNum);
    }
    
}
