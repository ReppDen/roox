package ru.repp.den.service.impl;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.repp.den.entity.Customer;
import ru.repp.den.exception.BadRequestException;
import ru.repp.den.repo.CustomerRepository;
import ru.repp.den.security.CustomerUserDetails;
import ru.repp.den.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    public static final String ME_LITERAL = "@me";

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer getCustomerById(String id) {
        if (id == null) {
            throw new BadRequestException("Customer ID cannot be null");
        }
        if (ME_LITERAL.equalsIgnoreCase(id)) {
            // TODO get current user
            CustomerUserDetails principal = (CustomerUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return customerRepository.findById(principal.getId());
        } else {
            if (!NumberUtils.isNumber(id)) {
                throw new BadRequestException("Customer ID is not a number or \"@me\" string");
            }
            return customerRepository.findById(Long.parseLong(id));
        }
    }

}
