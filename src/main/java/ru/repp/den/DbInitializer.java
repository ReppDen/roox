package ru.repp.den;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.repp.den.entity.Customer;
import ru.repp.den.entity.PartnerMapping;
import ru.repp.den.repo.CustomerRepository;
import ru.repp.den.repo.PartnerMappingRepository;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Class is used to execute initial loading to the database
 */
@Component
public class DbInitializer {

    public static final int CUSTOMERS_AMOUNT = 10;
    public static final int MAPPINGS_AMOUNT = 20;

    @Autowired
    CustomerRepository cr;

//    @Autowired
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    PartnerMappingRepository pmr;

    @Autowired
    ApplicationContext appContext;


    private Random r = new Random();

    @PostConstruct
    public void fillDb() throws IOException {
        String customerBaseName = "Customer full name ";
        String customerBaseLogin = "Login";

        for (int i=0;i< CUSTOMERS_AMOUNT;i++) {

            cr.save(Customer.newBuilder()
                    .setName(customerBaseName + i)
                    .setActive(i % 2 == 0)
                    .setBalance(r.nextFloat() * 100)
                    .setPwdHash(passwordEncoder.encode("pass"))
                    .setLogin(customerBaseLogin + i)
                    .build());
        }

        for (int i = 0; i < MAPPINGS_AMOUNT; i++) {
            Customer c = cr.findByLogin(customerBaseLogin + (Math.abs(r.nextLong()) % CUSTOMERS_AMOUNT));
            PartnerMapping pm = PartnerMapping.newBuilder()
                    .setAccount("account" + i)
                    .setFullName("External " + c.getName())
                    .setPartnerId(Math.abs(r.nextLong()) % 1000)
                    .setCustomer(c)
                    .setAvatar(getRandomFileName())
                    .build();

            pmr.save(pm);
        }


    }

    private String getRandomFileName() {
        String[] files = new File(RooxApplication.BASE_PATH).list();
        if (files == null) {
            return null;
        }
        return files[r.nextInt(files.length)];
    }
}
