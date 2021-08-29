package se.tedbrink;
import se.tedbrink.dao.*;
import se.tedbrink.model.*;
import java.sql.Date;
import java.util.Collection;

public class App {
    public static void main(String[] args) {
      //  System.out.println("Hello World!");

        //PeopleImplementation peopleImplementation = new PeopleImplementation();
/*
        //Person Locally in java program
        Person person = new Person(3, "Svarte", "Petter");
        System.out.println(person);

        // Överför till databasen
        person = peopleImplementation.create(person);

        //Print out
        System.out.println(person);
*/
        //// findAll() Hämta alla Personer från databasen
        //Collection<Person> allPerson = peopleImplementation.findAll();
        //allPerson.forEach(System.out::println);

        //// findById()/////
/*      System.out.println(peopleImplementation.findById(2));
        Collection<Person> all = peopleImplementation.findAll();
        all.forEach(System.out::println);
*/
        //// findByName() Finn person genom namn. Ska vara hela namnet - men nu görs sökningen på lastname.
        //peopleImplementation.findByName("Anka").forEach(System.out::println);

        //// update()  Uppdatering av person
/*      Person person = new Person("knatte", "Anka");
        person = peopleImplementation.create(person);
        System.out.println("Skapade personen = " + person);

        person.setFirstName("Fnatte");
        person = peopleImplementation.update(person);
        System.out.println("Updated person = " + person);
*/
        //// deleteById()  Ta bort person genom id ////
        //boolean wasDeleted = peopleImplementation.deleteById(8);
        //System.out.println("Borttagen " + wasDeleted);

        //-----------------------------------------------------------------------------------------

        //TodoItemsImplementation todoItemsImplementation = new TodoItemsImplementation();

        //// create (Todo)  /////// DATUMFORMATET ÄR FEL //////////////////////////////////////////////////////

        //Todo todo = new Todo("Ogräsrensning","Rensa bort ogräset i morotslandet",null,false,6);
        //todo = todoItemsImplementation.create(todo);
        //System.out.println("Skapad todo = " + todo);

        //// findAll() sök efter alla todo ///////
        //Collection<Todo> all = todoItemsImplementation.findAll();
        //all.forEach(System.out::println);

        //// findById(int) sök todo genom todoId /////
        //System.out.println(todoItemsImplementation.findById(2));

        //// findByDoneStatus(boolean) /////
        //Collection<Todo> doneStaus = todoItemsImplementation.findByDoneStatus(false);
        //doneStaus.forEach(System.out::println);

        //// findByAssignee(int) //////
        //Collection<Todo> assigned = todoItemsImplementation.findByAssignee(5);
        //assigned.forEach(System.out::println);

        //// findByAssignee(Person) HAR EJ GJORT DENNA ////////////////////////////////////////////////////////

        //// findByUnAssigneedTodoItems() /////
        //Collection<Todo> unassign = todoItemsImplementation.findByUnAssignedToDoItems();
        //unassign.forEach(System.out::println);

/*      Todo todo = new Todo("Städning" ,"dammsuga golvet",null,false,2);
        todo = todoItemsImplementation.create(todo);
        System.out.println("Created todo = " + todo);

        //// update(Todo)   ////
        todo.setAssigneeId(6);
        todo = todoItemsImplementation.update(todo);
        System.out.println("Uppdaterad post: " + todo);
*/
        //// deleteById(int) /////
        //boolean wasDeleted = todoItemsImplementation.deleteById(11);
        //System.out.println("Bortagen " + wasDeleted);
    }
}

///// ATT GÖRA

// Kunna söka på hela namnet. Nu går det bara söka på efternamnet.
// Sortering i bokstavsordning eller numerisk sortering.
// Ordna felet med datumformatet
// findByAssignee(Person) är inte gjord.