package ru.repp.den.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.multipart.MultipartFile;
import ru.repp.den.RooxApplication;
import ru.repp.den.converter.PartnerMappingConverter;
import ru.repp.den.dto.PartnerMappingDTO;
import ru.repp.den.entity.Customer;
import ru.repp.den.entity.PartnerMapping;
import ru.repp.den.exception.BadRequestException;
import ru.repp.den.exception.EntityNotFoundException;
import ru.repp.den.exception.InternalErrorException;
import ru.repp.den.repo.PartnerMappingRepository;
import ru.repp.den.service.PartnerMappingService;
import ru.repp.den.service.UserService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PartnerMappingServiceImpl implements PartnerMappingService{

    @Autowired
    PartnerMappingRepository pmr;

    @Autowired
    UserService us;

    @Override
    public List<PartnerMappingDTO> getAll() {
        List<PartnerMapping> all = pmr.findAll();
        return all.stream().map(PartnerMappingConverter::toDTO).collect(Collectors.toList());
    }

    @Override
    public PartnerMappingDTO addMapping(String id, PartnerMappingDTO mapping) {
        Customer c = us.getCustomerById(id);
        if (c == null) {
            throw new BadRequestException("Cannot create a Partner Mapping. The customer is not found for ID " + id);
        }
        PartnerMapping pm = PartnerMappingConverter.toEntity(mapping);
        pm.setId(null);
        pm.setCustomer(c);
        PartnerMapping saveRes = pmr.save(pm);
        return PartnerMappingConverter.toDTO(saveRes);

    }

    @Override
    public PartnerMappingDTO updateMapping(String id, Long mapId, PartnerMappingDTO mapping) {
        Customer c = us.getCustomerById(id);
        if (c == null) {
            throw new RestClientException("Cannot create a Partner Mapping. The customer is not found for ID " + id);
        }
        if (mapId == null) {
            throw new RestClientException("Cannot update a Partner Mapping. Mapping ID is null");
        }
        PartnerMapping existingPm = pmr.findOne(mapId);
        PartnerMapping pm;
        if (existingPm == null) {
            // create new record
            mapping.setId(mapId);
            pm = PartnerMappingConverter.toEntity(mapping);
            pm.setCustomer(c);
        } else {
            // update existing record
            pm = PartnerMappingConverter.appendToEntity(mapping, existingPm);
        }
        PartnerMapping saveRes = pmr.save(pm);
        return PartnerMappingConverter.toDTO(saveRes);
    }

    @Override
    public List<PartnerMappingDTO> getMappingsByCustomerId(String id) {
        Customer c = us.getCustomerById(id);
        if (c == null) {
            throw new RestClientException("Find a Partner Mapping. The Customer is not found for ID " + id);
        }
        return c.getPartnerMapping().stream().map(PartnerMappingConverter::toDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteMapping(String id, Long mapId) {
        Customer c = us.getCustomerById(id);
        if (c == null) {
            throw new RestClientException("Cannot create a Partner Mapping. The customer is not found for ID " + id);
        }
        if (mapId == null) {
            throw new RestClientException("Cannot update a Partner Mapping. Mapping ID is null");
        }
        pmr.delete(mapId);
    }

    @Override
    public PartnerMappingDTO getMappingById(String customerId, Long mapId) {
        return PartnerMappingConverter.toDTO(getPartnerMappingById(customerId, mapId));
    }

    @Override
    public void createAvatarForMapping(String id, Long mapId, MultipartFile file) {
        PartnerMapping pm = getPartnerMappingById(id, mapId);
        if (pm.getAvatar() != null) {
            throw new BadRequestException(String.format("Avatar already created for mapping id %s and customer id %s. Creation of the new one is not allowed. Try to update this record.", mapId, id));
        }
        String name = createNewAvatar(file);
        pm.setAvatar(name);
        pmr.save(pm);
    }

    private String createNewAvatar(MultipartFile file) {
        if (file.isEmpty()) {
            throw new BadRequestException("File is empty");
        }
        String name = UUID.randomUUID().toString();
        try {
            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(new File(RooxApplication.BASE_PATH + name)));
            FileCopyUtils.copy(file.getInputStream(), stream);
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalErrorException("Cannot upload file");
        }
        return name;
    }

    private String getAvatarFileName(String id, Long mapId) {
        PartnerMapping pm = getPartnerMappingById(id, mapId);
        return pm.getAvatar();
    }

    @Override
    public void deleteAvatarFromMapping(String id, Long mapId) {
        deleteAvatar(id, mapId);
        PartnerMapping pm = getPartnerMappingById(id, mapId);
        pm.setAvatar(null);
        pmr.save(pm);
    }

    private String deleteAvatar(String id, Long mapId) {
        String avatarName = getAvatarFileName(id,mapId);
        File file;
        try {
            file = new File(RooxApplication.BASE_PATH + avatarName);
            Files.delete(file.toPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new InternalErrorException(String.format("Avatar not found for mapping id %s for customer %s", mapId, id));
        } catch (IOException e) {
            e.printStackTrace();
            throw new InternalErrorException(String.format("Cannot delete avatar for mapping id %s and customer id %s", mapId, id));
        }
        return avatarName;
    }

    @Override
    public byte[] getAvatar(String id, Long mapId) {
        String fileName = getAvatarFileName(id,mapId);
        FileInputStream file;
        BufferedImage img;
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            file = new FileInputStream(RooxApplication.BASE_PATH + fileName);
            img = ImageIO.read(file);
            ImageIO.write(img, "png", bao);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new InternalErrorException(String.format("Avatar not found for mapping id %s for customer %s", mapId, id));
        } catch (IOException e) {
            e.printStackTrace();
            throw new InternalErrorException(String.format("Cannot read avatar for mapping id %s and customer id %s", mapId, id));
        }
        return bao.toByteArray();
    }

    @Override
    public void updateAvatarForMapping(String id, Long mapId, MultipartFile file) {
        PartnerMapping pm = getPartnerMappingById(id, mapId);
        if (pm.getAvatar() != null) {
            deleteAvatar(id,mapId);
        }
        String name = createNewAvatar(file);
        pm.setAvatar(name);
        pmr.save(pm);
    }

    private PartnerMapping getPartnerMappingById (String customerId, Long mapId) {
        if (mapId == null) {
            throw new EntityNotFoundException("Cannot get Partner mapping. Mapping ID is null");
        }
        Customer c = us.getCustomerById(customerId);
        if (c == null) {
            throw new EntityNotFoundException("Cannot find mapping by ID. Customer not found for ID " + customerId);
        }
        PartnerMapping pm = pmr.findOneByIdAndCustomerId(mapId, c.getId());
        if (pm == null) {
            throw new EntityNotFoundException(String.format("Such mapping for mapping id %s and customer id %s does not exists. Input parameters are incorrect", mapId, customerId));
        }
        return pm;
    }
}
