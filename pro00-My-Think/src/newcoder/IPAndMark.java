package newcoder;

import sun.misc.PostVMInitHook;

import java.awt.font.ShapeGraphicAttribute;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/2 20:26
 */
public class IPAndMark {

    public static void main(String[] args) {
        System.out.println(isMark("255.255.255.128"));
    }

    public static boolean isMark(String mark) {
        String[] markSplit = mark.split("\\.");
        if (markSplit.length != 4) {
            return false;
        }
        String s0 = Integer.toBinaryString(Integer.parseInt(markSplit[0]));
        String s1 = Integer.toBinaryString(Integer.parseInt(markSplit[1]));
        String s2 = Integer.toBinaryString(Integer.parseInt(markSplit[2]));
        String s3 = Integer.toBinaryString(Integer.parseInt(markSplit[3]));
        String s = full(s0) + full(s1) +full( s2) + full(s3);
        int index0 = s.indexOf('0');
        int index1 = s.lastIndexOf('1');
        return index1 != -1 && index0 != -1 && index0 - index1 == 1;
    }

    public static String full(String s) {
        int len = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8 - len; i++) {
            sb.append('0');
        }
        return sb.append(s).toString();
    }

}
