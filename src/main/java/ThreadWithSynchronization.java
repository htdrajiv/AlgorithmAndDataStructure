/**
 * Created by Rajiv on 12/2/2016.
 */
public class ThreadWithSynchronization{
    public static void main(String[] args){
        ThreadImplements threadImplements = new ThreadImplements(2,20);
        Thread t1 = new Thread(threadImplements);
        Thread t2 = new Thread(threadImplements);
        t1.start();
        t2.start();
    }
}

class ThreadImplements implements Runnable{
    int start;
    int end;
    Example example = new Example();
    public ThreadImplements(int start,int end){
        this.start = start;
        this.end = end;
    }
    @Override
    public void run() {
//        example.printIntegersSynchronizedBlock(this.start,this.end);
        example.synchronizedPrintIntegers(this.start,this.end);
    }
}

class Example{
    synchronized void synchronizedPrintIntegers(int start,int end){
        for(int i=start;i<end;i++){
            System.out.println("i = " + i);
        }
    }

    public void printIntegersSynchronizedBlock(int start, int end){
        synchronized (this){
            for(int i=start;i<end;i++){
                System.out.println("i = " + i);
            }
        }
    }
}
