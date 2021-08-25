package se.tedbrink;
import se.tedbrink.*;
import se.tedbrink.dao.PeopleImplementation;
import se.tedbrink.model.Person;

import java.util.Collection;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
      //  System.out.println("Hello World!");

        PeopleImplementation peopleImplementation = new PeopleImplementation();

        //Person Locally in java program
        Person person = new Person(0, "Musse", "Pigg");

        //System.out.println(person);

        //Persisting to DataBase
        person = peopleImplementation.create(person);

        //Print out
        System.out.println(person);

      //  System.out.println(PeopleImplementation.findById(1));

      //  Collection<Person> all = peopleImplementation.findAll();
      //  all.forEach(System.out::println);


    }
}
