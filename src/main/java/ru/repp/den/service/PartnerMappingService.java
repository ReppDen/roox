package ru.repp.den.service;

import ru.repp.den.dto.CustomerDTO;
import ru.repp.den.dto.PartnerMappingDTO;
import ru.repp.den.entity.PartnerMapping;

import java.util.List;

public interface PartnerMappingService {
    List<PartnerMappingDTO> getAll();

    PartnerMappingDTO addMapping(String id, PartnerMappingDTO mapping);

    PartnerMappingDTO updateMapping(String id, Long mapId, PartnerMappingDTO mapping);

    void deleteMapping(String id, Long mapId);

    PartnerMappingDTO getMappingById(String customerId, Long mapId);
}
