import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Sticker {
  
  public BufferedImage getOriginalImageFromPathname(String imagePathname) throws Exception{
    BufferedImage originalImage = ImageIO.read(new File(imagePathname));
    return originalImage;
  }

  public BufferedImage getOriginalImageFromUrl(String imageUrl) throws Exception {
    InputStream inputStream = new URL(imageUrl).openStream();
    BufferedImage originalImage = ImageIO.read(inputStream);
    return originalImage;
  }

  public void create(BufferedImage originalImage, String stickerPhrase) throws Exception {
    // Read original image
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
    fontName = "Impact";
    Font font = new Font(fontName, fontStyle, fontSize);
    graphics.setColor(Color.YELLOW);
    graphics.setFont(font);

    // Get text dimensions based on font style and typed phrase
    FontMetrics fontMetrics = graphics.getFontMetrics();
    Rectangle2D phraseRectangle2d = fontMetrics.getStringBounds(stickerPhrase, graphics);
    int phraseWidth = (int) phraseRectangle2d.getWidth();
    
    // Calculate writing start area
    int widthToDraw = (int) ((originalWidth - phraseWidth) / 2);
    int heightToDraw = (int) ( originalHeight + (originalHeight * 0.075) + (fontSize / 2)); // Horizontal center of blank space
    
    // Write phrase on edited image
    graphics.drawString(stickerPhrase, widthToDraw, heightToDraw);

    // Write edited image to a file
    File directory = new File(String.valueOf("./outputs"));
    if (!directory.exists()) {
        directory.mkdir();
    }
    ImageIO.write(newImage, "png", new File("./outputs/picture.png"));
  }
}
