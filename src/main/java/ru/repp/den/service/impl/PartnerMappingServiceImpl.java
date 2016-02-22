package ru.repp.den.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.repp.den.converter.PartnerMappingConverter;
import ru.repp.den.dto.PartnerMappingDTO;
import ru.repp.den.entity.PartnerMapping;
import ru.repp.den.repo.CustomerRepository;
import ru.repp.den.repo.PartnerMappingRepository;
import ru.repp.den.service.PartnerMappingService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartnerMappingServiceImpl implements PartnerMappingService{

    @Autowired
    PartnerMappingRepository pmr;

    @Override
    public List<PartnerMappingDTO> getAll() {
        List<PartnerMapping> all = pmr.findAll();
        return all.stream().map(PartnerMappingConverter::toDTO).collect(Collectors.toList());
    }
}
