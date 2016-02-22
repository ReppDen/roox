package ru.repp.den.dto;

import lombok.Getter;
import lombok.Setter;
import ru.repp.den.entity.Customer;

import javax.persistence.*;

@Getter
@Setter
public class PartnerMappingDTO {

    private Long id;

    private CustomerDTO customer;

    private Long partnerId;

    private String account;

    private String fullName;

    // TODO save objects
//    @Column
//    private Object avatar;

    public static Builder newBuilder() {
        return new PartnerMappingDTO().new Builder();
    }

    public class Builder {
        private Long id;

        private CustomerDTO customer;

        private Long partnerId;

        private String account;

        private String fullName;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setCustomer(CustomerDTO customer) {
            this.customer = customer;
            return this;
        }

        public Builder setPartnerId(Long partnerId) {
            this.partnerId = partnerId;
            return this;
        }

        public Builder setAccount(String account) {
            this.account = account;
            return this;
        }

        public Builder setFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public PartnerMappingDTO build() {
            PartnerMappingDTO pm = new PartnerMappingDTO();
            pm.setId(id);
            pm.setAccount(account);
            pm.setCustomer(customer);
            pm.setFullName(fullName);
            pm.setPartnerId(partnerId);
            return pm;
        }
    }
}
