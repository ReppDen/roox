package ru.repp.den.service;

import ru.repp.den.dto.CustomerDTO;
import ru.repp.den.dto.PartnerMappingDTO;
import ru.repp.den.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAll();

    CustomerDTO getById(String id);

    CustomerDTO getByLogin(String login);

    List<PartnerMappingDTO> getMappingsByCustomerId(String id);
}
