package ru.repp.den.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import ru.repp.den.converter.PartnerMappingConverter;
import ru.repp.den.dto.PartnerMappingDTO;
import ru.repp.den.entity.Customer;
import ru.repp.den.entity.PartnerMapping;
import ru.repp.den.repo.PartnerMappingRepository;
import ru.repp.den.service.CustomerService;
import ru.repp.den.service.PartnerMappingService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartnerMappingServiceImpl implements PartnerMappingService{

    @Autowired
    PartnerMappingRepository pmr;

    @Autowired
    CustomerService cs;

    @Override
    public List<PartnerMappingDTO> getAll() {
        List<PartnerMapping> all = pmr.findAll();
        return all.stream().map(PartnerMappingConverter::toDTO).collect(Collectors.toList());
    }

    @Override
    public PartnerMappingDTO addMapping(String id, PartnerMappingDTO mapping) {
        Customer c = cs.getCustomerById(id);
        if (c == null) {
            throw new RestClientException("Cannot create a Partner Mapping. The customer is not found for ID " + id);
        }
        PartnerMapping pm = PartnerMappingConverter.toEntity(mapping);
        pm.setCustomer(c);
        PartnerMapping saveRes = pmr.save(pm);
        return PartnerMappingConverter.toDTO(saveRes);

    }

    @Override
    public PartnerMappingDTO updateMapping(String id, Long mapId, PartnerMappingDTO mapping) {
        Customer c = cs.getCustomerById(id);
        if (c == null) {
            throw new RestClientException("Cannot create a Partner Mapping. The customer is not found for ID " + id);
        }
        if (mapId == null) {
            throw new RestClientException("Cannot update a Partner Mapping. Mapping ID is null");
        }
        mapping.setId(mapId);
        PartnerMapping pm = PartnerMappingConverter.toEntity(mapping);
        pm.setCustomer(c);
        PartnerMapping saveRes = pmr.save(pm);
        return PartnerMappingConverter.toDTO(saveRes);
    }

    @Override
    public void deleteMapping(String id, Long mapId) {
        Customer c = cs.getCustomerById(id);
        if (c == null) {
            throw new RestClientException("Cannot create a Partner Mapping. The customer is not found for ID " + id);
        }
        if (mapId == null) {
            throw new RestClientException("Cannot update a Partner Mapping. Mapping ID is null");
        }
        pmr.delete(mapId);
    }

    @Override
    public PartnerMappingDTO getMappingById(String customerId, Long mapId) {
        if (mapId == null) {
            throw new RestClientException("Cannot get Partner mapping. Mapping ID is null");
        }
        Customer c = cs.getCustomerById(customerId);
        if (c == null) {
            throw new RestClientException("Cannot find mapping by ID. Customer not found for ID " + customerId);
        }
        PartnerMapping pm = pmr.findOneByIdAndCustomerId(mapId, c.getId());
        return PartnerMappingConverter.toDTO(pm);
    }
}
