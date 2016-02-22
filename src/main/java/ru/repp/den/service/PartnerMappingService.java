package ru.repp.den.service;

import ru.repp.den.dto.CustomerDTO;
import ru.repp.den.dto.PartnerMappingDTO;

import java.util.List;

public interface PartnerMappingService {
    List<PartnerMappingDTO> getAll();
}
