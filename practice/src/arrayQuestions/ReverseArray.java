package arrayQuestions;

/**
 * Created by Rajiv on 1/4/2017.
 */
public class ReverseArray {
    public static void main(String[] args){
        int[] input = new int[]{2,4,8,7,9,6,3,5,10,11};
        System.out.println("original");
        for(int i:input){
            System.out.print(i + " ");
        }
        reverse(input);
        System.out.println();
        System.out.println("after reverse");
        for(int i:input){
            System.out.print(i + " ");
        }
    }

    public static void reverse(int[] input){
        for(int i=0,j=input.length-1;i<input.length/2;i++,j--){
            swap(input,i,j);
        }
    }

    public static void swap(int[] input,int i,int j){
        input[i] = input[i] + input[j];
        input[j] = input[i] - input[j];
        input[i] = input[i] - input[j];
    }
}
