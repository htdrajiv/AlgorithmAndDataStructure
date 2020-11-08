/**
 * Created by Rajiv on 10/26/2016.
 */
public class Test {
    public static void main(String[] args){
        int[] abc = new int[]{1,5,2,1,10};
        greaterSmaller(abc,6);
        rotate(null,6);
    }
    public static void greaterSmaller(int[] abc,int input){
        int greater = 0;
        int smaller = 0;
        /* can be done with binary search in less time
            if the given input array is in sorted order.
        * */
        for(int i=0;i<abc.length;i++){
            if(input<abc[i]) greater++;
            else if(input>abc[i]) smaller++;
        }
        System.out.println("smaller = " + smaller + ", greater = "+greater);
    }

    public static void rotate(String abc,int input){
        int length=0;
        /*  we have to use == operator to check null because we
            cannot call any instance method on null object.
        */
        if(abc==null || (length = abc.length()) < 1 ){
            System.out.println("empty string");
            return;
        }
        /*  check if input number is greater than the length.
            if greater convert within the range of length of
            array. can also ignore or throw some message.
        */
        if(input>length)
            input = input%length;
        String substr = abc.substring(0,length-input);
        System.out.println("final rotated string = " + abc.substring(length-input,length)+substr);
    }
}
