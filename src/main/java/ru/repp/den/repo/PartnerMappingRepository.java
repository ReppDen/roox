package ru.repp.den.repo;

import org.springframework.data.repository.CrudRepository;
import ru.repp.den.entity.PartnerMapping;

import java.util.List;

public interface PartnerMappingRepository extends CrudRepository<PartnerMapping, Long> {

    List<PartnerMapping> findAll();
}
