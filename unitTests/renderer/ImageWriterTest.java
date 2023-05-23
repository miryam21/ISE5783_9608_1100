package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

import static org.junit.jupiter.api.Assertions.*;

class ImageWriterTest {

    /**
     * Tests the writeToImage method of the ImageWriter class.
     */
    @Test
    void testWriteToImage() {
        ImageWriter imageWriter = new ImageWriter("ImageTest", 800, 500);
        for (int i = 0; i < 500; i++) {
            for (int j = 0; j < 800; j++) {
                if (i % 50 == 0 || j % 50 == 0)
                    imageWriter.writePixel(j, i, new Color(255, 0, 0));
                else
                    imageWriter.writePixel(j, i, new Color(255, 255, 0));
            }
        }
        imageWriter.writeToImage();
    }

}