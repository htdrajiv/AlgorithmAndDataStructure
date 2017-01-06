package arrayQuestions;

import java.util.*;

/**
 * Created by Rajiv on 1/5/2017.
 */
public class MazeSolverUsingBFS {
    public static void main(String[] args){
        int[][] input = new int[][]{{1,0,0,0},{1,1,1,1},{1,1,0,1},{0,1,1,1}};
        maze(input);
    }

    public static void maze(int[][] input){
        String format = "\r%3d%% %s %c";
        Map<String,Boolean> checkedNode = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        queue.add("00");
        checkedNode.put("00",true);
        int len = input[0].length;
        List<String> path = new ArrayList<>();
        while(!queue.isEmpty()){
            if(checkedNode.get("23")!=null && checkedNode.get("23")) break;
            String currPosition = queue.poll();
            int i = Integer.parseInt("" + currPosition.charAt(0));
            int j = Integer.parseInt("" + currPosition.charAt(1));
            if(i+1 < len) {
                int tempi = i+1;
                String tempVal = tempi + "" + j;
                if ((checkedNode.get(tempVal)==null || !checkedNode.get(tempVal)) && input[i + 1][j] == 1) {
                    queue.add(tempVal);
                    checkedNode.put(tempVal,true);
                    path.add(tempVal);
                }
            }
            if(j+1 < len) {
                int tempj = j + 1;
                String tempVal = i + "" + tempj;
                if ((checkedNode.get(tempVal)==null || !checkedNode.get(tempVal)) && input[i][j + 1] == 1) {
                    queue.add(tempVal);
                    checkedNode.put(tempVal, true);
                    path.add(tempVal);
                }
            }
            if(i-1>=0) {
                int tempi = i - 1;
                String tempVal = tempi + "" + j;
                if ((checkedNode.get(tempVal)==null || !checkedNode.get(tempVal)) && input[i - 1][j] == 1) {
                    queue.add(tempVal);
                    checkedNode.put(tempVal, true);
                    path.add(tempVal);
                }
            }
            if(j-1>=0){
                int tempj = j - 1;
                String tempVal = i + "" + tempj;
                if((checkedNode.get(tempVal)==null || !checkedNode.get(tempVal)) && input[i][j-1] == 1) {
                    queue.add(tempVal);
                    checkedNode.put(tempVal, true);
                    path.add(tempVal);
                }
            }
            System.out.println(""+i+""+j);
            for(int k = 0;k<len;k++){
                for(int l=0;l<len;l++){
                    System.out.print(input[k][l]+" ");
                }
                System.out.println("\r");
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(String s:path){
            System.out.println("s = " + s);
        }
    }
}