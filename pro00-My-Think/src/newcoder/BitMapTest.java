package newcoder;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/2 16:07
 */
public class BitMapTest {
    public static void main(String[] args) {
        BitMap bitMap = new BitMap(10000);
        bitMap.put(9999);
        System.out.println(bitMap.contains(9999));
        System.out.println(bitMap.contains(9998));
        bitMap.remove(9999);
        System.out.println(bitMap.contains(9999));

    }
}
