package BrickClass;

import java.util.ArrayList;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public class SpriteSheet {
    
    protected Image spriteSheet;
    protected int width, height, imgCount;
    protected ArrayList<Image> arr = new ArrayList<Image>();

    public SpriteSheet(Image spriteSheet, int width, int height, int imgCount)
    {
        this.spriteSheet = spriteSheet;
        this.width = width;
        this.height = height;
        this.imgCount = imgCount;
        
        int y = 0;
		while (y * height < spriteSheet.getHeight(null) - height + 1) {
			int x = 0;
			while (x * width < spriteSheet.getWidth(null) - width + 2) {
				BufferedImage bimg = toBufferedImage(spriteSheet).getSubimage(x * width, y * height, width,
						height);
				arr.add(bimg);
				// System.out.println("x"+x);
				x++;
				if (arr.size() == imgCount) {
					x = spriteSheet.getWidth(null);
					y = spriteSheet.getHeight(null);
				}
			}
			// System.out.println("y"+y);
			y++;
		}

    }

    private static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		// Draw the image on to the buffered image
		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();

		// Return the buffered image
		return bimage;
    }

    public Image get(int img)
    {
        return arr.get(img);
    }

}
