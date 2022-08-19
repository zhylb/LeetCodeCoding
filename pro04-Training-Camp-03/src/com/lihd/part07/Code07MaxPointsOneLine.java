package com.lihd.part07;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/9 21:57
 */
public class Code07MaxPointsOneLine {
    public static class Point {
        public int x;
        public int y;

        Point() {
            x = 0;
            y = 0;
        }

        Point(int a, int b) {
            x = a;
            y = b;
        }
    }



    public static int maxPoints(Point[] points) {
        int ans = 1;
        int n = points.length;
        for (int i = 0; i < n - 1; i++) {
            int repeat = 1;//这里的重复包括自己
            int sameX = 0;//x 相同 即 斜率不存在
            int sameY = 0;//y 相同 说明斜率是0
            HashMap<Slope, Integer> map = new HashMap<>();//斜率存在且不为0
            for (int j = i + 1; j < n; j++) {
                //i 和 i + 1 ... j 所有的点
                if (points[i].x == points[j].x && points[i].y == points[j].y) {
                    repeat++;
                } else if (points[i].x == points[j].x) {
                    sameX++;
                } else if (points[i].y == points[j].y) {
                    sameY++;
                } else {
                    Slope slope = getSlope(points[i], points[j]);
                    map.put(slope, map.getOrDefault(slope, 0) + 1);
                }
            }
            Optional<Integer> optional = map.values().stream().max(Integer::compareTo);
            int max = repeat + Math.max(Math.max(sameX, sameY), optional.orElse(0));
            ans = Math.max(ans, max);
        }
        return ans;
    }

    public static class Slope{
        int m;
        int d;
        public Slope(int m, int d) {
            this.m = m;
            this.d = d;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Slope slope = (Slope) o;
            if (m != slope.m) return false;
            return d == slope.d;
        }

        @Override
        public int hashCode() {
            int result = m;
            result = 31 * result + d;
            return result;
        }
    }

    // a,b 为不同的点 a, b斜率一定存在且不为0
    public static Slope getSlope(Point a, Point b) {
        int y = a.y - b.y;
        int x = a.x - b.x;
        int gcd = gcd(y, x);
        return new Slope(y / gcd, x / gcd);
    }

    public static int gcd(int m, int n) {
        return n == 0 ? m : gcd(n, m % n);
    }

    public static void main(String[] args) {
        for (int j = 0; j < 50; j++) {
            Point[] points = new Point[1001];
            for (int i = 0; i <= 1000; i++) {
                points[i] = new Point((int) (Math.random() * 100), (int) (Math.random() * 100));
            }
            int maxPoints = maxPoints(points);
            int maxPointsTea = maxPointsTea(points);
            if (maxPoints != maxPointsTea) {
                System.out.println(maxPoints);
                System.out.println(maxPointsTea);
            }
//            System.out.println(" : " + maxPoints);


        }

    }

    //for test
    public static int maxPointsTea(Point[] points) {
        if (points == null) {
            return 0;
        }
        if (points.length <= 2) {
            return points.length;
        }
        // key : 分子  value : 分母表
        Map<Integer, Map<Integer, Integer>> map = new HashMap<Integer, Map<Integer, Integer>>();
        int result = 0;
        for (int i = 0; i < points.length; i++) {
            map.clear();
            int samePosition = 1;
            int sameX = 0;
            int sameY = 0;
            int line = 0;
            for (int j = i + 1; j < points.length; j++) {
                int x = points[j].x - points[i].x;
                int y = points[j].y - points[i].y;
                if (x == 0 && y == 0) {
                    samePosition++;
                } else if (x == 0) {
                    sameX++;
                } else if (y == 0) {
                    sameY++;
                } else {
                    int gcd = gcd(x, y);
                    x /= gcd;
                    y /= gcd;
                    if (!map.containsKey(x)) {
                        map.put(x, new HashMap<Integer, Integer>());
                    }
                    if (!map.get(x).containsKey(y)) {
                        map.get(x).put(y, 0);
                    }
                    map.get(x).put(y, map.get(x).get(y) + 1);
                    line = Math.max(line, map.get(x).get(y));
                }
            }
            result = Math.max(result, Math.max(Math.max(sameX, sameY), line) + samePosition);
        }
        return result;
    }



}
