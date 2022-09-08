package com.lihd.top;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/4 16:17
 */
public class Code048RotateImage {

    /**
     * 本题注意宏观调度, 不要处理每个位置的情况即可 <br/>
     * 0 ms, faster than 100.00%, 42.3 MB, less than 79.94%<br/>
     * @param matrix 要旋转的矩阵
     * @see Code054SpiralMatrix 矩阵宏观调度
     * @author lihd
     * @date 2022/9/4 16:27
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int rb = 0;
        int cb = 0;
        int re = n - 1;
        int ce = n - 1;
        while (rb < re) {
            rotateOneLayer(matrix, rb++, cb++, re--, ce--);
        }
    }

    // 把这一圈旋转了
    private void rotateOneLayer(int[][] matrix, int rb, int cb, int re, int ce) {
        int times = re - rb;
        for (int i = 0; i < times; i++) {
            int t = matrix[rb][cb + i];
            matrix[rb][cb + i] = matrix[re - i][cb];
            matrix[re - i][cb] = matrix[re][ce - i];
            matrix[re][ce - i] = matrix[rb + i][ce];
            matrix[rb + i][ce] = t;
        }
    }

}
