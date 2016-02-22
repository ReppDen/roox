package ru.repp.den.entity;

import com.fasterxml.jackson.databind.deser.BuilderBasedDeserializer;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class PartnerMapping {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @Column
    private Long partnerId;

    @Column
    private String account;

    @Column
    private String fullName;

    // TODO save objects
//    @Column
//    private Object avatar;

    public static Builder newBuilder() {
        return new PartnerMapping().new Builder();
    }

    public class Builder {
        private Long id;

        private Customer customer;

        private Long partnerId;

        private String account;

        private String fullName;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setCustomer(Customer customer) {
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

        public PartnerMapping build() {
            PartnerMapping pm = new PartnerMapping();
            pm.setId(id);
            pm.setAccount(account);
            pm.setCustomer(customer);
            pm.setFullName(fullName);
            pm.setPartnerId(partnerId);
            return pm;
        }
    }
}
