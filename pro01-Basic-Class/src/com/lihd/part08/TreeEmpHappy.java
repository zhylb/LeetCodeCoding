package com.lihd.part08;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/6 15:19
 */
public class TreeEmpHappy {

    public static class Info{
        int exist;
        int notExist;
        public Info(int exist, int notExist) {
            this.exist = exist;
            this.notExist = notExist;
        }
    }
    public static Info getTreeInfo(Emp emp){
        if(emp.nexts.isEmpty()){
            return new Info(emp.happy, 0);
        }
        int exist = emp.happy;
        int notExist = 0;

        for (Emp next : emp.nexts) {
            Info curInfo = getTreeInfo(next);
            exist += curInfo.notExist;
            notExist += Math.max(curInfo.exist, curInfo.notExist);
        }
        return new Info(exist, notExist);
    }

}

class Emp{
    public int happy;
    List<Emp> nexts;
    public Emp(int happy) {
        this.happy = happy;
        nexts = new ArrayList<>();
    }
}