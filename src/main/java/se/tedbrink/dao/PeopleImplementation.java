package se.tedbrink.dao;

import se.tedbrink.db.MySQLConnection;
import se.tedbrink.model.Person;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class PeopleImplementation implements People {
    @Override
    public Person create(Person person) {

        String createSQL = "INSERT INTO person ( first_name, last_name) VALUES (?,?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int rowsAffected = 0;
        Person createdPerson = null;

        try {
            connection = MySQLConnection.getInstance().getConnection();

            preparedStatement = connection.prepareStatement(createSQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());

            rowsAffected = preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                person.setPersonId(resultSet.getInt(1));
            }

        } catch (SQLIntegrityConstraintViolationException exception) {
            System.out.println("Can not create: Violation of Constraint");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

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
                                resultSet.getInt("person_id"),
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

    //@Override
    public Person findById(int personId) {
        Person personFound = null;
        String findById = "SELECT * FROM person WHERE person_id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = MySQLConnection.getInstance().getConnection();

            preparedStatement = connection.prepareStatement(findById);
            preparedStatement.setInt(1, personId);

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
    public Collection<Person> findByName(String name) {    /////för- efternamn eller båda??? ///////////
        Collection<Person> personFound = new ArrayList<>();

        String findByName = "SELECT * FROM person WHERE last_name LIKE ? ";   // Får lägga till first_name AND last_name
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
    public Person update(Person person) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rowsAffected = 0;
        Person updatedPerson = null;

        try {
            connection = MySQLConnection.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("UPDATE person SET first_name = ?, last_name = ? WHERE person_id = ?");
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setInt(3, person.getPersonId());

            rowsAffected = preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        if (rowsAffected > 0) {
            return person;
        } else {
            return null;
        }
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

            wasDeleted = preparedStatement.executeUpdate() >0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wasDeleted;
    }
}