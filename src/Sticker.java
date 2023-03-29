import java.io.File;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Sticker {
  
  public void create(String imagePathname, String stickerPhrase) throws Exception {
    // Read original image
    BufferedImage originalImage = ImageIO.read(new File(imagePathname));
    int originalHeight = originalImage.getHeight();
    int originalWidth = originalImage.getWidth();

    // Calculate new image dimensions
    int newHeight = (int) (originalHeight * 1.15);

    // Create new transparent and resized image (in-memory)
    BufferedImage newImage = new BufferedImage(originalWidth, newHeight, BufferedImage.TRANSLUCENT);

    // Copy original image into new image (in-memory) to generate edited image
    Graphics2D graphics = (Graphics2D) newImage.getGraphics();
    graphics.drawImage(originalImage, 0, 0, null);

    // Configure font style
    int fontSize = (int) (originalHeight * 0.05);
    int fontStyle = Font.BOLD;
    String fontName = Font.SANS_SERIF;
    Font font = new Font(fontName, fontStyle, fontSize);
    graphics.setColor(Color.YELLOW);
    graphics.setFont(font);

    // Write phrase on edited image
    int heightToDraw = (int) ( originalHeight + (originalHeight * 0.075) + (fontSize / 2)); // Center of blank space
    graphics.drawString(stickerPhrase, 100, heightToDraw);

    // Write edited image to a file
    File directory = new File(String.valueOf("./outputs"));
    if (!directory.exists()) {
        directory.mkdir();
    }
    ImageIO.write(newImage, "png", new File("./outputs/picture.png"));
  }
}
