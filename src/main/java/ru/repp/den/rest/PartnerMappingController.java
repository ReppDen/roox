package ru.repp.den.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.repp.den.RooxApplication;
import ru.repp.den.dto.PartnerMappingDTO;
import ru.repp.den.service.PartnerMappingService;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.annotation.MultipartConfig;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;
import java.util.UUID;

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

    @Autowired
    ServletContext context;

    @ResponseBody
    @RequestMapping(value = "/customers/{id}/mappings/{mapId}/avatar", method = RequestMethod.GET, produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE})
    public byte[] getAvatar(@PathVariable String id,
                            @PathVariable Long mapId) throws IOException {
//        InputStream is = this.getClass().getResourceAsStream("/avatars/1.png");
        String fileName = pms.getAvatarFileName(id,mapId);
        FileInputStream file = new FileInputStream(RooxApplication.BASE_PATH + fileName);
        BufferedImage img = ImageIO.read(file);
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ImageIO.write(img, "png", bao);
        return bao.toByteArray();
    }

    @RequestMapping(value = "/customers/{id}/mappings/{mapId}/avatar", method = RequestMethod.POST)
    public void uploadAvatar(@PathVariable String id,
                                 @PathVariable Long mapId,
                                 @RequestParam("file") MultipartFile file,
                                 RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            throw new RestClientException("File is empty");
        }
        String name = UUID.randomUUID().toString();
        try {
            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(new File(RooxApplication.BASE_PATH + name)));
            FileCopyUtils.copy(file.getInputStream(), stream);
            stream.close();
        } catch (Exception e) {
            throw new RestClientException("Cannot upload file");
        }

        pms.updateAvatarForMapping(id, mapId, name);
    }


}
