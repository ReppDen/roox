package ru.repp.den.converter;

import ru.repp.den.dto.CustomerDTO;
import ru.repp.den.entity.Customer;

import java.util.stream.Collectors;

/**
 * class to convert Entity objects to DTO
 */
public class CustomerConverter {

    public static CustomerDTO toDTO(Customer customer) {
        return toDTO(customer, false);
    }

    public static CustomerDTO toDTO(Customer customer, boolean deep) {
        if (customer == null) {
            return new CustomerDTO();
        }
        CustomerDTO.Builder builder = CustomerDTO.newBuilder();
        builder.setId(customer.getId());
        builder.setName(customer.getName());
        builder.setBalance(customer.getBalance());
        builder.setActive(customer.getActive());
        builder.setPwdHash(customer.getPwdHash());
        builder.setLogin(customer.getLogin());
        if (deep) {
            builder.setPartnerMappingDTOs(customer.getPartnerMapping().stream().map(PartnerMappingConverter::toDTO).collect(Collectors.toList()));
        }
        return builder.build();
    }
}
