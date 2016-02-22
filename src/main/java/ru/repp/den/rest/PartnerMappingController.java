package ru.repp.den.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.repp.den.dto.PartnerMappingDTO;
import ru.repp.den.service.PartnerMappingService;

import java.util.List;

@RestController
public class PartnerMappingController {

    @Autowired
    PartnerMappingService pms;

    @RequestMapping("/mapping")
    public List<PartnerMappingDTO> getAll() {
        return pms.getAll();
    }
}
