package ru.repp.den.repo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.repp.den.DbInitializer;
import ru.repp.den.RooxApplication;
import ru.repp.den.entity.Customer;

import java.util.List;

import static org.junit.Assert.*;

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
}