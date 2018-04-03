package es.blog.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Quini_Dev
 */
@Entity
@Table(name = "info")
public class Info implements Serializable {

    private static final long serialVersionUID = 3871620496966521321L;
	@Id
    private int id;
    private String uuid;
    private String name;
    private String mail;
    private String city;
    private String phone;
    private String description;
    private String footer;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="image1")
    private Image image1;
    @JoinColumn(name="image2")
    @OneToOne(cascade = CascadeType.ALL)
    private Image image2;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "info", cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Skill> skills;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "info", cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    @OrderBy("from DESC")
    private List<Education> education;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "info", cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    @OrderBy("from DESC")
    private List<Experience> experience;

    public Info() {
    }

    public Image getImage1() {
        return image1;
    }

    public Image getImage2() {
        return image2;
    }

    public void setImage1(Image image1) {
        this.image1 = image1;
    }

    public void setImage2(Image image2) {
        this.image2 = image2;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * @param mail the mail to set
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid the uuid to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the footer
     */
    public String getFooter() {
        return footer;
    }

    /**
     * @param footer the footer to set
     */
    public void setFooter(String footer) {
        this.footer = footer;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<Education> getEducation() {
        return education;
    }

    public void setEducation(List<Education> education) {
        this.education = education;
    }

    public List<Experience> getExperience() {
        return experience;
    }

    public void setExperience(List<Experience> experience) {
        this.experience = experience;
    }

}
