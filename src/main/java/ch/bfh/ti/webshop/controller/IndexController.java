package ch.bfh.ti.webshop.controller;

import ch.bfh.ti.webshop.helper.UIHelper;
import ch.bfh.ti.webshop.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * IndexController.
 *
 * @author Thomas Ingold
 * @version 1.0
 */

@Controller
@Slf4j
public class IndexController {

    private final UIHelper uiHelper;

    /**
     * Constructor.
     *
     * @param uiHelper The UIHelper to be injected.
     */
    public IndexController(UIHelper uiHelper) {

        log.info("---------------------------------------------------------------------------------------------------");
        log.info("IndexController::IndexController()");

        this.uiHelper = uiHelper;
    }

    /**
     * Handles http GET requests to path '/' ( e.g. 'http://localhost:8080' or 'http://localhost:8080/' )
     *
     * @return the index.html template.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {

        log.info("---------------------------------------------------------------------------------------------------");
        log.info("IndexController::index()");

        // Returns the index.html template to the requester.
        return "index";
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
