package SearchingAndBacktracking;

import java.util.Iterator;
import java.util.Stack;

public class NQueenUsingStack {

    public static void main(String[] args){
        int N = 8;
        int dimX = 8;
        int dimY = 8;
        int row = 0, col = 0;
        Stack<NQueenNode> stack = new Stack<>();
        boolean solutionFound = false;
        int[][] board = {
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
        while(row < dimY){
            while(col < dimX){
                NQueenNode node = new NQueenNode(row, col);
                boolean isValidPos = isValidPos(node, stack);
                System.out.print("Node = " + node.toString() + ", isValidPos = " + isValidPos);
                if(isValidPos){
                    stack.push(node);
                    row++;
                    col =0;
                    // for animating each move
                    {
                        printBoard(dimX, dimY, board, stack);
                        delay(500);
                    }
                    continue;
                }

                // for animating each move
                {
                    NQueenNode nodeToPrint = new NQueenNode(row, col);
                    Stack stackToPrint = new Stack();
                    stackToPrint.addAll(stack);
                    stackToPrint.push(nodeToPrint);
                    printBoard(dimX, dimY, board, stackToPrint);
                    delay(500);
                }
                col++;

            }
            if(stack.size() == N){
                solutionFound = true;
                break;
            }

            NQueenNode n = stack.pop();
            col = n.y+1;
            row = n.x;
        }
        if(solutionFound){
            System.out.println("Solution found YAY!!");
            System.out.println("Positions:: " + stack.toString());
        }
    }

    static void delay( int milliseconds){
        try{
            Thread.sleep(milliseconds);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    static void printBoard(int dimX, int dimY, int[][] board, Stack stack){
        System.out.print("\n");
        for(int i=0;i<dimX; i++){
            for(int j=0;j<dimY;j++){
                NQueenNode node = new NQueenNode(i,j);
                if(!stack.contains(node)){
                    System.out.print(board[i][j]+"  ");
                }else{
                    System.out.print("*"+"  ");
                }
            }
            System.out.println("");
        }
    }

    static boolean isValidPos(NQueenNode node, Stack stack){
        Iterator<NQueenNode> itr = stack.iterator();
        while (itr.hasNext())
        {
            NQueenNode n = itr.next();
            // for row and columns
            if(node.x == n.x || node.y == n.y){
                return false;
            }
            // for diagonal positions
            else if(Math.abs(n.x - node.x) == Math.abs(n.y - node.y)){
                return false;
            }
        }
        return true;
    }
}

class NQueenNode
{
    int x;
    int y;

    NQueenNode(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj){
        NQueenNode n2 = (NQueenNode) obj;
        return this.x == n2.x && this.y == n2.y;
    }

    @Override
    public String toString(){
        return "("+this.x+","+this.y+")";
    }
}
