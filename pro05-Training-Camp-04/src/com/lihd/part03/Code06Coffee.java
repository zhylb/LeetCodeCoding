package com.lihd.part03;

import ans.class04.Code06_Coffee;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/12 12:35
 */
public class Code06Coffee {


    public static class Machine{
        int begin;
        int cost;

        public Machine(int begin, int cost) {
            this.begin = begin;
            this.cost = cost;
        }
    }


    public static int[] fastGetCoffeeTime(int[] arr, int person) {

        PriorityQueue<Machine> heap = new PriorityQueue<>((a, b) -> a.begin + a.cost - b.begin - b.cost);

        for (int machine : arr) {
            heap.add(new Machine(0, machine));
        }

        int[] ans = new int[person];

        for (int i = 0; i < person; i++) {
            Machine machine = heap.poll();
            machine.begin = machine.cost + machine.begin;
            ans[i] = machine.begin;
            heap.add(machine);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 7};
        int[] coffeeTime = fastGetCoffeeTime(arr, 100);
        int[] bestChoices = Code06_Coffee.bestChoices(arr, 100);
        System.out.println(Arrays.toString(coffeeTime));
        System.out.println(Arrays.toString(bestChoices));

    }
}
