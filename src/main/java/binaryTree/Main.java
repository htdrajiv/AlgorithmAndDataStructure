package binaryTree;

/**
 * Created by Rajiv on 10/31/2016.
 */
public class Main {
    public static void main(String[] args){
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.add(15);
        binaryTree.add(20);
        binaryTree.add(8);
        binaryTree.add(9);
        binaryTree.add(2);
        binaryTree.add(5);
        binaryTree.add(16);
        binaryTree.add(17);
        binaryTree.display(binaryTree.rootNode);
        System.out.println("binaryTree = " + binaryTree.getHeight(binaryTree.rootNode));
//        System.out.println(binaryTree.find(15));
//        binaryTree.delete(15);
    }
}
