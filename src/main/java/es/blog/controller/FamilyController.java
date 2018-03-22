package es.blog.controller;

import es.blog.model.Image;
import es.blog.model.PhotoFamily;
import es.blog.service.FamilyService;
import es.blog.service.ImageService;
import java.io.IOException;
import java.sql.SQLException;
import org.hibernate.QueryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
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
public class FamilyController {

    @Autowired
    private ImageService imageService;
    @Autowired
    private FamilyService familyService;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = {"/savefamily"}, method = RequestMethod.POST)
    public String addFamily(@ModelAttribute PhotoFamily family, @RequestParam("foto") MultipartFile image) {
        try {
            Image img = new Image();
            img.setImage(image.getBytes());
            imageService.saveImage(img);
            family.setFirstImage(img);
            family.setDescription(family.getDescription().toUpperCase());
            familyService.addFamily(family);
        } catch (QueryException | SQLException | IOException ex) {
            ex.printStackTrace();
        }
        return "redirect:/admin";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = {"/updatefamily"}, method = RequestMethod.POST)
    public String updateFamily(@ModelAttribute PhotoFamily family, @RequestParam("foto") MultipartFile image) {
        try {
            Image img = new Image();
            img.setImage(image.getBytes());
            Image oldImage = familyService.getFamilyPortade(family.getId());
            imageService.saveImage(img);
            family.setFirstImage(img);
            familyService.updateFamily(family);
            imageService.removeImage(oldImage);

        } catch (QueryException | SQLException | IOException ex) {
            ex.printStackTrace();
        }
        return "redirect:/admin";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = {"/deleteFamily/{id}"}, method = RequestMethod.GET)
    public String deleteFamily(@PathVariable(value = "id") String id) {
        try {
            int identifier = Integer.parseInt(id);
            familyService.deleteFamily(identifier);
        } catch (QueryException | SQLException ex) {
            ex.printStackTrace();
        }
        return "redirect:/admin";
    }

}
