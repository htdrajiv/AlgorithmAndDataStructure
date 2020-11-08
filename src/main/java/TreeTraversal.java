import java.util.*;

/**
 * Created by Rajiv on 11/4/2016.
 */

class Node{
    Node left,right;
    int data;
    Node(int data){
        this.data=data;
        left=right=null;
    }
}

public class TreeTraversal {
    void top_view(Node root)
    {
        top_left(root.left);
        System.out.print(root.data+" ");
        top_right(root.right);
    }

    void top_left(Node root){
        if(root!=null){
            top_left(root.left);
            System.out.print(root.data+" ");
        }
    }

    void top_right(Node root){
        if(root!=null){
            System.out.print(root.data+" ");
            top_right(root.right);
        }
    }

    // tree traversal : in order
    void in_order(Node root){
        if(root!=null){
            in_order(root.left);
            System.out.print(root.data+" ");
            in_order(root.right);
        }
    }

    // tree level order traversal
    void LevelOrder(Node root)
    {
        int h = height(root);
        for (int i=1; i<=h+1; i++)
            printGivenLevel(root, i);

    }

    int height(Node root){
        if(root==null) return -1;
        return Math.max(1+height(root.left),1+height(root.right));
    }

    /* Print nodes at a given level */
    void printGivenLevel(Node root, int level)
    {
        if (root == null)
            return;
        if (level == 1)
            System.out.print(root.data+" ");
        else if (level > 1)
        {
            printGivenLevel(root.left, level-1);
            printGivenLevel(root.right, level-1);
        }
    }

    // another solution for tree level order traversal
    void levelOrder(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            Node temp = queue.poll();
            if(temp.left!=null){
                queue.add(temp.left);
            }
            if(temp.right!=null){
                queue.add(temp.right);
            }
            System.out.print(temp.data+" ");
        }

    }

    // insert into binary tree
    static Node Insert(Node root,int value)
    {
        Node newNode = new Node(value);
        if(root==null) {
            root = newNode;
            return root;
        }
        Node tempNode = root;
        Node parent = null;
        boolean stop = false;
        while(!stop){
            parent = tempNode;
            if(value < tempNode.data){
                tempNode = tempNode.left;
                if(tempNode==null){
                    parent.left = newNode;
                    stop = true;
                }
            }else{
                tempNode = tempNode.right;
                if(tempNode==null) {
                    parent.right = newNode;
                    stop = true;
                }
            }
        }
        return root;
    }

    // insert into binary tree using recursion
    public static Node insert(Node root,int data){
        if(root==null){
            return new Node(data);
        }
        else{
            Node cur;
            if(data<=root.data){
                cur=insert(root.left,data);
                root.left=cur;
            }
            else{
                cur=insert(root.right,data);
                root.right=cur;
            }
            return root;
        }
    }

    //  huffman decoding
    void decode(String S ,Node root)
    {
        if(S==null){
            System.out.println("");
            return;
        }
        char[] charArr = S.toCharArray();
        StringBuilder myStr = new StringBuilder();
        if(root==null) {
            System.out.println("");
            return;
        }
        else if(root.left==null && root.right==null){
            System.out.print(root.data);
            return;
        }


        Node realRoot = root;

        for(int i=0;i<charArr.length;i++){
            if(charArr[i]=='0'){
                if((root=root.left)!=null){
                    if(root.left==null && root.right==null){
                        myStr.append(root.data);
                        root = realRoot;
                    }
                }
            }else{
                if((root=root.right)!=null){
                    if(root.left==null && root.right==null){
                        myStr.append(root.data);
                        root = realRoot;
                    }
                }
            }
        }
        System.out.print(myStr.toString()+" ");
    }



    // balanced brackets
    public static void checkBalancedBracket(String input){
        input = input.replaceAll("\\s+","");
        int length = input.length();
        if(length%2!=0) {
            System.out.println("NO");
            return;
        }
        char[] charArr = input.toCharArray();
        if(closing(charArr[0])){
            System.out.println("NO");
            return;
        }
        Map<Character,Character> temp = new HashMap<>();
        temp.put('[',']');
        temp.put('{','}');
        temp.put('(',')');
        // [[{}]]
        Stack<Character> stack = new Stack<Character>();
        for(int i=0;i<length;i++){
            if(closing(charArr[i])){
                if(stack.isEmpty() || charArr[i]!=temp.get(stack.pop())) {
                    System.out.println("NO");
                    return;
                }
            }else{
                stack.push(charArr[i]);
            }
        }
        if(stack.isEmpty()){
            System.out.println("YES");
        }else
            System.out.println("NO");

    }

    public static boolean closing(char input){
        return (input=='}' || input==']' || input==')');
    }


    // contact challange
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);
        int repeat = scanner.nextInt();
        scanner.nextLine();
        String input = "";
        int i=0;
        LinkedList<String> list = new LinkedList<>();
        while(i<4){
            input = scanner.nextLine();
            String[] splitted = input.split(" ");
            StringBuilder sb = new StringBuilder();
            int counter=0;
            if(splitted[0].equals("add")){
                for(int j=1;j<splitted.length;j++){
                    sb.append(splitted[j]+" ");
                }
                list.push(sb.toString());
            }else{
                for(int k=1;k<splitted.length;k++){
                    sb.append(splitted[k]);
                }
                for(String s:list){
                    if(s.contains(sb.toString())){
                        counter++;
                    }
                }
                System.out.println(counter);
            }
            i++;
        }
    }
}
