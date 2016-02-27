package ru.repp.den.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter
@Setter
@SequenceGenerator(name="seq", initialValue=1)
public class PartnerMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @Column
    private Long partnerId;

    @Column
    private String account;

    @Column
    private String fullName;

    @Column
    private String avatar;

    public static Builder newBuilder() {
        return new PartnerMapping().new Builder();
    }

    public class Builder {
        private Long id;

        private Customer customer;

        private Long partnerId;

        private String account;

        private String fullName;

        private String avatar;


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

        public Builder setAvatar(String avatar) {
            this.avatar = avatar;
            return this;
        }

        public PartnerMapping build() {
            PartnerMapping pm = new PartnerMapping();
            pm.setId(id);
            pm.setAccount(account);
            pm.setCustomer(customer);
            pm.setFullName(fullName);
            pm.setPartnerId(partnerId);
            pm.setAvatar(avatar);
            return pm;
        }
    }
}
