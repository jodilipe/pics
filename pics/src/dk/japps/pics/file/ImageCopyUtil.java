package dk.japps.pics.file;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;

import javax.swing.*;

import com.sun.image.codec.jpeg.*;

public class ImageCopyUtil {
  public static final float UNCHANGED = 1.0f;
  public static final float VERY_HIGH	= 0.95f;
  public static final float HIGH 			= 0.75f;
  public static final float MEDIUM 		= 0.50f;
  public static final float LOW 			= 0.25f;
  
	public static void main(String[] args) {
		createPictureCopy("./web/img/Tobias.jpg", "./web/thumbs/Tobias_thumb.jpg", 500);
		System.exit(0);
	}
	
	private ImageCopyUtil() {
	}

	public static void createPictureCopy(byte[] orig, String thumb, int maxDim) {
		createPictureCopy(orig, thumb, maxDim, UNCHANGED);
	}

	public static void createPictureCopy(byte[] orig, String thumb, int maxDim, float quality) {
		createPictureCopy(new ImageIcon(orig).getImage(), thumb, maxDim, quality);
	}

	public static void createPictureCopy(String orig, String thumb, int maxDim) {
	  createPictureCopy(orig, thumb, maxDim, UNCHANGED);
	}

	public static void createPictureCopy(String orig, String thumb, int maxDim, float quality) {
		createPictureCopy(new ImageIcon(orig).getImage(), thumb, maxDim, quality);
	}

	private static void createPictureCopy(Image inImage, String thumb, int maxDim, float quality) {
		try {
			// Get the image from a file.
//			Image inImage = new ImageIcon(orig).getImage();

			// Determine the scale.
			double scale = (double) maxDim / (double) inImage.getHeight(null);
			if (inImage.getWidth(null) > inImage.getHeight(null)) {
				scale = (double) maxDim / (double) inImage.getWidth(null);
			}

			// Determine size of new image. One of them
			// should equal maxDim.
			int scaledW = (int) (scale * inImage.getWidth(null));
			int scaledH = (int) (scale * inImage.getHeight(null));

			// Create an image buffer in which to paint on.
			BufferedImage outImage = new BufferedImage(scaledW, scaledH, BufferedImage.TYPE_INT_RGB);

			// Set the scale.
			AffineTransform tx = new AffineTransform();

			// If the image is smaller than the desired image size,
			// don't bother scaling.
			if (scale < 1.0d) {
				tx.scale(scale, scale);
			}

			// Paint image.
			Graphics2D g2d = outImage.createGraphics();
			g2d.drawImage(inImage, tx, null);
			g2d.dispose();

			// JPEG-encode the image and write to file.
			OutputStream os = new FileOutputStream(thumb);

			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
			JPEGEncodeParam encodeParam = encoder.getDefaultJPEGEncodeParam(outImage);
			encodeParam.setQuality(quality, false);
			encoder.encode(outImage, encodeParam);
			os.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
