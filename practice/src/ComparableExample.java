import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Rajiv on 1/8/2017.
 */
public class ComparableExample{

    public static void main(String[] args){
        List<Person> persons = new ArrayList<>();
        Person p1 = new Person("Hary",25);
        Person p2 = new Person("Lucy",23);
        Person p3 = new Person("Peter",28);
        Person p4 = new Person("Jimmy",19);
        Person p5 = new Person("Jackson",31);
        persons.add(p1);
        persons.add(p2);
        persons.add(p3);
        persons.add(p4);
        persons.add(p5);

        System.out.println("Before Sorting");
        for(Person p:persons){
            System.out.println(p.toString());
        }

        Collections.sort(persons);

        System.out.println("After Sorting");
        for(Person p:persons){
            System.out.println(p.toString());
        }
    }

}

class Person implements Comparable<Person>{
    public Person(String name,int age){
        this.name = name;
        this.age = age;
    }

    private String name;
    private int age;

    @Override
    public String toString(){
        return "Name = "+this.name+", Age = "+this.age;
    }

    @Override
    public int compareTo(Person person) {
        int result = 0;
        if(this.age > person.age) result = 1;
        else if(this.age < person.age) result = -1;
        return  result;
    }

}
