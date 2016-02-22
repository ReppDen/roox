package ru.repp.den.repo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.repp.den.DbInitializer;
import ru.repp.den.RooxApplication;
import ru.repp.den.entity.PartnerMapping;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RooxApplication.class)
public class PartnerMappingRepositoryTest {

    @Autowired
    PartnerMappingRepository pmr;

    @Test
    public void testFindAll(){
        List<PartnerMapping> all = pmr.findAll();

        assertNotNull(all);
        assertEquals(all.size(), DbInitializer.MAPPINGS_AMOUNT);
    }
}
