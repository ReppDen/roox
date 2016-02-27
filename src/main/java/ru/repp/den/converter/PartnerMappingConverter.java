package ru.repp.den.converter;

import ru.repp.den.dto.PartnerMappingDTO;
import ru.repp.den.entity.PartnerMapping;

/**
 * class to convert Entity objects to DTO
 */
public class PartnerMappingConverter {

    /**
     * Transforms {@link PartnerMapping} entity to DTO for usage in REST services
     * @param partnerMapping entity to transform
     * @return DTO object
     */
    public static PartnerMappingDTO toDTO(PartnerMapping partnerMapping) {
        if (partnerMapping == null) {
            return null;
        }
        PartnerMappingDTO.Builder builder = PartnerMappingDTO.newBuilder();
        builder.setId(partnerMapping.getId());
        builder.setFullName(partnerMapping.getFullName());
        builder.setPartnerId(partnerMapping.getPartnerId());
        builder.setAccount(partnerMapping.getAccount());
        builder.setCustomer(CustomerConverter.toDTO(partnerMapping.getCustomer()));
        return builder.build();
    }

    /**
     * creates new {@link PartnerMapping} entity without fields: customer, avatar. These fields should be populated separately
     * @param dto DTO object, source of information
     * @return new instance of filled PartnerMapping class
     */
    public static PartnerMapping toEntity (PartnerMappingDTO dto) {
        if (dto == null) {
            return null;
        }
        PartnerMapping pm = new PartnerMapping();
        pm.setPartnerId(dto.getPartnerId());
        pm.setFullName(dto.getFullName());
        pm.setAccount(dto.getAccount());
        pm.setId(dto.getId());
        return pm;
    }

    /**
     * appends specified {@link PartnerMapping} entity with values from DTO. Fields are ignored: customer, avatar. These fields should be populated separately
     * @param source DTO object, source of information
     * @param destination an existing PartnerMapping instance
     * @return new instance of filled PartnerMapping class
     */
    public static PartnerMapping appendToEntity (PartnerMappingDTO source, PartnerMapping destination) {
        if (source == null || destination == null) {
            return null;
        }
        if (!isTheSame(source.getAccount(), destination.getAccount())) {
            destination.setAccount(source.getAccount());
        }
        if (!isTheSame(source.getPartnerId(), destination.getPartnerId())) {
            destination.setPartnerId(source.getPartnerId());
        }
        if (!isTheSame(source.getFullName(), destination.getFullName())) {
            destination.setFullName(source.getFullName());
        }
        return destination;
    }

    private static boolean isTheSame(Object o, Object n) {
        return (o != null && o.equals(n)) || (n != null && n.equals(o));
    }
}
