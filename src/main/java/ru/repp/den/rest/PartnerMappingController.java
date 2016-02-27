package ru.repp.den.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.repp.den.dto.PartnerMappingDTO;
import ru.repp.den.service.PartnerMappingService;

import javax.servlet.ServletContext;
import javax.servlet.annotation.MultipartConfig;
import java.io.*;
import java.util.List;

@RestController
@MultipartConfig(maxFileSize = 307200)
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
    public PartnerMappingDTO getMappingsById(@PathVariable String customerId, @PathVariable Long mapId) {
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

    @Autowired
    ServletContext context;

    @ResponseBody
    @RequestMapping(value = "/customers/{id}/mappings/{mapId}/avatar", method = RequestMethod.GET, produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE})
    public byte[] getAvatar(@PathVariable String id,
                            @PathVariable Long mapId) throws IOException {
        return pms.getAvatar(id, mapId);
    }

    @RequestMapping(value = "/customers/{id}/mappings/{mapId}/avatar", method = RequestMethod.POST)
    public void uploadNewAvatar(@PathVariable String id,
                                 @PathVariable Long mapId,
                                 @RequestParam("file") MultipartFile file) {
        pms.createAvatarForMapping(id, mapId, file);
    }

    @RequestMapping(value = "/customers/{id}/mappings/{mapId}/avatar", method = RequestMethod.PUT)
    public void updateAvatar(@PathVariable String id,
                                @PathVariable Long mapId,
                                @RequestParam("file") MultipartFile file) {
        pms.updateAvatarForMapping(id, mapId, file);
    }

    @RequestMapping(value = "/customers/{id}/mappings/{mapId}/avatar", method = RequestMethod.DELETE)
    public void deleteAvatar(@PathVariable String id,
                             @PathVariable Long mapId) {
        pms.deleteAvatarFromMapping(id, mapId);
    }


}
