package binaryTree;

/**
 * Created by Rajiv on 10/31/2016.
 */
public class Node {
    private Node leftChild;
    private Node rightChild;
    private Node parent;
    private int value;

    public Node(int value){
        this.value = value;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public boolean hasBothChild(){
        return (this.leftChild==null && this.rightChild==null);
    }

    public boolean hasLeftChild(){
        return this.leftChild==null;
    }

    public boolean hasRightChild(){
        return this.rightChild==null;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString(){
        return Integer.toString(this.value);
    }

}
