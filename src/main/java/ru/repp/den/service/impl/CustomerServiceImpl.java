package ru.repp.den.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.repp.den.converter.CustomerConverter;
import ru.repp.den.converter.PartnerMappingConverter;
import ru.repp.den.dto.CustomerDTO;
import ru.repp.den.dto.PartnerMappingDTO;
import ru.repp.den.entity.Customer;
import ru.repp.den.entity.PartnerMapping;
import ru.repp.den.repo.CustomerRepository;
import ru.repp.den.service.CustomerService;

import java.util.ArrayList;
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
        List<PartnerMappingDTO> res = new ArrayList<>();
        if (c != null) {
            res = c.getPartnerMapping().stream().map(PartnerMappingConverter::toDTO).collect(Collectors.toList());
        }
        return  res;
    }

    private Customer getCustomerById(String id) {
        if (ME_LITERAL.equalsIgnoreCase(id)) {
            // TODO get current user
            return null;
        } else {
            return customerRepository.findById(Long.parseLong(id));
        }
    }
}
