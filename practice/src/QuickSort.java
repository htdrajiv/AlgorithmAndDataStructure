import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Rajiv on 10/31/2016.
 */
public class QuickSort {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner inFile1 = new Scanner(new File("C:\\Users\\Rajiv\\Desktop\\input.txt"));

        // Original answer used LinkedList, but probably preferable to use ArrayList in most cases
        // List<String> temps = new LinkedList<String>();
        int[] arr = new int[50000];

        int token1;
        int abc = 0;
        // while loop
        while (inFile1.hasNext()) {

            // find next line
            token1 = inFile1.nextInt();
            arr[abc] = token1;
            abc++;
        }
        inFile1.close();

        //int[] input = new int[]{9,6,5,0,8,2,4,7};
//        quickSort(input,0,input.length-1);
        int count = 0;
        int temp = 0;
        int first = 0;
        int second = 0;
        int loop = 50000;
        while(loop-->0){
            for(int j = count-1 ; j > 0 ; j--){
                if(arr[j] < arr[j-1]){
                    temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
            }
            first = arr[count/2];
            second = 0;
            if(count%2==0){
                second = arr[count/2-1];
            }
            if(second==0)
                System.out.println((double)first);
            else
                System.out.println((double)(first+second)/2.0);
            // System.out.println("first :: "+first+", second :: "+second);


        }
    }

    public static void quickSort(int[] input,int s,int e){
        if(s<e){
            int pivot = partition(input,s,e);
            quickSort(input, 0, pivot - 1);
            quickSort(input, pivot + 1, e);
        }
    }

    private static int partition(int[] input,int s,int e){
        int i = s-1;
        int ab = input[e];
        for(int j=s;j<e;j++){
            if(input[j]<ab){
                i++;
                swap(input,i,j);
            }
        }
        i = i+1;
        swap(input,i,e);
        return i;
    }

    public static int findKthLargest(int[] nums,int end, int k) {
        int start = 0, index = end - k;
        while (start < end) {
            int pivot = partition(nums, start, end);
            if (pivot < index) start = pivot + 1;
            else if (pivot > index) end = pivot - 1;
            else return nums[pivot];
        }
        return nums[start];
    }

    private static void swap(int[] input,int i,int j){
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }
}
