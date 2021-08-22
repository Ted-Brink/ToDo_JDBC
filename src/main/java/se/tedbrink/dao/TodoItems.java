package se.tedbrink.dao;

import se.tedbrink.model.Person;
import se.tedbrink.model.Todo;

import java.util.ArrayList;
import java.util.Collection;

public interface TodoItems {

    ArrayList<Todo> create(Todo todo);   // ska inte returnera en ArrayList!!!!!!!!!!!

    Collection<Todo> findAll();

    Todo findById(int todoId);

    Collection<Todo> findByDoneStatus(boolean doneStatus);

    Collection<Todo> findByAssignee(int personId);

    Collection<Todo> findByAssignee(Person person);

    Collection<Todo> findByUnassignedToDoItems();

    Todo update(Todo todo);

    boolean deleteById(int todoId);
}