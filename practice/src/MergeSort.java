/**
 * Created by Rajiv on 10/31/2016.
 */
public class MergeSort {
    static int[] input = new int[]{9,6,5,0,8,2,4,7};
    public static void main(String[] args){
        mergeSort(0,input.length-1);
        for(int i:input){
            System.out.println("i = " + i);
        }
    }

    public static void mergeSort(int s,int e){
        if(s<e){
            int pivot = (s+e)/2;
            mergeSort(s,pivot);
            mergeSort(pivot+1,e);
        }
    }

    public static void merge(int[] input1,int[] input2){

    }
}
