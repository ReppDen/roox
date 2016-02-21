package ru.repp.den;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.repp.den.entity.Customer;
import ru.repp.den.repo.CustomerRepository;

import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.UUID;

/**
 * Class is used to execute initial loading to the database
 */
@Component
public class DbInitializer {

    public static final int AMOUNT = 10;

    @Autowired
    CustomerRepository cr;

    @PostConstruct
    public void fillDb() {
        Random r = new Random();
        for (int i=0;i<AMOUNT;i++) {
            cr.save(Customer.newBuilder()
                    .setName("Full name " + i)
                    .setActive(i % 2 == 0)
                    .setBalance(r.nextFloat()*100)
                    .setPwdHash(UUID.randomUUID().toString())
                    .setLogin("Login"+i)
                    .build());
        }
    }
}
