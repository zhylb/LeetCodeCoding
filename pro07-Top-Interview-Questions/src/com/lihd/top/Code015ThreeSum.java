package com.lihd.top;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/9/2 13:10
 */
public class Code015ThreeSum {

    /**
     * 解决 2sum, 3sum问题的一般解法 <br/>
     * 左右指针, 不算重复值 <br/>
     * 25 ms, faster than 91.11%, 46.6 MB, less than 92.92%<br/>
     *
     * @param nums 无序数组
     * @return java.util.List<java.util.List<java.lang.Integer>>
     * @author lihd
     * @date 2022/9/2 13:56
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        return threeSum(nums, 0);
    }


    private List<List<Integer>> threeSum(int[] nums, int k) {
        List<List<Integer>> ans = new ArrayList<>();


        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                List<List<Integer>> list = twoSum(nums, i + 1, nums.length - 1, k - nums[i]);
                for (List<Integer> integers : list) {
                    integers.add(0, nums[i]);
                    ans.add(integers);
                }
            }
        }
        return ans;


    }


    private List<List<Integer>> twoSum(int[] nums, int l, int r, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        int begin = l;
        while (l < r) {
            int sum = nums[l] + nums[r];
            if (sum > k) {
                r --;
            } else if (sum < k) {
                l++;
            } else {
                if (l == begin || nums[l] != nums[l - 1]) {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(nums[l]);
                    list.add(nums[r]);
                    ans.add(list);
                }
                l++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Code015ThreeSum sum = new Code015ThreeSum();
        int[] arr={ -1, 0, 1, 2, -1, -4};
        Arrays.sort(arr);
        System.out.println(sum.twoSum(arr, 1, arr.length - 1, 1));

        System.out.println(sum.threeSum(arr));
    }

}
