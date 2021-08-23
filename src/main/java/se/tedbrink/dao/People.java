package se.tedbrink.dao;

import se.tedbrink.model.Person;
import java.util.Collection;

interface People {
Person create (Person person);

Collection<Person> findAll();

Person findById(int personId);

Collection<Person> findByName(String name);

Person update(Person person);

boolean deleteById(int personId);
}