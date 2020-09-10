package ch.bfh.ti.webshop.applicationStart;

import ch.bfh.ti.webshop.model.Customer;
import ch.bfh.ti.webshop.persistence.CustomerDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * DBSeeder.
 *
 * @author Thomas Ingold
 * @version 1.0
 */

@Component
@Slf4j
public class DBSeeder implements CommandLineRunner {

    private final CustomerDAO customerDAO;

    /**
     * Constructor.
     *
     * @param customerDAO The customer DAO to be injected.
     */
    public DBSeeder(CustomerDAO customerDAO) {

        log.info("---------------------------------------------------------------------------------------------------");
        log.info("DBSeeder::DBSeeder()");

        this.customerDAO = customerDAO;
    }

    /**
     * Method executed after server startup.
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {

        log.info("---------------------------------------------------------------------------------------------------");
        log.info("DBSeeder::run()");

        customerDAO.save(new Customer("Thomas", "Ingold", "thomas.ingold@bfh.ch"));
        customerDAO.save(new Customer("Theo", "Huber", "theo.huber@gmail.com"));
        customerDAO.save(new Customer("Tamara", "Lüthi", "tamara.luethi@hotmail.com"));
        customerDAO.save(new Customer("Sabine", "Lanz", "sabine.lanz@bluewin.ch"));
        customerDAO.save(new Customer("Sacha", "Odermatt", "sacha.odermatt@novartis.com"));
        customerDAO.save(new Customer("Sabina", "Merz", "sabina.merz@roche.com"));
        customerDAO.save(new Customer("Sandra", "Zumstein", "sandra.zumstein@nestle.com"));
        customerDAO.save(new Customer("Therese", "Bircher", "therese.bircher@migros.ch"));
        customerDAO.save(new Customer("Aykan", "Simsek", "aykan.symsek@students.bfh.ch"));
        customerDAO.save(new Customer("Hans", "Herren", "hans.herren@students.bfh.ch"));
        customerDAO.save(new Customer("Pinar", "Akan", "pinar.akan@students.bfh.ch"));
        customerDAO.save(new Customer("Benjamin", "Meier", "benjamin.meier@students.bfh.ch"));
        customerDAO.save(new Customer("Fabian", "Bammatter", "fabian.bammatter@students.bfh.ch"));
    }
}
