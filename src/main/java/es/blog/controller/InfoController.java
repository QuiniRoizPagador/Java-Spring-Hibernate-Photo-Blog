package es.blog.controller;

import es.blog.model.Education;
import es.blog.model.Experience;
import es.blog.model.Image;
import es.blog.model.Info;
import es.blog.model.Skill;
import es.blog.service.EducationService;
import es.blog.service.ExperienceService;
import es.blog.service.ImageService;
import es.blog.service.InfoService;
import es.blog.service.SkillService;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
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
public class InfoController {

    @Autowired
    private InfoService infoService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private SkillService skillService;
    @Autowired
    private ExperienceService experienceService;
    @Autowired
    private EducationService educationService;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = {"/updateInfo"}, method = RequestMethod.POST)
    public String updateInfo(@ModelAttribute Info info, @RequestParam("profile1") MultipartFile profile1,
            @RequestParam("profile2") MultipartFile profile2, HttpServletRequest request) {
        try {
            if (profile1.getSize() > 3) {
                Info inf = infoService.getInfo();
                Image img = new Image();
                img.setImage(profile1.getBytes());
                imageService.saveImage(img);
                info.setImage1(img);
                if (inf.getImage1() != null) {
                    imageService.removeImage(inf.getImage1());
                }
            }
            if (profile2.getSize() > 3) {
                Info inf = infoService.getInfo();
                Image img = new Image();
                img.setImage(profile2.getBytes());
                imageService.saveImage(img);
                info.setImage2(img);
                if (inf.getImage2() != null) {
                    imageService.removeImage(inf.getImage2());
                }
            }
            infoService.updateInfo(info);
        } catch (QueryException | SQLException | IOException ex) {
            ex.printStackTrace();
        }
        return "redirect:/admin";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = {"/saveSkill"}, method = RequestMethod.POST)
    public String addSkill(@ModelAttribute Skill skill) {
        try {
            skill.setInfo(infoService.getInfo());
            skillService.addSkill(skill);
        } catch (QueryException | SQLException ex) {
            ex.printStackTrace();
        }
        return "redirect:/admin";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = {"/updateSkill"}, method = RequestMethod.POST)
    public String updateSkill(@ModelAttribute Skill skill) {
        try {
            skillService.updateSkill(skill);
        } catch (QueryException | SQLException ex) {
            ex.printStackTrace();
        }
        return "redirect:/admin";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = {"/deleteSkill/{id}"}, method = RequestMethod.GET)
    public String deleteSkill(@PathVariable(value = "id") String id) {
        try {
            int identifier = Integer.parseInt(id);
            skillService.deleteSkill(identifier);
        } catch (QueryException | SQLException ex) {
            ex.printStackTrace();
        }
        return "redirect:/admin";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = {"/saveExperience"}, method = RequestMethod.POST)
    public String addExperience(@ModelAttribute Experience experience) {
        try {
            experience.setInfo(infoService.getInfo());
            experienceService.addExperience(experience);
        } catch (QueryException | SQLException ex) {
            ex.printStackTrace();
        }
        return "redirect:/admin";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = {"/updateExperience"}, method = RequestMethod.POST)
    public String updateExperience(@ModelAttribute Experience experience) {
        try {
            experienceService.updateExperience(experience);
        } catch (QueryException | SQLException ex) {
            ex.printStackTrace();
        }
        return "redirect:/admin";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = {"/deleteExperience/{id}"}, method = RequestMethod.GET)
    public String deleteExperience(@PathVariable(value = "id") String id) {
        try {
            int identifier = Integer.parseInt(id);
            experienceService.deleteExperience(identifier);
        } catch (QueryException | SQLException ex) {
            ex.printStackTrace();
        }
        return "redirect:/admin";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = {"/saveEducation"}, method = RequestMethod.POST)
    public String addEducation(@ModelAttribute Education education) {
        try {
            education.setInfo(infoService.getInfo());
            educationService.addEducation(education);
        } catch (QueryException | SQLException ex) {
            ex.printStackTrace();
        }
        return "redirect:/admin";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = {"/updateEducation"}, method = RequestMethod.POST)
    public String updateExperience(@ModelAttribute Education education) {
        try {
            educationService.updateEducation(education);
        } catch (QueryException | SQLException ex) {
            ex.printStackTrace();
        }
        return "redirect:/admin";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = {"/deleteEducation/{id}"}, method = RequestMethod.GET)
    public String deleteEducation(@PathVariable(value = "id") String id) {
        try {
            int identifier = Integer.parseInt(id);
            educationService.deleteEducation(identifier);
        } catch (QueryException | SQLException ex) {
            ex.printStackTrace();
        }
        return "redirect:/admin";
    }

}
