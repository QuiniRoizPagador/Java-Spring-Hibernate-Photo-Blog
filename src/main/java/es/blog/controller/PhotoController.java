package es.blog.controller;

import es.blog.model.Image;
import es.blog.model.Photo;
import es.blog.model.PhotoFamily;
import es.blog.service.FamilyService;
import es.blog.service.ImageService;
import es.blog.service.InfoService;
import es.blog.service.PhotoService;
import es.blog.utils.ImagesUtils;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.QueryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Quini_Dev
 */
@Controller
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @Autowired
    private FamilyService familyService;

    @Autowired
    private InfoService infoService;

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = {"/loadFamily/{id}"}, method = RequestMethod.GET)
    public String loadFamily(@PathVariable(value = "id") String id, ModelMap model) {
        try {
            int identifier = Integer.parseInt(id);
            List<Photo> photos = photoService.listPhotos(identifier);
            model.addAttribute("info", infoService.getInfo());
            model.addAttribute("photos", photos);
            model.addAttribute("families", familyService.listFamilies());
        } catch (QueryException | SQLException ex) {
            ex.printStackTrace();
        }

        return "photos";
    }

    @RequestMapping(value = {"/photo/{id}"}, method = RequestMethod.GET)
    public void loadPhoto(@PathVariable(value = "id") String id, ModelMap model, HttpServletResponse response) {
        try {

            Image image = photoService.getPhoto(id);

            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            response.getOutputStream().write(image.getImage());
            response.getOutputStream().close();

        } catch (QueryException | SQLException | IOException ex) {
            ex.printStackTrace();
        }

    }

    @RequestMapping(value = {"/infoPhoto/{id}/{num}"}, method = RequestMethod.GET)
    public void loadInphoPhoto(@PathVariable(value = "id") String id, @PathVariable(value = "num") String num,
            ModelMap model, HttpServletResponse response) {
        try {
            Image image = infoService.getPhoto(id, Integer.parseInt(num));
            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            response.getOutputStream().write(image.getImage());
            response.getOutputStream().close();
        } catch (QueryException | SQLException | IOException | NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    @RequestMapping(value = {"/image/{id}"}, method = RequestMethod.GET)
    public void familyImage(@PathVariable(value = "id") String id,
            ModelMap model, HttpServletResponse response) {
        try {
            Image image = imageService.getImage(Integer.parseInt(id));
            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            response.getOutputStream().write(image.getImage());
            response.getOutputStream().close();
        } catch (QueryException | IOException | NullPointerException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    @RequestMapping(value = {"/savePhoto"}, method = RequestMethod.POST)
    public String addPhoto(@ModelAttribute Photo photo, @RequestParam("foto") MultipartFile image, @RequestParam("family_id") String family, HttpServletRequest request) {
        try {
            if (image.getSize() > 3) {
                PhotoFamily photoFam = familyService.getFamily(Integer.parseInt(family));
                photo.setFamily(photoFam);
                photo.setUuid(UUID.randomUUID().toString());
                Image img = new Image();
                img.setImage(image.getBytes());
                imageService.saveImage(img);
                photo.setImage(img);
                photoService.addPhoto(photo);
            }

        } catch (QueryException | SQLException | IOException ex) {
            ex.printStackTrace();
        }
        return "redirect:/admin";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = {"/updateImage"}, method = RequestMethod.POST)
    public String updateImage(@ModelAttribute Photo photo, @RequestParam("foto") MultipartFile image, @RequestParam("family_id") String family, HttpServletRequest request) {
        try {
            PhotoFamily photoFam = familyService.getFamily(Integer.parseInt(family));
            photo.setFamily(photoFam);

            if (image.getSize() > 3) {
                Image img = new Image();
                img.setImage(image.getBytes());
                photo.setImage(img);
            }
            photoService.updatePhoto(photo);
        } catch (QueryException | SQLException | IOException ex) {
            ex.printStackTrace();
        }
        return "redirect:/admin";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = {"/deleteImage/{uuid}"}, method = RequestMethod.GET)
    public String deleteImage(@PathVariable(value = "uuid") String uuid, HttpServletRequest request) {
        try {
            photoService.deletePhoto(uuid);
            ImagesUtils.deleteImageFile(uuid, request);
        } catch (QueryException | SQLException ex) {
            ex.printStackTrace();
        }
        return "redirect:/admin";
    }
}
