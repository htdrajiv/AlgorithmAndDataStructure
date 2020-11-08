import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Rajiv on 12/1/2016.
 */
public class SerializableExample {
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        Student s1 = new Student(1,"Rajiv Neupane");
        Student s2 = new Student(2,"Bhesh Raj Sejawal");
        List<Student> students = Arrays.asList(s1,s2);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("C:\\Users\\Rajiv\\Desktop\\TestFileSerilizable.txt"));
        objectOutputStream.writeObject(students);
        objectOutputStream.flush();
        objectOutputStream.close();
        System.out.println("Finished writing");

        students = null;
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("C:\\Users\\Rajiv\\Desktop\\TestFileSerilizable.txt"));
        students = (List<Student>) objectInputStream.readObject();
        System.out.println("student = " + students);
    }
}

class Student implements Serializable{
    transient int id;
    String name;
    public Student(int id,String name){
        this.id = id;
        this.name = name;
    }
}
