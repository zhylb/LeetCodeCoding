package com.lihd.top;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/4 19:25
 */
public class Code054SpiralMatrix {

    /**
     * 本题注意宏观调度, 不要处理每个位置的情况即可 <br/>
     * 0 ms, faster than 100.00%, 41.8 MB, less than 76.10%<br/>
     * @param matrix 矩阵
     * @return java.util.List<java.lang.Integer>
     * @see Code048RotateImage 旋转图片
     * @author lihd
     * @date 2022/9/4 19:33
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        int rb = 0;
        int cb = 0;
        int re = matrix.length - 1;
        int ce = matrix[0].length - 1;
        ArrayList<Integer> list = new ArrayList<>();
        while (rb <= re && cb <= ce) {
            list.addAll(getOneLayer(matrix, rb++, cb++, re--, ce--));
        }
        return list;
    }
    // 获取外面一层
    private List<Integer> getOneLayer(int[][] matrix, int rb, int cb, int re, int ce) {

        ArrayList<Integer> list = new ArrayList<>();
        if (rb == re) {
            for (int i = cb; i <= ce; i++) {
                list.add(matrix[rb][i]);
            }
        } else if (cb == ce) {
            for (int i = rb; i <= re; i++) {
                list.add(matrix[i][cb]);
            }
        } else {
            // 说明真的是一圈
            for (int c = cb; c < ce; c++) {
                list.add(matrix[rb][c]);
            }
            for (int r = rb; r < re; r++) {
                list.add(matrix[r][ce]);
            }
            for (int c = ce; c > cb; c--) {
                list.add(matrix[re][c]);
            }
            for (int r = re; r > rb; r--) {
                list.add(matrix[r][cb]);
            }
        }
        return list;
    }
}
