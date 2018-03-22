package es.blog.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author Quini
 */
public class ImagesUtils {

    /**
     *
     * @param name
     * @param request
     */
    public static void deleteImageFile(String name, HttpServletRequest request) {
        File dir = new File(request.getSession().getServletContext().getRealPath("/") + "resources/images");
        File serverFile = new File(dir + "/" + name + ".jpg");
        serverFile.delete();
    }

    

    /**
     *
     * @param image
     * @param name
     * @param request
     * @throws IOException
     */
    public static void saveImageFile(byte[] image, String name, HttpServletRequest request) throws IOException {
        File dir = new File(request.getSession().getServletContext().getRealPath("/") + "resources/images");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File serverFile = new File(dir + "/" + name + ".jpg");
        BufferedOutputStream stream;

        stream = new BufferedOutputStream(new FileOutputStream(serverFile));
        stream.write(image);
        stream.close();

    }

    /**
     *
     * @param fileData
     * @param width
     * @param height
     * @return
     * @throws java.io.IOException
     */
    public static byte[] escalar(byte[] fileData, int width, int height) throws IOException {
        ByteArrayInputStream in = new ByteArrayInputStream(fileData);
        BufferedImage img = ImageIO.read(in);
        if (height == 0) {
            height = (width * img.getHeight()) / img.getWidth();
        }
        if (width == 0) {
            width = (height * img.getWidth()) / img.getHeight();
        }
        java.awt.Image scaledImage = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        imageBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0, 0, 0), null);

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        ImageIO.write(imageBuff, "jpg", buffer);

        return buffer.toByteArray();

    }

    
}
