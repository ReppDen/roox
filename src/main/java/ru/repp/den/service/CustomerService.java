package ru.repp.den.service;

import ru.repp.den.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAll();

    CustomerDTO getById(String id);
}
