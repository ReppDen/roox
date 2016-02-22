package ru.repp.den.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.repp.den.dto.CustomerDTO;
import ru.repp.den.dto.PartnerMappingDTO;
import ru.repp.den.service.CustomerService;
import ru.repp.den.service.PartnerMappingService;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerService cs;

    @Autowired
    PartnerMappingService pms;

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public List<CustomerDTO> getAll() {
        return cs.getAll();
    }

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.GET)
    public CustomerDTO getById(@PathVariable String id) {
        return cs.getById(id);
    }


}
