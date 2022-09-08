package com.lihd.top;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/8 22:08
 */
public class Code118PascalsTriangle {

    /**
     * 杨辉三角, 迭代即可 <br/>
     * 1 ms, faster than 73.46%, 41.6 MB, less than 73.24%<br/>
     * @param numRows 要生成的行数 >= 1
     * @return java.util.List<java.util.List<java.lang.Integer>>
     * @author lihd
     * @date 2022/9/8 22:13
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> lineOne = new ArrayList<>();
        lineOne.add(1);
        ans.add(lineOne);
        for (int i = 2; i <= numRows; i++) {
            // 先获取上一个列表
            List<Integer> last = ans.get(i - 2);
            List<Integer> cur = new ArrayList<>();
            cur.add(1);
            for (int j = 1; j < last.size(); j++) {
                cur.add(last.get(j - 1) + last.get(j));
            }
            cur.add(1);
            ans.add(cur);
        }
        return ans;
    }
}
