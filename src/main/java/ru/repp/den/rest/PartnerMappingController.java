package ru.repp.den.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.repp.den.dto.PartnerMappingDTO;
import ru.repp.den.service.PartnerMappingService;

import java.util.List;

@RestController
public class PartnerMappingController {

    @Autowired
    PartnerMappingService pms;

    @RequestMapping("/mapping")
    public List<PartnerMappingDTO> getAll() {
        return pms.getAll();
    }

    @RequestMapping(value = "/customers/{id}/mappings", method = RequestMethod.GET)
    public List<PartnerMappingDTO> getMappingsByCustomerId(@PathVariable String id) {
        return pms.getMappingsByCustomerId(id);
    }

    @RequestMapping(value = "/customers/{customerId}/mappings/{mapId}", method = RequestMethod.GET)
    public PartnerMappingDTO getMappingsByCustomerId(@PathVariable String customerId, @PathVariable Long mapId) {
        return pms.getMappingById(customerId, mapId);
    }

    @RequestMapping(value = "/customers/{id}/mappings", method = RequestMethod.POST)
    public PartnerMappingDTO addMappingForCustomer(@PathVariable String id, @RequestBody PartnerMappingDTO mapping) {
        return pms.addMapping(id, mapping);
    }

    @RequestMapping(value = "/customers/{id}/mappings/{mapId}", method = RequestMethod.PUT)
    public PartnerMappingDTO updateMappingForCustomer(@PathVariable String id, @PathVariable Long mapId, @RequestBody PartnerMappingDTO mapping) {
        return pms.updateMapping(id, mapId, mapping);
    }

    @RequestMapping(value = "/customers/{id}/mappings/{mapId}", method = RequestMethod.DELETE)
    public void deleteMappingForCustomer(@PathVariable String id, @PathVariable Long mapId) {
        pms.deleteMapping(id, mapId);
    }
}
