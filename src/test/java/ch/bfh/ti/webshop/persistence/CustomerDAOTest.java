package ch.bfh.ti.webshop.persistence;

import ch.bfh.ti.webshop.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * CustomerRepositoryTest.
 *
 * Don't do this in a real world scenario!
 * We're abusing our 'productive' database!
 *
 * @author Thomas Ingold
 * @version 1.0
 */

@SpringBootTest
public class CustomerDAOTest {

    @Autowired
    private CustomerDAO customerDAO;

    @Test
    public void testCreate() throws SQLException, ClassNotFoundException {

        List<Customer> customersBefore = customerDAO.findAll();

        Customer customer = new Customer("Donald", "Trump", "donald@whitehouse.gov");
        customerDAO.save(customer);

        List<Customer> customersAfter = customerDAO.findAll();

        assertThat(customersBefore.size()).isLessThan(customersAfter.size());

        assertThat(customersBefore.size() + 1).isEqualTo(customersAfter.size());
    }
}
