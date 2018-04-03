package es.blog.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Quini_Dev
 */
@Entity
@Table(name = "skills")
public class Skill implements Serializable {

    private static final long serialVersionUID = 3200701722982781518L;
	@Id
    private int id;
    private boolean isSkill;
    private int percent;
    private String description;
    @ManyToOne
    @JoinColumn(name = "info_id")
    private Info info;

    public Skill() {

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
     * @return the isSkill
     */
    public boolean isIsSkill() {
        return isSkill;
    }

    /**
     * @param isSkill the isSkill to set
     */
    public void setIsSkill(boolean isSkill) {
        this.isSkill = isSkill;
    }

    /**
     * @return the percent
     */
    public int getPercent() {
        return percent;
    }

    /**
     * @param percent the percent to set
     */
    public void setPercent(int percent) {
        this.percent = percent;
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

    public void setInfo(Info info) {
        this.info = info;
    }

    public Info getInfo() {
        return info;
    }

}
