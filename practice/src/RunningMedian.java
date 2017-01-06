/**
 * Created by Rajiv on 11/8/2016.
 */
import java.io.File;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class RunningMedian {
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */

        RunningMedian runningMedian=new RunningMedian();
        Scanner inFile1 = new Scanner(new File("C:\\Users\\Rajiv\\Desktop\\input.txt"));

        // Original answer used LinkedList, but probably preferable to use ArrayList in most cases
        // List<String> temps = new LinkedList<String>();
        int[] arr = new int[100000];
        int token1;
        int count = 0;
        // while loop
        while (inFile1.hasNext()) {
            // find next line
            token1 = inFile1.nextInt();
            arr[count++] = token1;
        }
        count = 0;
        int loop = 100000;
        inFile1.close();
        while(loop-->0){
            int num=arr[count++];
            System.out.println(runningMedian.getMedian(num));
        }
    }

    PriorityQueue<Integer> upperQueue;
    PriorityQueue<Integer> lowerQueue;

    public RunningMedian()
    {
        lowerQueue=new PriorityQueue<Integer>(
                20,new Comparator<Integer>()
        {
            @Override
            public int compare(Integer o1, Integer o2)
            {
                return -o1.compareTo(o2);
            }
        });
        upperQueue=new PriorityQueue<Integer>();
        upperQueue.add(Integer.MAX_VALUE);
        lowerQueue.add(Integer.MIN_VALUE);
    }

    public double getMedian(int num)
    {
        //adding the number to proper heap
        if(num>=upperQueue.peek())
            upperQueue.add(num);
        else
            lowerQueue.add(num);
        //balancing the heaps
        if(upperQueue.size()-lowerQueue.size()==2)
            lowerQueue.add(upperQueue.poll());
        else if(lowerQueue.size()-upperQueue.size()==2)
            upperQueue.add(lowerQueue.poll());
        //returning the median
        if(upperQueue.size()==lowerQueue.size())
            return(upperQueue.peek()+lowerQueue.peek())/2.0;
        else if(upperQueue.size()>lowerQueue.size())
            return upperQueue.peek();
        else
            return lowerQueue.peek();

    }
}

