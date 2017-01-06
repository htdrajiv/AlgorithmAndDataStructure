package binaryTree;

/**
 * Created by Rajiv on 10/31/2016.
 */
public class BinaryTree {
    Node rootNode;
    public BinaryTree(){
        this.rootNode = null;
    }

    public boolean isEmpty(){
        return this.rootNode==null;
    }

    public void display(Node root){
        if(root!=null){
            display(root.getLeftChild());
            System.out.println(" " + root.toString());
            display(root.getRightChild());
        }
    }

    public int getHeight(Node root){
        if(root==null)
            return -1;
        else{
           return Math.max(1 + getHeight(root.getLeftChild()),1 + getHeight(root.getRightChild()));
        }
    }

    public void add(int input){
        Node node = new Node(input);
        if(this.isEmpty()){
            this.rootNode = node;
            return;
        }
        Node tempNode = this.rootNode;
        Node parent = null;
        while(true){
            parent = tempNode;
            if(input < tempNode.getValue()){   // go for left child
                tempNode = tempNode.getLeftChild();
                if(tempNode==null) {
                    parent.setLeftChild(node);
                    node.setParent(parent);
                    return;
                }
            }else{   // go for right child
                tempNode = tempNode.getRightChild();
                if(tempNode==null){
                    parent.setRightChild(node);
                    node.setParent(parent);
                    return;
                }
            }
        }
    }

    public Node find(int value){
        if(this.isEmpty()) return null;
        Node tempNode = this.rootNode;
        if(tempNode.getValue()==value) return tempNode;
        while(true){
            if(value < tempNode.getValue()){ // look into the left child
                tempNode = tempNode.getLeftChild();
                if(tempNode==null){
                    System.out.println("on left child return null");
                    return null;
                }
                if(tempNode.getValue()==value){
                    System.out.println("on left child return tempNode");
                    return tempNode;
                }
            }else{  // look into the right child
                tempNode = tempNode.getRightChild();
                if(tempNode==null){
                    System.out.println("on right child return null");
                    return null;
                }
                if(tempNode.getValue()==value){
                    System.out.println("on right child return tempNode");
                    return tempNode;
                }
            }
        }
    }

    public Node getSuccessor(int value){
        try{
            Node tempNode = find(value);
            if(tempNode==null) {
                throw new NullPointerException("The given input do not exists in the tree");
            };
            if(tempNode.getRightChild()!=null){  // search in right child the smallest element
                tempNode = tempNode.getRightChild();
                if(tempNode.getLeftChild()==null) return tempNode;
                Node parent = null;
                while(true){
                    parent = tempNode;
                    tempNode = tempNode.getLeftChild();
                    if(tempNode==null)return parent;
                }
            }else{   // search for the first parent whose left child is also parent of the given node
                Node tempParent = tempNode.getParent();
                if(tempParent==null) return null;
                if(tempParent.getParent()==null) return null;
                if(tempParent.getLeftChild()==tempNode) return tempNode;
                if(tempParent.getParent().getLeftChild() == tempNode.getParent()) return tempNode.getParent().getParent();
            }
        }catch (Exception e){
            System.out.println("Unable to find the successor!");
            System.out.println("Following error occurs : "+e);
        }
        return null;  // just for returning.
    }

    public void delete(int value){
        Node node = find(value);
        if(node==null) {
            throw new NullPointerException("No node found to delete");
        }
        Node successor = getSuccessor(value);
        System.out.println("successor = " + successor);
        if(successor==null){
            node.getLeftChild().setParent(null);
            node.setLeftChild(null);
        }else{
            if(successor.hasRightChild()){
                successor.getRightChild().setParent(successor.getParent());
                if(successor.getParent().getLeftChild()==successor) successor.getParent().setLeftChild(successor.getRightChild());
                else successor.getParent().setRightChild(successor.getRightChild());
            }
            swapNode(node,successor);
            Node temp = node.getParent();
            node.setParent(null);
            if(temp.getLeftChild()==node) temp.setLeftChild(null);
            else if(temp.getRightChild()==node) temp.setRightChild(null);
        }
    }

    public void swapNode(Node node1,Node node2){
        Node tempNode = node1;
        node1.setLeftChild(node2.getLeftChild());
        node1.setRightChild(node2.getRightChild());
        node1.setParent(node2.getParent());
        if(node1.hasLeftChild()) node1.getLeftChild().setParent(node2);
        if(node1.hasRightChild()) node1.getRightChild().setParent(node2);

        node2.setLeftChild(tempNode.getLeftChild());
        node2.setRightChild(tempNode.getRightChild());
        node2.setParent(tempNode.getParent());
        if(node2.getLeftChild()!=null) node2.getLeftChild().setParent(tempNode);
        if(node2.getRightChild()!=null) node2.getRightChild().setParent(tempNode);
    }



}
