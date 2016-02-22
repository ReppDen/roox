package ru.repp.den.service.impl;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import ru.repp.den.converter.CustomerConverter;
import ru.repp.den.converter.PartnerMappingConverter;
import ru.repp.den.dto.CustomerDTO;
import ru.repp.den.dto.PartnerMappingDTO;
import ru.repp.den.entity.Customer;
import ru.repp.den.repo.CustomerRepository;
import ru.repp.den.service.CustomerService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService{

    public static final String ME_LITERAL = "@me";

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<CustomerDTO> getAll() {
        List<Customer> all = customerRepository.findAll();
        return all.stream().map(CustomerConverter::toDTO).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getById(String id) {
        return CustomerConverter.toDTO(getCustomerById(id));
    }

    @Override
    public CustomerDTO getByLogin(String login) {
        return CustomerConverter.toDTO(customerRepository.findByLogin(login));
    }

    @Override
    public List<PartnerMappingDTO> getMappingsByCustomerId(String id) {
        Customer c = getCustomerById(id);
        if (c == null) {
            throw new RestClientException("Find a Partner Mapping. The Customer is not found for ID " + id);
        }
        return c.getPartnerMapping().stream().map(PartnerMappingConverter::toDTO).collect(Collectors.toList());
    }

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
}
