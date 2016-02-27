package ru.repp.den.service;

import org.springframework.web.multipart.MultipartFile;
import ru.repp.den.dto.PartnerMappingDTO;

import java.util.List;

public interface PartnerMappingService {

    PartnerMappingDTO addMapping(String id, PartnerMappingDTO mapping);

    PartnerMappingDTO updateMapping(String id, Long mapId, PartnerMappingDTO mapping);

    List<PartnerMappingDTO> getMappingsByCustomerId(String id);

    void deleteMapping(String id, Long mapId);

    PartnerMappingDTO getMappingById(String customerId, Long mapId);

    boolean createAvatarForMapping(String id, Long mapId, MultipartFile file);

    void deleteAvatarFromMapping(String id, Long mapId);

    byte[] getAvatar(String id, Long mapId);

    void updateAvatarForMapping(String id, Long mapId, MultipartFile file);
}
