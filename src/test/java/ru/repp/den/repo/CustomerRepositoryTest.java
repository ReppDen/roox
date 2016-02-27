package ru.repp.den.repo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.repp.den.DbInitializer;
import ru.repp.den.RooxApplication;
import ru.repp.den.entity.Customer;
import ru.repp.den.entity.PartnerMapping;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RooxApplication.class)
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository cr;

    @Test
    public void testFindAll() throws Exception {
        List<Customer> all = cr.findAll();
        assertNotNull(all);
        assertEquals(all.size(), DbInitializer.CUSTOMERS_AMOUNT);
    }

    @Test
     public void testFindByName() {
        String name = "Customer full name 3";

        Customer byName = cr.findByName(name);
        assertNotNull(byName);
        assertEquals(byName.getName(), name);
    }

    @Test
    public void testFindMappings() {
        String name = "Customer full name 3";

        Customer byName = cr.findByName(name);
        assertNotNull(byName);
        assertEquals(byName.getName(), name);
        List<PartnerMapping> partnerMapping = byName.getPartnerMapping();
        assertNotNull(partnerMapping);

    }

    @Test
    public void testFindByLogin() {
        String login = "Login2";

        Customer c = cr.findByLogin(login);

        assertNotNull(c);
        assertEquals(c.getLogin(), login);
    }
}