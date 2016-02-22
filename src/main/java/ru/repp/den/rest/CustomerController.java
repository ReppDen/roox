package ru.repp.den.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.repp.den.dto.CustomerDTO;
import ru.repp.den.dto.PartnerMappingDTO;
import ru.repp.den.entity.PartnerMapping;
import ru.repp.den.service.CustomerService;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerService cs;

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public List<CustomerDTO> getAll() {
        return cs.getAll();
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    public CustomerDTO getById(@PathVariable String id) {
        return cs.getById(id);
    }

    @RequestMapping(value = "/customer/{id}/mappings", method = RequestMethod.GET)
    public List<PartnerMappingDTO> getMappingsByCustomerId(@PathVariable String id) {
        return cs.getMappingsByCustomerId(id);
    }
}
