package ch.bfh.ti.webshop.restController;

import ch.bfh.ti.webshop.model.Customer;
import ch.bfh.ti.webshop.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

/**
 * CustomerRESTController.
 *
 * @author Thomas Ingold
 * @version 1.0
 */

@RestController
@Slf4j
public class CustomerRESTController {

    private final CustomerService customerService;

    /**
     * Constructor.
     *
     * @param customerService The customer service to be injected.
     */
    public CustomerRESTController(CustomerService customerService) {

        log.info("---------------------------------------------------------------------------------------------------");
        log.info("CustomerRESTController::CustomerRESTController()");

        this.customerService = customerService;
    }

    @RequestMapping(value = "/getCustomers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Customer> getCustomers(
            @RequestParam String firstName
    ) throws ClassNotFoundException, SQLException {

        log.info("---------------------------------------------------------------------------------------------------");
        log.info("CustomerRESTController::getCustomers()");

        return customerService.findByFirstNameStartingWith(firstName);
    }
}
