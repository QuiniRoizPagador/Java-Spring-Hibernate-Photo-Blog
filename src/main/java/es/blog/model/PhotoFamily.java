package es.blog.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Quini_Dev
 */
@Entity
@Table(name = "families")
public class PhotoFamily implements Serializable {

    private static final long serialVersionUID = -347003119926717137L;
	@Id
    private int id;
    @Column(name = "description")
    private String description;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="firstImage")
    private Image firstImage;

    public PhotoFamily() {
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    public Image getFirstImage() {
        return firstImage;
    }

    public void setFirstImage(Image firstImage) {
        this.firstImage = firstImage;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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

}
