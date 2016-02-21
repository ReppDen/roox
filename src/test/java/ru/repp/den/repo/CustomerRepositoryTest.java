package ru.repp.den.repo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
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
        cr.save(new Builder().setId(1L).setName("Vasya").build()); // TODO use proper builder
        cr.save(new Builder().setId(2L).setName("Petya").build());

        List<Customer> all = cr.findAll();
        assertNotNull(all);
        assertEquals(all.size(), 2);
    }

    class Builder {
        private Long id;
        private String name;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Customer build() {
            Customer c = new Customer();
            c.setId(id);
            c.setName(name);
            return c;
        }
    }
}