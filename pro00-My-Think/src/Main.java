import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.BrokenBarrierException;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int many = sc.nextInt();
        int[] arr = new int[num];
        for (int i = 0; i < num; i++) {
            arr[i] = sc.nextInt();
        }
        quickSort(arr, 0, arr.length -1 );
        for (int i = 0; i < many; i++) {
            System.out.print(arr[i] + " ");
        }

    }

    public static void quickSort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int[] partition = partition(arr, l, r);
        quickSort(arr, l, partition[0] - 1);
        quickSort(arr, partition[1] + 1,r);
    }


    public static int[] partition(int[] arr,int L, int R) {
        int l = L - 1;
        int r = R;
        int index = L;

        while (index < r) {
            if (arr[index] > arr[R]) {
                swap(arr, index , --r);
            } else if (arr[index] < arr[R]) {
                swap(arr, index++, ++l);
            } else {
                index++;
            }
        }
        swap(arr, index, R);
        return new int[]{l + 1, r - 1};
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}