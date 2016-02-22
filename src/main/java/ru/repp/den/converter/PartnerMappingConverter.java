package ru.repp.den.converter;

import ru.repp.den.dto.CustomerDTO;
import ru.repp.den.dto.PartnerMappingDTO;
import ru.repp.den.entity.Customer;
import ru.repp.den.entity.PartnerMapping;

/**
 * class to convert Entity objects to DTO
 */
public class PartnerMappingConverter {

    public static PartnerMappingDTO toDTO(PartnerMapping partnerMapping) {
        if (partnerMapping == null) {
            return new PartnerMappingDTO();
        }
        PartnerMappingDTO.Builder builder = PartnerMappingDTO.newBuilder();
        builder.setId(partnerMapping.getId());
        builder.setFullName(partnerMapping.getFullName());
        builder.setPartnerId(partnerMapping.getPartnerId());
        builder.setAccount(partnerMapping.getAccount());
        builder.setCustomer(CustomerConverter.toDTO(partnerMapping.getCustomer()));
        return builder.build();
    }
}