package com.lihd.part10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/17 21:31
 */
public class Code07Interview {

    public static class Student{
        String infoA;
        String infoB;
        String infoC;
        public Student(String infoA, String infoB, String infoC) {
            this.infoA = infoA;
            this.infoB = infoB;
            this.infoC = infoC;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Student student = (Student) o;
            return this.infoA.equals(student.infoA) || this.infoB.equals(student.infoB) || this.infoC.equals(student.infoC);
        }

        @Override
        public String toString() {
            return "Student{" +
                    "infoA='" + infoA + '\'' +
                    ", infoB='" + infoB + '\'' +
                    ", infoC='" + infoC + '\'' +
                    '}';
        }
    }

    public static int getCount(Student[] students) {
        HashMap<String, Student> mapA = new HashMap<>();
        HashMap<String, Student> mapB = new HashMap<>();
        HashMap<String, Student> mapC = new HashMap<>();
//        Code06UnionFind.UnionFind<Student> unionFind = new Code06UnionFind.UnionFind<>();
//        unionFind.addListInfo(Arrays.asList(students));
        UnionFind<Student> unionFind = new UnionFind<>();
        unionFind.addAll(Arrays.asList(students));
        for (Student student : students) {
            Student studentA = mapA.get(student.infoA);
            Student studentB = mapB.get(student.infoB);
            Student studentC = mapC.get(student.infoC);
            if (studentA != null) {
                unionFind.union(student, studentA);
            }
            if (studentB != null) {
                unionFind.union(student,studentB);
            }
            if (studentC != null) {
                unionFind.union(student,studentC);
            }
            mapA.put(student.infoA, student);
            mapB.put(student.infoB, student);
            mapC.put(student.infoC, student);
        }
        //返回值就是答案
        return unionFind.getPointNodesNumMapLen();
    }

    public static int getCountForce(Student[] students) {
        if (students == null || students.length == 0) {
            return 0;
        }
        int res = 1;

        for (int i = 0; i < students.length; i++) {
            for (int j = 0; j < students.length; j++) {
                if (!students[i].equals(students[j])) {
                    res ++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
//        Student student1 = new Student("aa", "bb", "cc1");
//        Student student2 = new Student("aa1", "bb1", "cc1");
//        Student student3 = new Student("aa2", "bb2", "cc2");
//        Student student4 = new Student("aa3", "bb3", "cc2");
//        Student[] students = {student1, student2, student3, student4};
//        int count = getCount(students);
//        int countForce = getCountForce(students);
//        System.out.println("count = " + count);
//        System.out.println("countForce = " + countForce);

        int range = 5;
        int len = 3;
        int testTime = 300;
        int stuLen = 20;
        for (int i = 0; i < testTime; i++) {
            Student[] studentArr = createStudentArr(stuLen, len, range);
            int count = getCount(studentArr);
            int countForce = getCountForce(studentArr);
            if (count != countForce) {
                System.out.println("studentArr = " + Arrays.toString(studentArr));
                System.out.println("count = " + count);
                System.out.println("countForce = " + countForce);
//                break;
            }
        }

    }

    //[1,range]
    public static int getRandomValue(int range) {
        return (int) (Math.random() * range) + 1;
    }

    public static String getRandomString(int len, int range) {
        len = getRandomValue(len);
        char[] chs = new char[len];
        for (int i = 0; i < len; i++) {
            chs[i] = (char) ('a' + getRandomValue(range) - 1);
        }
        return new String(chs);
    }

    public static Student[] createStudentArr(int stuLen, int len, int range) {
        stuLen = getRandomValue(stuLen);
        Student[] students = new Student[stuLen];
        for (int i = 0; i < stuLen; i++) {
            students[i] = new Student(getRandomString(len, range), getRandomString(len, range), getRandomString(len, range));
        }
        return students;
    }

}
