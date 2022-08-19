package com.lihd.part04;

import java.util.*;

/**
 * 4514 ms
 * 时间消耗
 * 118.43 MB
 * 空间消耗
 * 您的提交打败了
 * 52.20 %
 * 的提交
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/16 10:07
 */
public class Code01BuildingOutline {

    public static class Op{
        int index;
        int val;

        public Op(int index, int val) {
            this.index = index;
            this.val = val;
        }
    }

    public static List<List<Integer>> buildingOutline(int[][] matrix) {

        Op[] ops = new Op[matrix.length * 2];
        for (int i = 0; i < matrix.length; i++) {
            ops[2 * i] = new Op(matrix[i][0], matrix[i][2]);
            ops[2 * i + 1] = new Op(matrix[i][1], -matrix[i][2]);
        }
        Arrays.sort(ops, new Comparator<Op>() {
            @Override
            public int compare(Op o1, Op o2) {
                if (o1.index == o2.index && o1.val * o2.val < 0) {
                    //符号不同
                    return o1.val > 0 ? 1 : -1;
                }
                return o1.index - o2.index;
            }
        });

        TreeMap<Integer, Integer> timesMap = new TreeMap<>();
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();

        for (Op op : ops) {
            if (op.val > 0) {
                timesMap.put(op.val, 1 + timesMap.getOrDefault(op.val, 0));
            } else {
                int val = - op.val;
                if (timesMap.get(val) == 1) {
                    timesMap.remove(val);
                } else {
                    timesMap.put(val, timesMap.get(val) - 1);
                }
            }
            if (timesMap.isEmpty()) {
                heightMap.put(op.index, 0);
            } else {
                heightMap.put(op.index, timesMap.lastKey());
            }

        }
        int height = 0;
        int startIndex = 0;
        List<List<Integer>> ans = new ArrayList<>();
        Set<Map.Entry<Integer, Integer>> entries = heightMap.entrySet();
        for (Map.Entry<Integer, Integer> entry : entries) {
            Integer index = entry.getKey();
            Integer val = entry.getValue();
            if (height != val) {
                if (height != 0) {
                    ans.add(Arrays.asList(startIndex, index, height));
                }
                startIndex = index;
                height = val;
            }
        }

        return ans;

    }


    public static void main(String[] args) {
        int[][] map = {{1, 3, 3}, {2, 4, 4}, {5, 6, 1}};
        System.out.println(buildingOutline(map));

    }

}
