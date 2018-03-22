package es.blog.model;

import java.util.List;

/**
 *
 * @author Quini_Dev
 */
public class ImageData {
    
    private PhotoFamily family;
    private List<Photo> images;

    public ImageData() {
    }

    public PhotoFamily getFamily() {
        return family;
    }

    public void setFamily(PhotoFamily family) {
        this.family = family;
    }

    public List<Photo> getImages() {
        return images;
    }

    public void setImages(List<Photo> images) {
        this.images = images;
    }
    
    
    
}
