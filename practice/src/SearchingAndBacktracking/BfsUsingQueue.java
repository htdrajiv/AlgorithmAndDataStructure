package SearchingAndBacktracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BfsUsingQueue {
    public static void main (String[]args)
    {
        int[][] input = {
                {1, 0, 0, 1, 0, 0},
                {1, 1, 1, 0, 0, 1},
                {0, 1, 1, 0, 1, 1},
                {0, 0, 1, 0, 0, 1},
                {0, 1, 1, 1, 1, 1},
                {1, 0, 1, 0, 1, 0}
        };
        int dimX = 6;
        int dimY = 6;
        int startX = 0;
        int startY = 0;
        int destX = 2;
        int destY = 4;
        solveMaze(input,dimX,dimY,startX,startY,destX,destY);
    }

    /*
     *
     **/
    private static void solveMaze(int[][] input, int dimX, int dimY, int initialXpos, int initialYpos, int destXpos, int destYpos){
        Queue< BfsNode > q = new LinkedList<>();
        List<BfsNode> visited = new ArrayList<>();
        List<String> neighbours = new ArrayList<String>(){{
            add("left");add("right");add("top");add("bottom");
        }};
        BfsNode initial = new BfsNode(initialXpos, initialYpos);
        BfsNode destination = new BfsNode(destXpos,destYpos);
        q.add(initial);
        boolean pathFound = false;
        int path = 0;
        while(q.size() > 0){
            BfsNode n = q.poll();
            if(alreadyVisited(visited, n)) continue;
            System.out.println("Exploring "+n.toString());
            path++;
            visited.add(n);
            if(n.equals(destination)){
                System.out.println(n.toString()+" is destination. YAY!!");
                pathFound = true;
                break;
            }

            for(String str : neighbours){
                BfsNode neigh = getNeighbour(str, n, dimX, dimY);
                if(neigh != null){
                    System.out.println("node = "+neigh.toString()+", value = "+input[neigh.x][neigh.y]);
                    if(input[neigh.x][neigh.y] == 1){
                        q.add(neigh);
                    }
                }
            }
            printContent(n, visited, dimX, dimY, input);
        }

        if(pathFound){
            System.out.println("Visited Path: ");
            for(BfsNode node : visited){
                System.out.println(node.toString());
            }
        }else{
            System.out.println("Path not found to destination");
        }
    }

    private static boolean alreadyVisited(List<BfsNode> visited, BfsNode n){
        for(BfsNode node : visited){
            if(node.equals(n)){
                return true;
            }
        }
        return false;
    }

    private static BfsNode getNeighbour(String type, BfsNode n, int maxX, int maxY){
        switch (type) {
            case "left":
                if ((n.y - 1) < 0) return null;
                return new BfsNode(n.x, n.y - 1);
            case "right":
                if ((n.y + 1) >= maxY) return null;
                return new BfsNode(n.x, n.y + 1);
            case "top":
                if ((n.x - 1) < 0) return null;
                return new BfsNode(n.x - 1, n.y);
            case "bottom":
                if ((n.x + 1) >= maxX) return null;
                return new BfsNode(n.x + 1, n.y);
        }
        return null;
    }

    private static void printContent(BfsNode node, List<BfsNode> visited, int lenX, int lenY, int[][] input){
        int i = node.x; int j = node.y;
        try{
            Thread.sleep(1000);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        System.out.println("Currently at position : "+i+","+j);
        for(int k = 0;k<lenX;k++){
            for(int l=0;l<lenY;l++){
                if(i==k && j==l) {
                    System.out.print("" + i + "" + j + "   ");
                }else if(alreadyVisited(visited, new BfsNode(k,l))){
                    System.out.print("*"+ "    ");
                }
                else System.out.print(input[k][l] + "    ");
            }
            System.out.println("");
        }
    }
}

class BfsNode
{
    int x;
    int y;

    BfsNode(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj){
        BfsNode n2 = (BfsNode) obj;
        return this.x == n2.x && this.y == n2.y;
    }

    @Override
    public String toString(){
        return "("+this.x+","+this.y+")";
    }
}
