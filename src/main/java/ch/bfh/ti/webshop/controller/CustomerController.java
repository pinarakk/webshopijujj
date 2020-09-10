package ch.bfh.ti.webshop.controller;

import ch.bfh.ti.webshop.helper.UIHelper;
import ch.bfh.ti.webshop.model.Customer;
import ch.bfh.ti.webshop.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

/**
 * CustomerController.
 *
 * @author Thomas Ingold
 * @version 1.0
 */

@Controller
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    private final UIHelper uiHelper;

    /**
     * Constructor.
     *
     * @param customerService The customer service to be injected.
     * @param uiHelper The UIHelper to be injected.
     */
    public CustomerController(CustomerService customerService, UIHelper uiHelper) {

        log.info("---------------------------------------------------------------------------------------------------");
        log.info("CustomerController::CustomerController()");

        this.customerService = customerService;
        this.uiHelper = uiHelper;
    }

    /**
     * Returns the view with customers. (GET)
     *
     * @param model The model to be injected.
     * @return customers view template.
     */
    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public String getCustomers(Model model) throws SQLException, ClassNotFoundException {

        log.info("---------------------------------------------------------------------------------------------------");
        log.info("CustomerController::getCustomers()");

        model.addAttribute("customers", customerService.getCustomers());

        // Returns the customers.html template to the requester.
        return "customers";
    }

    @RequestMapping(value = "/newCustomer", method = RequestMethod.GET)
    public String newCustomer(Model model) {

        log.info("---------------------------------------------------------------------------------------------------");
        log.info("CustomerController::newCustomer()");

        model.addAttribute("customer", new Customer());

        return "newCustomer";
    }

    /**
     * Posts a new customer and returns to customer confirmation page. (POST)
     *
     * @param customer The new customer to be persisted.
     * @return New customer's confirmation template.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @RequestMapping(value = "/createCustomer", method = RequestMethod.POST)
    public String createCustomer(Customer customer) throws SQLException, ClassNotFoundException {

        log.info("---------------------------------------------------------------------------------------------------");
        log.info("CustomerController::createCustomer()");

        customerService.createCustomer(customer);

        return "newCustomerConfirmation";
    }

    /**
     * Gets a customer to be updated.
     *
     * @param customerId The customer's id to be updated.
     * @param model The model
     * @return Update customer's template.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @RequestMapping(value = "/editCustomer/{customerId}", method = RequestMethod.GET)
    public String editCustomer(@PathVariable Long customerId, Model model) throws SQLException, ClassNotFoundException {

        log.info("---------------------------------------------------------------------------------------------------");
        log.info("CustomerController::editCustomer()");

        Customer customer = customerService.getCustomerById(customerId);

        model.addAttribute("customer", customer);

        return "updateCustomer";
    }

    /**
     * Posts a customer to be updated.
     *
     * @param customer The customer to be updated.
     * @return The customer's update confirmation template.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @RequestMapping(value = "/updateCustomer", method = RequestMethod.POST)
    public String updateCustomer(Customer customer) throws SQLException, ClassNotFoundException {

        log.info("---------------------------------------------------------------------------------------------------");
        log.info("CustomerController::updateCustomer()");

        customerService.updateCustomer(customer);

        return "updateCustomerConfirmation";
    }

    /**
     * Requests a customer to be deleted.
     *
     * @param customerId The customer's id.
     * @return The customer's deletion confirmation template.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @RequestMapping(value = "/deleteCustomer/{customerId}", method = RequestMethod.GET)
    public String deleteCustomer(@PathVariable Long customerId) throws SQLException, ClassNotFoundException {

        log.info("---------------------------------------------------------------------------------------------------");
        log.info("CustomerController::deleteCustomer()");

        Customer customer = customerService.getCustomerById(customerId);

        customerService.deleteCustomer(customer);

        return "deleteCustomerConfirmation";
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Exception handlers

    /**
     * Handles all SQL related exceptions.
     *
     * @param model The model..
     * @return The error page template.
     */
    @ExceptionHandler({SQLException.class, ClassNotFoundException.class})
    public String catchSQLException(Model model) {

        log.info("---------------------------------------------------------------------------------------------------");
        log.info("CustomerController::catchSQLException()");

        model.addAttribute("errorMessage", "There's a problem with your SQL connection!");
        model.addAttribute("uiHelper", uiHelper);

        return "error";
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Model attributes for every method / request (not working in exception handlers !)

    /**
     * Automatically adds the UIHelper bean to the model.
     *
     * @return The UIHelper bean.
     */
    @ModelAttribute("uiHelper")
    public UIHelper getUIHelper() {

        return uiHelper;
    }
}
