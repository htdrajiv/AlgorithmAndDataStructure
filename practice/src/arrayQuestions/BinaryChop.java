package arrayQuestions;

/**
 * Created by Rajiv on 1/10/2017.
 */
public class BinaryChop {
    public static void main(String[] args){
        System.out.println("result from recursive = " + chopRecursive(2,new int[]{1,2,3,4,5}));
        System.out.println("result from recursive = " + chop(2,new int[]{1,2,3,4,5}));
    }

    // using iteration to find the index of given int input in the array
    public static int chop (int searchTarget,int[] input){
        int lowIndex = 0; // lower index
        int highIndex = input.length - 1; // upper index
        int midIndex = (lowIndex+highIndex) / 2; // mid position
        while(lowIndex <= highIndex){
            if(input[midIndex] == searchTarget){
                return midIndex;
            } else if (searchTarget > input[midIndex]) {
                lowIndex = midIndex + 1;
            }else
                highIndex = midIndex - 1;
            midIndex = (lowIndex + highIndex) / 2;
        }
        return -1;
    }

    // using recursion
    public static int chopRecursive(int searchTarget,int[] input){
        return chopRecursiveHelper(searchTarget,input,0,input.length);

    }

    // recursive helper method to find the index of given int input in the array
    public static int chopRecursiveHelper (int searchTarget,int[] input, int lowIndex, int highIndex){
        if(lowIndex < highIndex){
           int midIndex = lowIndex + (highIndex - lowIndex) / 2;
            if(searchTarget == input[midIndex]){
                return midIndex;
            }
            else if(searchTarget < input[midIndex]){
                return chopRecursiveHelper(searchTarget, input, lowIndex, midIndex);
            }else {
                return chopRecursiveHelper(searchTarget,input,midIndex+1,highIndex);
            }
        }
        return -1;
    }
}
