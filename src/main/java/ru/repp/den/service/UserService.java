package ru.repp.den.service;

import ru.repp.den.entity.Customer;

public interface UserService {

    /**
     * Method retive the Customer object based on ID, if id is "@me", the logged customer info will be returned
     */
    Customer getCustomerById(String id);

    /**
     * a util method checks if specified customer is able to proceed or not
     */
    boolean isAllowedToProcess(String customerId);
}