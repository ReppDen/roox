package ru.repp.den.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private Float balance;

    @Column
    private String login;

    @Column
    private String pwdHash;

    @Column
    private Boolean active;

    public static Builder newBuilder() {
        return new Customer().new Builder();
    }

    public class Builder {
        private Long id;

        private String name;

        private Float balance;

        private String login;

        private String pwdHash;

        private Boolean active;

        private Builder() {
        }

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setBalance(Float balance) {
            this.balance = balance;
            return this;
        }

        public Builder setLogin(String login) {
            this.login = login;
            return this;
        }

        public Builder setPwdHash(String pwdHash) {
            this.pwdHash = pwdHash;
            return this;
        }

        public Builder setActive(Boolean active) {
            this.active = active;
            return this;
        }

        public Customer build() {
            Customer c = new Customer();
            c.setId(id);
            c.setName(name);
            c.setBalance(balance);
            c.setLogin(login);
            c.setPwdHash(pwdHash);
            c.setActive(active);
            return c;
        }
    }
}
