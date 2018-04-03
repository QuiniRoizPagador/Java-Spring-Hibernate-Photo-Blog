package es.blog.model;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Quini_Dev
 */
@Entity
@Table(name = "photos")
public class Photo implements Serializable {

    private static final long serialVersionUID = 1735509778614055781L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "uuid", unique = true, nullable = false)
    private String uuid;
    @Column(name = "description", unique = true, nullable = false)
    private String description;
    @Column(name = "title", unique = true, nullable = false)
    private String title;
    @OneToOne(cascade = CascadeType.ALL)
    private PhotoFamily family;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="_image")
    private Image _image;

    public Photo() {
    }

    public Image getImage() {
        return _image;
    }

    public void setImage(Image image) {
        _image = image;
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
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the family
     */
    public PhotoFamily getFamily() {
        return family;
    }

    /**
     * @param family the family to set
     */
    public void setFamily(PhotoFamily family) {
        this.family = family;
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

    public void setUuid() {
        uuid = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "Id: " + getId()
                + "\nUUID: " + getUuid()
                + "\nTitle: " + getTitle()
                + "\nDescription: " + getDescription();
    }

}
