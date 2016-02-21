package ru.repp.den.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("balance")
    private Float balance;

    @JsonProperty("login")
    private String login;

    @JsonIgnore
    private String pwdHash;

    @JsonProperty("active")
    private Boolean active;

    public static Builder newBuilder() {
        return new CustomerDTO().new Builder();
    }

    public class Builder {
        private Long id;

        private String name;

        private Float balance;

        private String login;

        private String pwdHash;

        private Boolean active;

        private Builder() {}

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

        public CustomerDTO build() {
            CustomerDTO c = new CustomerDTO();
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
