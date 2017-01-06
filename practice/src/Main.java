import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        List<String> input = new ArrayList<>();
        input.add("1");
        input.add("2");
        input.add("3");

        powerSet(input);
    }

    public static void powerSet(List<String> x){
        List<Set<String>> p = new ArrayList<>();
        Set s = new HashSet<>();
        p.add(s);
        Set t = new HashSet<>();
        while(!x.isEmpty()){
            String f = x.get(0);
            x.remove(0);
            for(int i=0;i<p.size();i++){
                t.addAll(Arrays.asList(p.get(i),x));
                p.add(t);
            }
            System.out.println("f = " + x.size());
        }
        System.out.println("p = " + p);
    }

    public static void greaterSmaller(int input){
        int[] abc = new int[]{1,5,2,1,10};
        int greater = 0;
        int smaller = 0;
        for(int i=0;i<abc.length;i++){
            if(input>abc[i]) greater++;
            else if(input<abc[i]) smaller++;
        }
        System.out.println("smaller = " + smaller + ", greater = "+greater);
    }
}
