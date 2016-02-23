/*
 * Copyright 2014-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ru.repp.den.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.repp.den.entity.Customer;
import ru.repp.den.repo.CustomerRepository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
	private CustomerRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer user = userRepository.findByLogin(username);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("User %s does not exist!", username));
		}

//		return new CustomerUserDetails(user);
//        UserDetails userDetails = new User(user.getLogin(),user.getPwdHash(),roles);
        return new CustomerUserDetails(user);
	}

	private final static class CustomerUserDetails extends Customer implements UserDetails {

		private static final long serialVersionUID = 1L;

		private CustomerUserDetails(Customer user) {
            super();
            this.setName(user.getName());
            this.setLogin(user.getLogin());
            this.setPwdHash(user.getPwdHash());
		}

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            Set<GrantedAuthority> roles = new HashSet<>();
            roles.add(new SimpleGrantedAuthority("ROLE_USER"));
            return roles;
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
            return true; // todo replace with staus
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }

	}

}
