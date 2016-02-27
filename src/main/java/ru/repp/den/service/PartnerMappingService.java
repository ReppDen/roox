package ru.repp.den.service;

import org.springframework.web.multipart.MultipartFile;
import ru.repp.den.dto.CustomerDTO;
import ru.repp.den.dto.PartnerMappingDTO;
import ru.repp.den.entity.PartnerMapping;

import java.util.List;

public interface PartnerMappingService {
    List<PartnerMappingDTO> getAll();

    PartnerMappingDTO addMapping(String id, PartnerMappingDTO mapping);

    PartnerMappingDTO updateMapping(String id, Long mapId, PartnerMappingDTO mapping);

    List<PartnerMappingDTO> getMappingsByCustomerId(String id);

    void deleteMapping(String id, Long mapId);

    PartnerMappingDTO getMappingById(String customerId, Long mapId);

    void createAvatarForMapping(String id, Long mapId, MultipartFile file);

    void deleteAvatarFromMapping(String id, Long mapId);

    byte[] getAvatar(String id, Long mapId);

    void updateAvatarForMapping(String id, Long mapId, MultipartFile file);
}
