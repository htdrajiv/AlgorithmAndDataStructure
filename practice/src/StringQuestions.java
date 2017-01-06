import java.util.*;

/**
 * Created by Rajiv on 10/30/2016.
 */
public class StringQuestions {
    public static void main(String []args){
//        System.out.println("Hello World");
//        reverseString("Hello world");
//        reverseNumber(12548);
//        countDuplicate("Programming");
//        countGivenChar("Programming",'m');
//        checkPalindromString("ProgrgorP");
//        removeDuplicateCharacterFromString("programming");
//        containsUniqueChar("aabcde");
//        removeDuplicateChars("programming");
//        subPalindroms("abaabcde");
        isAnagramUsingStringBuilder("angeq", "glean");
    }

    public static void reverseString(String input){
        char[] charArray = input.toCharArray();
        StringBuilder strBuilder = new StringBuilder();
        for(int i=charArray.length-1;i>=0;i--){
            strBuilder.append(charArray[i]);
        }
        System.out.println(strBuilder.toString());
    }

    public static boolean checkPalindromString(String input){
        char[] charArray = input.toCharArray();
        for(int i=0,j=charArray.length-1;i<charArray.length/2;i++,j--) {
            if (charArray[i] != charArray[j]){
                System.out.println("false");
                return false;
            }
        }
        System.out.println(" true ");
        return true;
    }

    public static void reverseNumber(int number){
        int reverse = 0;
        while(number!=0){
            reverse = reverse*10 + number%10;
            number = number / 10;
        }
        System.out.println(reverse);
    }

    public static void countDuplicate(String input){
        char[] charArray = input.toCharArray();

        Map<Character,Integer> countMap = new HashMap<>();
        for(int i=0;i<charArray.length;i++){
            if(countMap.containsKey(charArray[i])){
                countMap.put(charArray[i], countMap.get(charArray[i]) + 1);
            }
            else{
                countMap.put(charArray[i],1);
            }
        }
        for(Character ch : countMap.keySet()){
            if(countMap.get(ch)>1){
                System.out.println(ch +" : "+countMap.get(ch));
            }
        }
    }

    public static void countGivenChar(String input,char a){
        char[] characters = input.toCharArray();
        int counter = 0;
        for(int i=0;i<characters.length;i++){
            if(a==characters[i]) counter++;
        }
        System.out.println(counter);
    }

    public static void removeDuplicateCharacterFromString(String input){
        char[] characters = input.toCharArray();
        Set<Character> charSet = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for(char c:characters){
            if(charSet.add(c)){
                sb.append(c);
            };
        }
        System.out.println("After removing duplicates :::: " + sb.toString());
    }

    public static void containsUniqueChar(String input){
        boolean[] check = new boolean[256];
        for(int i=0;i<input.length();i++){
            int val = input.charAt(i);
            if(check[val]) {
                System.out.println("unique chars = false");
                return;
            }else check[val] = true;
        }
        System.out.println("unique chars = true");
    }

    public static void removeDuplicateChars(String input){
        boolean[] check = new boolean[256];
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<input.length();i++){
            char temp = input.charAt(i);
            int val = temp;
            if(!check[val]) {
                check[val] = true;
                stringBuilder.append(temp);
            }
        }
        System.out.println("after removing duplicate char :: "+stringBuilder.toString());
    }

    public static void subPalindroms(String input){
        StringBuilder stringBuilder = new StringBuilder();

        for(int i=0;i<input.length();i++){
            stringBuilder.append(input.charAt(i));
            stringBuilder.append("#");
        }
        String temp = stringBuilder.toString();
        System.out.println("temp = " + temp);
        StringBuilder stringBuilder1 = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        int j,k;
        List<String> tempList = new ArrayList<>();
        for(int i=1;i<temp.length();i++){
            j = i-1;
            k = i+1;
            while(j>=0 && k<temp.length()){
                if(temp.charAt(j)==temp.charAt(k) && temp.charAt(j)!='#'){
                    stringBuilder1.append(temp.charAt(j));
                    stringBuilder2.append(temp.charAt(k));
                }
                j--;
                k++;
            }
            tempList.add(stringBuilder1.toString());
        }
        for(String s:tempList)
            System.out.println("s = " + s);
    }

    public static void isAnagramUsingStack(String source,String input){
        if(source.length()!=input.length()){
            System.out.println("Not an anagram ");
            return;
        }
        Stack<Character> stack = new Stack<>();
        for(char c:source.toCharArray()){
            stack.push(c);
        }
        for(int i=0;i<input.length();i++){
            if(source.indexOf(input.charAt(i))>-1){
                stack.pop();
            }
        }
        if(stack.isEmpty()){
            System.out.println("An anagram");
        }else System.out.println("Not an anagram");
    }

    public static void isAnagramUsingStringBuilder(String s1, String s2){
        StringBuilder sb1 = new StringBuilder(s1);
        for(int i=0;i<s2.length();i++){
            int index = sb1.indexOf(""+s2.charAt(i));
            if(index > -1){
                sb1.deleteCharAt(index);
            }
        }
        if(sb1.length()>0){
            System.out.println(" Not an Anagram" );
        }else System.out.println(" An anagram");
    }
}
