package ru.repp.den.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.repp.den.converter.CustomerConverter;
import ru.repp.den.dto.CustomerDTO;
import ru.repp.den.entity.Customer;
import ru.repp.den.repo.CustomerRepository;
import ru.repp.den.service.CustomerService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<CustomerDTO> getAll() {
        Customer c = new Customer();
        c.setName("Vasya");
        c.setActive(true);
        c.setPwdHash("QWQEQWEQWE");
        c.setLogin("Vasya_SuperMan");
        c.setBalance(100.5f);

        Customer c2 = new Customer();
        c2.setName("Olya");
        c2.setActive(false);
        c2.setPwdHash("ASDASDASD");
        c2.setLogin("Olya_SuperMan");
        c2.setBalance(200.5f);
        customerRepository.save(c);
        customerRepository.save(c2);

        List<Customer> all = customerRepository.findAll();
        return all.stream().map(CustomerConverter::toDTO).collect(Collectors.toList());
    }
}
