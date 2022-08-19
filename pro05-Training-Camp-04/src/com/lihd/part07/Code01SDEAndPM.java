package com.lihd.part07;

import java.util.*;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/18 12:36
 */
public class Code01SDEAndPM {


    public static int[] workFinish(int pms, int sde, int[][] programs) {

        MPHeap mpHeap = new MPHeap(pms);
        PriorityQueue<Integer> programmerHeaps = new PriorityQueue<>();
        PriorityQueue<Project> projectHeap = new PriorityQueue<>((a, b) -> a.mtime - b.mtime);
        for (int i = 0; i < programs.length; i++) {
            projectHeap.add(new Project(i,programs[i][0],programs[i][1], programs[i][2], programs[i][3]));
        }
        for (int i = 0; i < sde; i++) {
            programmerHeaps.add(1);
        }
        int[] ans = new int[programs.length];
        int finish = 0;
        while (finish < ans.length) {
            Integer programmerFreeTime = programmerHeaps.poll();
            // 把这个时间内所有的项目都加入
            while (!projectHeap.isEmpty()) {
                if (projectHeap.peek().mtime <= programmerFreeTime) {
                    mpHeap.add(projectHeap.poll());
                } else {
                    break;
                }
            }
            if (mpHeap.isEmpty()) {
                // 一个项目也没有
                programmerHeaps.add(projectHeap.peek().mtime);
            } else {
                Project project = mpHeap.poll();
                int finishTime = project.cost + programmerFreeTime;
                ans[project.index] = finishTime;
                programmerHeaps.add(finishTime);
                finish++;
            }
        }
        return ans;
    }


    private static class MPHeap{
        List<PriorityQueue<Project>> mHeaps;
        Project[] arr;
        int[] indexArr;
        int size;

        public MPHeap(int managerNums) {
            mHeaps = new ArrayList<>();
            for (int i = 0; i < managerNums; i++) {
                mHeaps.add(new PriorityQueue<>(new ManagerComparator()));
            }
            arr = new Project[managerNums];
            indexArr = new int[managerNums];
            Arrays.fill(indexArr, -1);
            size = 0;
        }

        public void add(Project project) {
            int mIndex = project.mid - 1;
            mHeaps.get(mIndex).add(project);
            // 这个 maxProject 就是堆中 最大的
            Project maxProject = mHeaps.get(mIndex).peek();
            // 在数组上的位置
            int arrIndex = indexArr[mIndex];
            if (arrIndex == -1) {
                //说明没有进去过
                arr[size] = maxProject;
                indexArr[mIndex] = size;
                heapInsert(size);
                size++;
            } else {
                //说明进去过
                arr[arrIndex] = maxProject;
                heapfiy(arrIndex);
                heapInsert(arrIndex);
            }
        }

        public Project poll() {
            Project ret = arr[0];
            // 调整好经理堆
            int mIndex = ret.mid - 1;
            PriorityQueue<Project> heap = mHeaps.get(mIndex);
            heap.poll();
            if (heap.isEmpty()) {
                // 调整好堆
                size --;
                swap(0, size);
                arr[size] = null;
                heapfiy(0);
                // 调整好 索引表
                indexArr[mIndex] = -1;
            } else {
                arr[0] = heap.peek();
                heapfiy(0);
            }
            // 返回
            return ret;
        }


        public boolean isEmpty() {
            return size == 0;
        }

        private void heapInsert(int index) {
            int fatherIndex = (index - 1) / 2;
            while (arr[index].compareTo(arr[fatherIndex]) > 0) {
                swap(index, fatherIndex);
                index = fatherIndex;
                fatherIndex = (index - 1) / 2;
            }
        }

        private void heapfiy(int index) {
            int leftIndex = index * 2 + 1;
            while (leftIndex < size) {
                int maxIndex = leftIndex + 1 < size && arr[leftIndex + 1].compareTo(arr[leftIndex]) > 0 ? leftIndex + 1 : leftIndex;
                if (arr[maxIndex].compareTo(arr[index]) <= 0) {
                    //最大的孩子比不过父亲
                    break;
                }
                swap(index, leftIndex);
                index = leftIndex;
                leftIndex = index * 2 + 1;
            }
        }

        private void swap(int i, int j) {
            Project projectI = arr[i];
            Project projectJ = arr[j];
            indexArr[projectI.mid - 1] = j;
            indexArr[projectJ.mid - 1] = i;
            arr[i] = projectJ;
            arr[j] = projectI;
        }
    }

    private static class ManagerComparator implements Comparator<Project> {
        @Override
        public int compare(Project o1, Project o2) {
            if (o1.priority == o2.priority) {
                if (o1.cost == o2.cost) {
                    return (o1.mtime - o2.mtime);
                }
                return (o1.cost - o2.cost);
            }
            return -(o1.priority - o2.priority);
        }
    }

    private static class Project implements Comparable<Project>{
        int index;
        int mid;
        int mtime;
        int priority;
        int cost;

        public Project(int index, int mid, int mtime, int priority, int cost) {
            this.index = index;
            this.mid = mid;
            this.mtime = mtime;
            this.priority = priority;
            this.cost = cost;
        }



        @Override
        public int compareTo(Project o) {
            if (this.cost == o.cost) {
                return -(this.mid - o.mid);
            }
            return -(this.cost - o.cost);
        }
    }

    public static void main(String[] args) {
        int pms = 2;
        int sde = 2;
        int[][] programs = { { 1, 1, 1, 2 }, { 1, 2, 1, 1 }, { 1, 3, 2, 2 }, { 2, 1, 1, 2 }, { 2, 3, 5, 5 } };
        int[] ans = workFinish(pms, sde, programs);
        System.out.println(Arrays.toString(ans));
    }


}
