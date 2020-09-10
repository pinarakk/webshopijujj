package ch.bfh.ti.webshop.service;

import ch.bfh.ti.webshop.persistence.CustomerDAO;
import ch.bfh.ti.webshop.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * CustomerService.
 *
 * @author Thomas Ingold
 * @version 1.0
 */

@Service
@Slf4j
public class CustomerService {

    private final CustomerDAO customerDAO;

    /**
     * Constructor.
     *
     * @param customerDAO The customer repository to be injected.
     */
    public CustomerService(CustomerDAO customerDAO) {

        log.info("---------------------------------------------------------------------------------------------------");
        log.info("CustomerService::CustomerService()");

        this.customerDAO = customerDAO;
    }

    /**
     * Persists a new customer.
     *
     * @param customer The customer to be persisted.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void createCustomer(Customer customer) throws SQLException, ClassNotFoundException {

        log.info("---------------------------------------------------------------------------------------------------");
        log.info("CustomerService::createCustomer()");

        customerDAO.save(customer);
    }

    /**
     * Returns a customer by id.
     *
     * @param customerId The customer's id.
     * @return The customer found by its id.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Customer getCustomerById(Long customerId) throws SQLException, ClassNotFoundException {

        log.info("---------------------------------------------------------------------------------------------------");
        log.info("CustomerService::getCustomerById()");

        return customerDAO.read(customerId);
    }

    /**
     * Updates an existing customer.
     *
     * @param customer The customer to be updated.
     * @return The updated customer entity.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Customer updateCustomer(Customer customer) throws SQLException, ClassNotFoundException {

        log.info("---------------------------------------------------------------------------------------------------");
        log.info("CustomerService::updateCustomer()");

        return customerDAO.update(customer);
    }


    public void deleteCustomer(Customer customer) throws SQLException, ClassNotFoundException {

        log.info("---------------------------------------------------------------------------------------------------");
        log.info("CustomerService::deleteCustomer()");

        customerDAO.delete(customer);
    }

    /**
     * Returns all customers from the database.
     * NEVER EVER return all records in a productive environment !
     *
     * @return the customers found in the database.
     */
    public List<Customer> getCustomers() throws SQLException, ClassNotFoundException {

        log.info("---------------------------------------------------------------------------------------------------");
        log.info("CustomerService::getCustomers()");

        return customerDAO.findAll();
    }
}
