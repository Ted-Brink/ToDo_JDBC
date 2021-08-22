package se.tedbrink.dao;

import se.tedbrink.model.Person;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class PeopleImplementation implements People {


       private ArrayList<Person> personer = new ArrayList<Person>();
//    @Override
    public ArrayList<Person> create(Person person) { /////// Create ska inte returnera en ArrayList!!!!!!!!!!!!!!
        personer.add(person);
        return personer;
    }

//    @Override
//    public Person create(Person person) {       // Fel, Hur skapar jag en person.
//        Person nyPerson = new Person();         // hur används konstruktorn i Person-klassen?
//        return person;
//    }

    @Override
    public Collection<Person> findAll() {
        return Collections.unmodifiableCollection(personer);
    }

    @Override
    public Person findById(int personId) {
        for (Person person : personer) {
            if (person.getPersonID() == personId) {
                return person;
            }
        }
        return null;
    }

    @Override
    public Collection<Person> findByName(String name) {
        Collection<Person> nameMatching = new ArrayList<>();

        for (Person person : personer) {
            if (person.getName().trim().equals(person)) {         // kolla upp om hela namnet eller för eller efternamn
                nameMatching.add(person);
            }
        }
        return nameMatching;
    }

    @Override
    public Person update(Person person) {
        Person personUpdate = findById(person.getPersonID());
        if (personUpdate != null) {
            personUpdate.setFirstName(person.getFirstName());       // kolla upp om hela namnet och vilka persondata som finns !!!!!!!!!!!
        }
        return person;
    }

    @Override
    public boolean deleteById(int personId) {
        for (Person person : personer) {
            if (person.getPersonID() == personId) {
            }
            return personer.remove(person);
        }
        return false;
    }
}