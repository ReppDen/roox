package ru.repp.den.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.repp.den.dto.CustomerDTO;
import ru.repp.den.service.CustomerService;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerService cs;

    @RequestMapping("/customer")
    public List<CustomerDTO> getAll() {
        return cs.getAll();
    }
}
