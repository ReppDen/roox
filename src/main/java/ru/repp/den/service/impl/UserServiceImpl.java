package ru.repp.den.service.impl;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import ru.repp.den.entity.Customer;
import ru.repp.den.repo.CustomerRepository;
import ru.repp.den.service.UserService;
import sun.rmi.server.UnicastServerRef;

@Service
public class UserServiceImpl implements UserService{

    public static final String ME_LITERAL = "@me";

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer getCustomerById(String id) {
        if (id == null) {
            throw new RestClientException("Customer ID cannot be null");
        }
        if (ME_LITERAL.equalsIgnoreCase(id)) {
            // TODO get current user
            return null;
        } else {
            if (!NumberUtils.isNumber(id)) {
                throw new RestClientException("Customer ID is not a number of \"@me\" string");
            }
            return customerRepository.findById(Long.parseLong(id));
        }
    }

    @Override
    public Customer getUser(String login) {
//        Customer user = customerRepository.findByLogin(login);
        Customer user = new Customer();
        user.setLogin(login);
        user.setPwdHash("7110eda4d09e062aa5e4a390b0a572ac0d2c0220");

        return user;
    }
}
