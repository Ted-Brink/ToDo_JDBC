
package se.tedbrink.dao;

import se.tedbrink.db.MySQLConnection;
import se.tedbrink.model.Person;
import se.tedbrink.model.Todo;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class TodoItemsImplementation implements TodoItems{

    @Override
    public Todo create(Todo todo) {

        String createSQL = "INSERT in todo_item" +
                "(title, description, deadline, done, assignee_id) VALUES(?,?,?,?,?,?)"; ////// assignee hämtas från person /////////
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int rowsAffected = 0;
        Todo createdTodo = null;

        try {
            connection = MySQLConnection.getInstance().getConnection();

            preparedStatement = connection.prepareStatement(createSQL, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, todo.getTitle());
            preparedStatement.setString(2, todo.getDescription());
            preparedStatement.setDate(3, todo.getDeadline());
            preparedStatement.setBoolean(4, todo.isDone());
         //   preparedStatement.setInt(5, todo.getAssignee_id());       // kollla upp Todo om Assignee

            rowsAffected = preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                todo.setTodoId(resultSet.getInt(1));
            }

        } catch (SQLIntegrityConstraintViolationException exception) {
            System.out.println("Can not create: Violation of Constraint");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return todo;
    }


    @Override
    public Collection<Todo> findAll() {
        Collection<Todo> todosFound = new ArrayList<>();

        String findAll = "SELECT * FROM todo_item";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{

            connection = MySQLConnection.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(findAll);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                todosFound.add(
                        new Todo(
                                resultSet.getInt("todo_id"),
                                resultSet.getString("title"),
                                resultSet.getString("description"),
                                resultSet.getDate("deadline"),
                                resultSet.getBoolean("done")      //int eller boolean???????????????????
                               // resultSet.getInt("assignee_id")      ///////// undra om det behövs??
                        )
                );
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return todosFound;
    }

    @Override
    public Todo findById(int todoId) {

       Todo todoFound = null;
       String findById = "SELECT * FROM todo_item WHERE todo_id = ?";
       Connection connection = MySQLConnection.getInstance().getConnection();
       PreparedStatement preparedStatement = null;
       ResultSet resultSet = null;

       try {
           connection = MySQLConnection.getInstance().getConnection();
           preparedStatement = connection.prepareStatement(findById);
           preparedStatement.setInt(1,todoId);

           resultSet = preparedStatement.executeQuery();

           if (resultSet.next()) {
               todoFound = new Todo(
                       resultSet.getInt("todo_id"),
                       resultSet.getString("title"),
                       resultSet.getString("description"),
                       resultSet.getDate("deadline"),
                       resultSet.getBoolean("done")         //boolean eller int //////////////////////
                     //  resultSet.getInt("assignee_id")           // Person eller int //////////////////////////
               );
           }

        } catch (SQLException exception) {
           exception.printStackTrace();
       }
        return null;
    }

    @Override
    public Collection<Todo> findByDoneStatus(boolean doneStatus) {
        Collection<Todo> findBydoneStatus = new ArrayList<>();

        String findByDoneStatus = "SELECT * FROM todo_item WHERE done = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLConnection.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(findByDoneStatus);
            preparedStatement.setBoolean(1,doneStatus);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                findBydoneStatus.add(
                        new Todo(
                                resultSet.getInt("todo_id"),
                                resultSet.getString("title"),
                                resultSet.getString("description"),
                                resultSet.getDate("deadline"),
                                resultSet.getBoolean("done")      //int eller boolean???????????????????
                                // resultSet.getInt("assignee_id")      ///////// undra om det behövs??
                        )
                );
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return findBydoneStatus;
    }

    @Override
    public Collection<Todo> findByAssignee(int personId) {
        Collection<Todo> assigneeFound = new ArrayList<>();

        String findByAssignee = "SELECT * FROM todo_item WHERE personID = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLConnection.getInstance().getConnection();

            preparedStatement = connection.prepareStatement(findByAssignee);
            preparedStatement.setInt(1, personId);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                assigneeFound.add(
                        new Todo(
                                resultSet.getInt("todo_id"),
                                resultSet.getString("title"),
                                resultSet.getString("description"),
                                resultSet.getDate("deadline"),
                                resultSet.getBoolean("done")      //int eller boolean???????????????????
                                //    resultSet.getInt("assignee_id")      ///////// undra om det behövs??
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assigneeFound;
    }




        @Override // sök bland person efter vilket assignee_id  /////////////// ATT GÖRA //////////////
    public Collection<Todo> findByAssignee(Person person) {
 //       Collection<Todo> assigneeFound = new ArrayList<>();
 //       String findByAssignee = " SELECT * FROM todo_item WHERE assignee_id = ?"

            return null;

    }



    @Override
    public Collection<Todo> findByUnassignedToDoItems() {
        Collection<Todo> unAssignedTodo = new ArrayList<>();

        String findByUnassignedToDoItems = "SELECT * FROM todo_items WHERE assignee_id = ?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLConnection.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(findByUnassignedToDoItems);
            resultSet = preparedStatement.executeQuery();
            preparedStatement.setInt(1,    );



        } catch (SQLException exception) {
            exception.printStackTrace();
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

