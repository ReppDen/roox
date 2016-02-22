package ru.repp.den.repo;

import org.springframework.data.repository.CrudRepository;
import ru.repp.den.entity.PartnerMapping;

import javax.xml.soap.SAAJResult;
import java.util.List;

public interface PartnerMappingRepository extends CrudRepository<PartnerMapping, Long> {

    List<PartnerMapping> findAll();

    PartnerMapping findOneByIdAndCustomerId(Long id, Long customerId);
}
