package SearchingAndBacktracking;

import java.util.Stack;

public class SudokuUsingStack {

    public static void main(String[] args){
        // our input sudoku puzzle
        int[][] givenBoard = {
                {5,3,0, 0,7,0,  0,0,0},
                {6,0,0, 1,9,5,  0,0,0},
                {0,9,8, 0,0,0,  0,6,0},

                {8,0,0, 0,6,0,  0,0,3},
                {4,0,0, 8,0,3,  0,0,1},
                {7,0,0, 0,2,0,  0,0,6},

                {0,6,0, 0,0,0,  2,8,0},
                {0,0,0, 4,1,9,  0,0,5},
                {0,0,0, 0,8,0,  0,7,9}
        };

        int dimX = 9, dimY = 9, low = 1;
        int[][] sudoku = new int[dimX][dimY];

        // create copy to work on
        for(int i=0;i<dimX;i++){
            System.arraycopy(givenBoard[i], 0, sudoku[i], 0, dimY);
        }

        // stack data structure for backtracking
        Stack<SudokuNode> stack = new Stack<>();

        for(int i = 0; i < dimX; i++){
            for(int j = 0; j < dimY; j++){
                SudokuNode node = new SudokuNode(i,j);

                // check if original board has fix value
                boolean isPositionAvailable = isPosAvailable(node, givenBoard);
                System.out.println("Node = "+ node.toString() + ", isPositionAvailable = "+isPositionAvailable);
                break1:
                // proceed if position is empty
                if(isPositionAvailable){
                    for(int num = low; num <= 9; num++){
                        node.setValue(num);
                        boolean isValidEntry = isValidEntry(node, sudoku);
                        System.out.println("Node = "+ node.toString() + ", isValidEntry = "+isValidEntry);

                        // check if entry is valid for cell. if valid, proceed to next cell by breaking this loop.
                        if(isValidEntry){
                            stack.push(node);
                            sudoku[i][j] = node.getValue();
                            System.out.println("stack.toString() = " + stack.toString());
                            low = 0;
                            break break1;
                        }
                    }
                    // if entry is not valid, backtrack to previous position to try next possible numbers.
                    SudokuNode sn = stack.pop();
                    sudoku[i][j] = 0;
                    i = sn.getX();
                    j = sn.getY()-1;
                    low = sn.getValue();
                }
            }
        }

        System.out.println("final stack.toString() = " + stack.toString());
        printBoard(sudoku, dimX, dimY);
    }

    // to print given board
    private static void printBoard(int[][] sudokuBoard, int dimX, int dimY){
        allTheWayOut:
        for(int i=0;i<dimX;i++){
            for(int j=0;j<dimY;j++){
                if(sudokuBoard[i][j] == 0){
                    System.out.println("Couldn't find solution for this board!!");
                    break allTheWayOut;
                }
                System.out.print(sudokuBoard[i][j]+"  ");
            }
            System.out.println();
        }
    }

    static void delay( int milliseconds){
        try{
            Thread.sleep(milliseconds);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    // check if any position is fixed
    private static boolean isPosAvailable(SudokuNode node, int[][] sudoku){
        return sudoku[node.getX()][node.getY()] == 0;
    }

    // check if entry is valid for this position.
    private static boolean isValidEntry(SudokuNode node, int[][] sudoku){
        // check row
        for(int i=0;i<9;i++){
            if(sudoku[node.getX()][i] == node.getValue()){
                return false;
            }
        }

        // check column
        for(int i=0;i<9;i++){
            if(sudoku[i][node.getY()] == node.getValue()){
                return false;
            }
        }

        // check inner board
        int x = node.getX() - node.getX() % 3;
        int y = node.getY() - node.getY() % 3;
        for(int i=x;i<x+3;i++){
            for(int j=y;j<y+3;j++){
                if(sudoku[i][j] == node.getValue()){
                    return false;
                }
            }
        }

        return true;
    }
}

// Node to represent each cell in sudoku board.
class SudokuNode
{
    private int x;
    private int y;
    private int value;

    SudokuNode(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj){
        SudokuNode n2 = (SudokuNode) obj;
        return this.value == n2.value;
    }

    @Override
    public String toString(){
        return "[("+this.x+","+this.y+")="+this.value+"]";
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

