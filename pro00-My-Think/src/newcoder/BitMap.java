package newcoder;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/2 15:55
 */
public class BitMap {
    private int range;

    private int len;
    private static final int BIT_LEN = 32;
    private int[] arr;
    public BitMap(int range) {
        this.range = range;

        int len = range / BIT_LEN;
        if (range % BIT_LEN != 0) {
            len ++;
        }
        arr = new int[len];
    }

    public void put(int val){
        int index = val / BIT_LEN;
        int bit = val % BIT_LEN;
        //不包含
        if((arr[index] & 1 << bit) == 0){
            len ++;
        }
        arr[index] = arr[index]  | 1 << bit;
    }

    public boolean contains(int val){
        int index = val / BIT_LEN;
        int bit = val % BIT_LEN;
        return (arr[index] & 1 << bit) != 0;
    }

    public void remove(int val) {
        int index = val / BIT_LEN;
        int bit = val % BIT_LEN;
        arr[index] = arr[index]  & ~(1 << bit);
    }
}
