package ru.repp.den.service;

import ru.repp.den.entity.Customer;

public interface UserService {
    Customer getCustomerById(String id);
}