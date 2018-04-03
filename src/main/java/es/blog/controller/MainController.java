package es.blog.controller;

import es.blog.model.Education;
import es.blog.model.Experience;
import es.blog.model.GeoLocation;
import es.blog.model.Photo;
import es.blog.model.PhotoFamily;
import es.blog.model.Skill;
import es.blog.model.SystemUser;
import es.blog.service.FamilyService;
import es.blog.service.GeoLocationService;
import es.blog.service.InfoService;
import es.blog.service.PhotoService;
import es.blog.service.UserService;
import es.blog.utils.SendMail;
import es.blog.utils.VerifyRecaptcha;
import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Quini_Dev
 */
@Controller
public class MainController {

    @Autowired
    private PhotoService photoService;
    @Autowired
    private FamilyService familyService;
    @Autowired
    private InfoService infoService;
    @Autowired
    private GeoLocationService geoLocationService;
    @Autowired
    private UserService userService;

    private Thread thread;
    private Runnable task;

    @RequestMapping(value = {"/portal"}, method = RequestMethod.GET)
    public String portal(ModelMap model, @ModelAttribute("success") String success, @ModelAttribute("error") String error) {
        try {
            model.addAttribute("families", familyService.listFamilies());
            model.addAttribute("familyData", userService.getImagesAndFamiliesFromUser());
            model.addAttribute("info", infoService.getInfo());

        } catch (QueryException | SQLException ex) {
            ex.printStackTrace();
        }
        return "home";
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login(ModelMap model) {
        return "login";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
    public String admin(ModelMap model, Principal p) {
        try {
            model.addAttribute("family", new PhotoFamily());
            model.addAttribute("photo", new Photo());
            model.addAttribute("skill", new Skill());
            model.addAttribute("experience", new Experience());
            model.addAttribute("education", new Education());
            model.addAttribute("families", familyService.listFamilies());
            model.addAttribute("images", photoService.listPhotos());
            model.addAttribute("info", infoService.getInfo());
            model.addAttribute("geoTotal", geoLocationService.countAll());
            model.addAttribute("geos", geoLocationService.findAll());
        } catch (QueryException | SQLException ex) {
            ex.printStackTrace();
        }
        return "admin";
    }

    @RequestMapping(value = {"/geoLocation/{location}"}, method = RequestMethod.GET)
    public @ResponseBody
    boolean geoLocation(@PathVariable(value = "location") String location) {
        boolean res = true;
        try {
            String id = RequestContextHolder.currentRequestAttributes().getSessionId();
            GeoLocation geo = geoLocationService.find(id);
            if (geo == null) {
                geo = new GeoLocation();
                geo.setSesionId(id);
                geo.setLocationCountry(location);
                geoLocationService.save(geo);
            }
        } catch (QueryException | SQLException ex) {
            ex.printStackTrace();
            res = false;
        }
        return res;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = {"/updatePassword"}, method = RequestMethod.POST)
    public String updatePassword(@RequestParam("password1") String password, Principal p) {
        try {
            SystemUser user = userService.find(p.getName());
            if (!user.getPassword().equals(password)) {
                user.setPassword(password);
                userService.updatePassword(user);
            }
        } catch (QueryException | SQLException ex) {
            ex.printStackTrace();
        }
        return "redirect:/admin";
    }

    @RequestMapping(value = {"/sendMail"}, method = RequestMethod.GET)
    public ModelAndView sendMail(@RequestParam("nombre") String nombre, @RequestParam("email") String email,
            @RequestParam("comentario") String mensaje, @RequestParam("g-recaptcha-response") String captcha, Principal p) {
        boolean res = false;
        ModelAndView model = new ModelAndView();
        try {
            res = VerifyRecaptcha.verify(captcha);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (res) {
                sendMessage(nombre, email, mensaje);
                model.addObject("success", "Gracias, leer&eacute; tu mensaje tan pronto como sea posible.");
            } else {
                model.addObject("error", "Ha ocurrido un error al enviar tu mensaje. Por favor, int&eacute;ntalo de nuevo en otro momento.");
            }
            model.setViewName("redirect:/");
        }
        return model;

    }

    private void sendMessage(String nombre, String email, String mensaje) {
        task = () -> {
            try {
                SendMail.send("Tienes un nuevo mensaje de " + nombre + "!",
                        "Email de contacto: " + email
                        + "\nContenido del mensaje: \n\n" + mensaje,
                        "dammacontrol@gmail.com");

            } catch (AddressException e) {
                e.printStackTrace();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        };

        thread = new Thread(task);
        thread.start();
    }

}
