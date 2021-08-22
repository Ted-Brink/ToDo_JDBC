package se.tedbrink.dao;

import se.tedbrink.model.Person;
import se.tedbrink.model.Todo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class TodoItemsImplementation implements TodoItems{

//    @Override
//    public Todo create(Todo todo) {  // Hur skapar jag en Todo här????
//        Todo nyTodo = new Todo("");
//        return nyTodo;
//    }

    private ArrayList<Todo> todos = new ArrayList<>();      // Ska inte returnera en Arraylist!!!!!!!!!!!!

    public ArrayList<Todo> create(Todo todo) {
        todos.add(todo);
        return todos;
    }

    @Override
    public Collection<Todo> findAll() {

        return Collections.unmodifiableCollection(todos);
    }

    @Override
    public Todo findById(int todoId) {
        for (Todo todo : todos) {
            if (todo.getTodoId() == todoId) {
                return todo;
            }
        }
        return null;
    }

    @Override
    public Collection<Todo> findByDoneStatus(boolean doneStatus) {


        for (Todo todo : todos) {
            if (todo.isDone() == doneStatus) {
            }
        }
        return null;
    }

    @Override
    public Collection<Todo> findByAssignee(int personId) {
        for(Todo todo : todos) {
            if (todo.getAssignee().getPersonID() == personId) {
            }
            return todos;
        }
        return null;
    }

    @Override
    public Collection<Todo> findByAssignee(Person person) {
        for (Todo todo : todos) {
            if (todo.getAssignee().equals(person)) {
        }
        return todos;
    }
    return null;
    }

    @Override
    public Collection<Todo> findByUnassignedToDoItems() {
        for (Todo todo : todos) {
            if (todo.getAssignee()== null) {
            }
            return todos;
        }
        return null;
    }

    @Override
    public Todo update(Todo todo) {  ////////////////// Kolla vilka fält som finns i databasen

        Todo todoUpdate = findById(todo.getTodoId());
        if (todoUpdate != null) {
            todoUpdate.setDescription(todoUpdate.getDescription()); // Kolla upp vad som kan uppdateras
        }
        return todoUpdate;
    }

    @Override
    public boolean deleteById(int todoId) {
            for (Todo todo : todos) {
                if (todo.getTodoId()== todoId) {
                 return  todos.remove(todo);
                }
            }
            return false;
        }
    }