package ru.repp.den.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.repp.den.entity.Customer;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomerUserDetails extends Customer implements UserDetails {

        // we have only one role, return it each time
        private static final Set<SimpleGrantedAuthority> authorities = Stream.of(new SimpleGrantedAuthority("ROLE_USER")).collect(Collectors.toSet());

        private static final long serialVersionUID = 1L;


        public CustomerUserDetails(Customer user) {
            super();
            this.setId(user.getId());
            this.setName(user.getName());
            this.setLogin(user.getLogin());
            this.setPwdHash(user.getPwdHash());
            this.setActive(user.getActive());
            this.setBalance(user.getBalance());
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorities;
        }

        @Override
        public String getPassword() {
            return this.getPwdHash();
        }

        @Override
        public String getUsername() {
            return this.getLogin();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return this.getActive();
        }

}
