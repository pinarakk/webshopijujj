package ch.bfh.ti.webshop.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * UIHelper.
 *
 * @author Thomas Ingold
 * @version 1.0
 */

@Component
@Slf4j
public class UIHelper {

    /**
     * Image resource location.
     */
    private final static String IMAGE_RESOURCE_LOCATION = "static/images/";

    /**
     * Returns the width of a given image.
     *
     * @param imageName The image's name.
     * @return The image's width in pixels.
     */
    public int getImageWidth(String imageName) {

        int imageWidth = 0;

        try {

            BufferedImage bufferedImage = getBufferedImageByName(imageName);
            imageWidth = bufferedImage.getWidth();
        }
        catch(IOException e) {

            log.error("Image " + imageName + " not found.");
        }

        return imageWidth;
    }

    /**
     * Returns the height of a given image.
     *
     * @param imageName The image's name.
     * @return The image's height in pixel.
     */
    public int getImageHeight(String imageName) {

        int imageHeight = 0;

        try {

            BufferedImage bufferedImage = getBufferedImageByName(imageName);
            imageHeight = bufferedImage.getHeight();
        }
        catch(IOException e) {

            log.error("Image " + imageName + " not found.");
        }

        return imageHeight;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Private methods

    private BufferedImage getBufferedImageByName(String imageName) throws IOException {

        ClassPathResource classPathResource = new ClassPathResource(IMAGE_RESOURCE_LOCATION + imageName);
        InputStream inputStream  = classPathResource.getInputStream();
        return ImageIO.read(inputStream);
    }
}
