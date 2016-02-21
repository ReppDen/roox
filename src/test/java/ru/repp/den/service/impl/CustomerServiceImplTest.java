package ru.repp.den.service.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.repp.den.RooxApplication;
import ru.repp.den.dto.CustomerDTO;
import ru.repp.den.entity.Customer;
import ru.repp.den.repo.CustomerRepository;
import ru.repp.den.service.CustomerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RooxApplication.class)
public class CustomerServiceImplTest {

    @InjectMocks
    CustomerServiceImpl cs;

    @Mock
    CustomerRepository cr;

    @Before
    public void before(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAll() throws Exception {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Builder().setId(1L).setName("Vasya1").build());
        customers.add(new Builder().setId(2L).setName("Vasya2").build());
        customers.add(new Builder().setId(3L).setName("Vasya3").build());
        Mockito.when(cr.findAll()).thenReturn(customers);

        List<CustomerDTO> all = cs.getAll();

        assertNotNull(all);
        assertEquals(all.size(), customers.size());
        all.stream().forEach((dto) ->{
            Optional<Customer> customerOpt = customers.stream().filter((customer -> customer.getId().equals(dto.getId()))).findFirst();
            assertNotNull(customerOpt.get());
        });
    }

    // TODO fill all data
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