package ch.bfh.ti.webshop.persistence;

import ch.bfh.ti.webshop.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CustomerRepository.
 *
 * @author Thomas Ingold
 * @version 1.0
 */

@Component
@Slf4j
public class CustomerDAO {

    @Value("${spring.datasource.driver-class-name}")
    private String JDBC_DRIVER;

    @Value("${spring.datasource.url}")
    private String DATABASE_URL;

    @Value("${spring.datasource.username}")
    private String USER;

    @Value("${spring.datasource.password}")
    private String PASSWORD;

    /**
     * Persists a new customer.
     *
     * ( C(reate) from CRUD operations )
     *
     * @param customer The customer to persist.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void save(Customer customer) throws ClassNotFoundException, SQLException {

        log.info("---------------------------------------------------------------------------------------------------");
        log.info("CustomerDAO::save()");

        Class.forName(JDBC_DRIVER);

        Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        String query = "INSERT INTO customer VALUES("
                + "null"
                + ","
                + "'"
                + customer.getFirstName()
                + "'"
                + ","
                + "'"
                + customer.getLastName()
                + "'"
                + ","
                + "'"
                + customer.getEmail()
                + "'"
                + ")";

        log.info("Query: " + query);

        Statement statement = connection.createStatement();
        statement.executeUpdate(query);

        connection.close();
    }

    /**
     * Returns (reads) an existing customer by customer's id.
     *
     * ( R(ead) from CRUD operations )
     *
     * @param customerId The customer's id.
     * @return The customer found in the database.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Customer read(Long customerId) throws ClassNotFoundException, SQLException {

        log.info("---------------------------------------------------------------------------------------------------");
        log.info("CustomerDAO::read()");

        Class.forName(JDBC_DRIVER);

        Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        String query = "SELECT * FROM customer WHERE customer.id = " + customerId;

        log.info("Query: " + query);

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(query);

        Customer customer = null;

        while(resultSet.next()) {

            Long id             = resultSet.getLong("id");
            String firstName    = resultSet.getString("firstname");
            String lastName     = resultSet.getString("lastname");
            String email        = resultSet.getString("email");

            customer = new Customer(id, firstName, lastName, email);
        }

        connection.close();

        return customer;
    }

    /**
     * Updates an existing customer.
     *
     * ( U(pdate) from CRUD operations )
     *
     * @param customer The customer to be updated.
     * @return The updated customer entity.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Customer update(Customer customer) throws ClassNotFoundException, SQLException {

        log.info("---------------------------------------------------------------------------------------------------");
        log.info("CustomerDAO::update()");

        Class.forName(JDBC_DRIVER);

        Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        String query = "UPDATE customer SET "
                + "firstname="
                + "'"
                + customer.getFirstName()
                + "'"
                + ","
                + "lastname="
                + "'"
                + customer.getLastName()
                + "'"
                + ","
                + "email="
                + "'"
                + customer.getEmail()
                + "'"
                + " WHERE id="
                + customer.getId()
                + ";";

        log.info("Query: " + query);

        Statement statement = connection.createStatement();

        statement.executeQuery(query);

        // TODO: Read in the updated customer and return it.
        Customer updatedCustomer = null;

        connection.close();

        return updatedCustomer;
    }

    /**
     * Deletes an existing customer in the database.
     *
     * ( D(elete) from CRUD operations )
     *
     * @param customer The customer to be deleted.
     */
    public void delete(Customer customer) throws ClassNotFoundException, SQLException {

        log.info("---------------------------------------------------------------------------------------------------");
        log.info("CustomerDAO::delete()");

        Class.forName(JDBC_DRIVER);

        Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        String query = "DELETE FROM customer WHERE id=" + customer.getId() + ";";

        Statement statement = connection.createStatement();

        statement.executeQuery(query);

        connection.close();
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Additional methods other than CRUD

    /**
     * Returns a list of all customers.
     *
     * @return The list of all customers.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public List<Customer> findAll() throws ClassNotFoundException, SQLException {

        log.info("---------------------------------------------------------------------------------------------------");
        log.info("CustomerDAO::findAll()");

        Class.forName(JDBC_DRIVER);

        List<Customer> customers = new ArrayList<>();

        Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        String query = "SELECT * FROM customer";

        log.info("Query: " + query);

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(query);

        while(resultSet.next()) {

            Long id             = resultSet.getLong("id");
            String firstName    = resultSet.getString("firstname");
            String lastName     = resultSet.getString("lastname");
            String email        = resultSet.getString("email");

            Customer customer = new Customer(id, firstName, lastName, email);
            customers.add(customer);
        }

        connection.close();

        return customers;
    }

    public List<Customer> findByFirstNameStartingWith(String firstname) throws ClassNotFoundException, SQLException {

        log.info("---------------------------------------------------------------------------------------------------");
        log.info("CustomerDAO::findByFirstNameStartingWith()");

        Class.forName(JDBC_DRIVER);

        Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        String query = "SELECT * FROM customer WHERE (LOWER(firstname) LIKE "
                + "'%"
                + firstname
                + "%')";

        log.info("Query: " + query);

        List<Customer> customers = new ArrayList<>();

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(query);

        while(resultSet.next()) {

            Long id             = resultSet.getLong("id");
            String firstName    = resultSet.getString("firstname");
            String lastName     = resultSet.getString("lastname");
            String email        = resultSet.getString("email");

            Customer customer = new Customer(id, firstName, lastName, email);
            customers.add(customer);
        }

        connection.close();

        return customers;
    }
}
