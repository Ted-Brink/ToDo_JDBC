package se.tedbrink.dao;

import se.tedbrink.db.MySQLConnection;
import se.tedbrink.model.Person;
import se.tedbrink.model.Todo;

import java.sql.*;
import java.util.*;

public class TodoItemsImplementation implements TodoItems{

    @Override
    public Todo create(Todo todo) {

        String createSQL = "INSERT INTO todo_item (title, description, deadline, done, assignee_id) VALUES(?,?,?,?,?)";
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
            preparedStatement.setDate(3,   todo.getDeadline());                             //java.sql.Date.valueOf(todo.getDeadline()));
            preparedStatement.setBoolean(4, todo.isDone());
            preparedStatement.setInt(5, todo.getAssigneeId());

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
                                resultSet.getBoolean("done"),
                                resultSet.getInt("assignee_id")
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
       Connection connection = null;
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
                       resultSet.getBoolean("done"),
                       resultSet.getInt("assignee_id")
               );
           }

        } catch (SQLException exception) {
           exception.printStackTrace();
       }
        return todoFound;
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
                                resultSet.getBoolean("done"),
                                resultSet.getInt("assignee_id")
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

        String findByAssignee = "SELECT todo_id, title, description, deadline, done, assignee_id " +
                " FROM todo_item INNER JOIN person ON  assignee_id  = person_id WHERE assignee_id =?";


        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLConnection.getInstance().getConnection();

            preparedStatement = connection.prepareStatement(findByAssignee);
            preparedStatement.setInt(1, personId);                                                  /////////////////////////////////

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                assigneeFound.add(
                        new Todo(
                                resultSet.getInt("todo_id"),
                                resultSet.getString("title"),
                                resultSet.getString("description"),
                                resultSet.getDate("deadline"),
                                resultSet.getBoolean("done"),
                                resultSet.getInt("assignee_id")
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
    public Collection<Todo> findByUnAssignedToDoItems() {
        Collection<Todo> unAssignedTodo = new ArrayList<>();

        String findByUnassignedToDoItems = "SELECT * FROM todo_item WHERE assignee_id IS NULL";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLConnection.getInstance().getConnection();

            preparedStatement = connection.prepareStatement(findByUnassignedToDoItems);

            resultSet = preparedStatement.executeQuery();
           while (resultSet.next()) {
                unAssignedTodo.add(
                        new Todo(
                                resultSet.getInt("todo_id"),
                                resultSet.getString("title"),
                                resultSet.getString("description"),
                                resultSet.getDate("deadline"),
                                resultSet.getBoolean("done"),
                                resultSet.getInt("assignee_id")
                        )
                );
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return unAssignedTodo;
    }



    @Override
    public Todo update(Todo todo) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rowsAffected = 0;
        Todo updatedTodo = null;

        try {

            connection = MySQLConnection.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("UPDATE todo_item SET title = ?, description = ?, deadline = ?, done = ?, assignee_id = ? WHERE todo_id =?");
            preparedStatement.setString(1, todo.getTitle());
            preparedStatement.setString(2, todo.getDescription());
            preparedStatement.setDate(3,todo.getDeadline());
            preparedStatement.setBoolean(4, todo.isDone());
            preparedStatement.setInt(5, todo.getAssigneeId());
            preparedStatement.setInt(6,todo.getTodoId());

            rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return todo;
    }

    @Override
    public boolean deleteById(int todoId) {

        String deleteById = "DELETE FROM todo_item WHERE todo_id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean wasDeleted = false;

        try {
            connection = MySQLConnection.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(deleteById);
            preparedStatement.setInt(1, todoId);

            wasDeleted = preparedStatement.executeUpdate() > 0;

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return wasDeleted;
    }
}