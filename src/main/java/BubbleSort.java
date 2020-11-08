/**
 * Created by Rajiv on 9/11/2016.
 */
public class BubbleSort {
    static int[] arr;
    public static void main(String[] args){
        arr = new int[]{7,2,5,9,8,4,7};
        sort();
        for(int i : arr){
            System.out.println("i = " + i);
        }
    }

    static void sort(){
        int len = arr.length;
        for(int i = 0; i < len; ++i) {
            for(int j=0;j<len-1;++j) {
                if(arr[j] > arr[j+1]){
                    swap(j,j+1);
                }
            }
        }
    }

    static void swap(int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
