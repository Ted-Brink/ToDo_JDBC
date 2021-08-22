package se.tedbrink.model;

import se.tedbrink.data.PersonSequencer;

public class Person {
    private final int personId; ///////////////// AI i databasen
    private String firstName;
    private String lastName;
    private String name;

    public Person(String firstName, String lastName) {
        this.personId = PersonSequencer.nextPersonId(); //////////////// AI databasen
        this.firstName = firstName;
        this.lastName = lastName;
        name = this.firstName.concat(" " + this.lastName);  //////////////// Går säkert att göra på bättre sätt
    }

    public int getPersonID() {   ////////////// AI i databasen
        return personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {      // Används för test, kan ta bort.
        return "Person{" +
                //"personId=" + personId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}