package ru.repp.den.repo;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import ru.repp.den.entity.Customer;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findAll();

    Customer findById(Long id);


}
