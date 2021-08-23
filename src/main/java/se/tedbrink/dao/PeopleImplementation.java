package se.tedbrink.dao;

import se.tedbrink.db.MySQLConnection;
import se.tedbrink.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class PeopleImplementation implements People {


      /* private ArrayList<Person> personer = new ArrayList<Person>();
//    @Override
    public ArrayList<Person> create(Person person) { /////// Create ska inte returnera en ArrayList!!!!!!!!!!!!!!
        personer.add(person);
        return personer;
    }       */

    @Override
    public Person create(Person person) {

        String createSQL = "INSERT INTO person (person_id, first_name, last_name) VALUES (?,?,?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rowsAffected = 0;

        try {
            connection = MySQLConnection.getInstance().getConnection();

            preparedStatement = connection.prepareStatement(createSQL);
            preparedStatement.setInt(1, person.getPersonID());
            preparedStatement.setString(2, person.getFirstName());

            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException exception) {
            System.out.println("Can not create: Violation of Constraint");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    //        if (rowsAffected > 0) {  // bara om Create-metoden ska returnera en boolean
//            return true;
//        } else {
//            return false;
//        }
//
//  @Override
    public Collection<Person> findAll() {

        Collection<Person> personsFound = new ArrayList<>();

        // preparing
        String findAll = "SELECT * FROM person";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLConnection.getInstance().getConnection();

            preparedStatement = connection.prepareStatement(findAll);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                personsFound.add(
                        new Person(
                                resultSet.getInt("person_id"),    /////////////////////////////////////////////////////
                                resultSet.getString("first_name"),
                                resultSet.getString("last_name")
                        )
                );

            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return personsFound;
    }

    @Override
    public Person findById(int personId) {
        Person personFound = null;
        String findById = "SELECT * FROM person person_id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLConnection.getInstance().getConnection();

            preparedStatement = connection.prepareStatement(findById);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                personFound = new Person(
                        resultSet.getInt("person_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personFound;


    }

    @Override
    public Collection<Person> findByName(String name) {         ////////////////för- efternamn eller båda??????????? //////////////////
        Collection<Person> personFound = new ArrayList<>();

        String findByName = "SELECT * FROM person WHERE name LIKE ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLConnection.getInstance().getConnection();

            preparedStatement = connection.prepareStatement(findByName);
            preparedStatement.setString(1, name + "%");

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                personFound.add(
                        new Person(
                                resultSet.getInt("person_id"),
                                resultSet.getString("first_name"),
                                resultSet.getString("last_name")
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personFound;
    }

    @Override
    public Person update(Person person) {  ////////////////////// SKA GÖRA /////////////////////////////////////
        return null;
    }


    @Override
    public boolean deleteById(int personId) {
        String deleteById = "DELETE FROM person WHERE person_id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean wasDeleted = false;

        try {
            connection = MySQLConnection.getInstance().getConnection();

            preparedStatement = connection.prepareStatement(deleteById);
            preparedStatement.setInt(1, personId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wasDeleted;
    }
}