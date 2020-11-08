/**
 * Created by Rajiv on 10/31/2016.
 */
public class Permutation {
    public static void main(String[] args){
        permutation("","1234");
    }

    public static void permutation(String currentPerm, String remainingWord){
        if(remainingWord.isEmpty()){
            System.out.println("currentPerm = " + currentPerm+remainingWord);
        }
        else{
            for(int i=0;i<remainingWord.length();i++){
                permutation(currentPerm + remainingWord.charAt(i),remainingWord.substring(0,i)
                        +remainingWord.substring(i+1,remainingWord.length()));
            }
        }
    }
}
