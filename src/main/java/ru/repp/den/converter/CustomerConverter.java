package ru.repp.den.converter;

import ru.repp.den.dto.CustomerDTO;
import ru.repp.den.entity.Customer;

/**
 * class to convert Entity objects to DTO
 */
public class CustomerConverter {

    public static CustomerDTO toDTO(Customer customer) {
        CustomerDTO.Builder builder = CustomerDTO.newBuilder();
        builder.setId(customer.getId());
        builder.setName(customer.getName());
        builder.setBalance(customer.getBalance());
        builder.setActive(customer.getActive());
        builder.setPwdHash(customer.getPwdHash());
        builder.setLogin(customer.getLogin());
        return builder.build();
    }
}
