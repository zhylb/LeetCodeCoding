package com.lihd.part01;

import java.util.LinkedList;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/26 10:05
 */
public class SlidingWindow {

    private int[] arr;
    private int left;
    private int right;
    // 小 -> 大
    private LinkedList<Integer> minDequeue;
    // 大 - > 小
    private LinkedList<Integer> maxDequeue;


    public SlidingWindow(int[] arr) {
        this.arr = arr;
        left = -1;
        right = -1;
        minDequeue = new LinkedList<>();
        maxDequeue = new LinkedList<>();
    }

    public boolean isEmpty() {
        return right == -1;
    }

    public boolean isFull() {
        return right == arr.length - 1;
    }

    public boolean rightOutIndex() {
        return right == arr.length;
    }



    private void add() {
        while (!maxDequeue.isEmpty() && arr[maxDequeue.peekLast()] <= arr[right]) {
            maxDequeue.pollLast();
        }
        while (!minDequeue.isEmpty() && arr[minDequeue.peekLast()] >= arr[right]) {
            minDequeue.pollLast();
        }
        //当前元素一定会进入 存的是下标
        maxDequeue.addLast(right);
        minDequeue.addLast(right);
    }

    public boolean leftCanMov() {
        return left <= right;
    }

    public void rightMove() {
        //先检验能不能右移
        if (isFull()) {
            return;
        }
        right ++;
        add();
    }

    public int getMax() {
        return arr[maxDequeue.peekFirst()];
    }

    public int getMin() {
        return arr[minDequeue.peekFirst()];
    }

    public boolean leftLessRight() {
        return left < right;
    }




    public void leftMove() {
        //先检验能不能左移
        if (left == right) {
            return;
        }
        //可以左移
        left ++;
        if (maxDequeue.peekFirst() < left) {
            maxDequeue.pollFirst();
        }
        if (minDequeue.peekFirst() < left) {
            minDequeue.pollFirst();
        }
    }


    public int leftIndex() {
        return left;
    }

    public int rightIndex() {
        return right;
    }

}
