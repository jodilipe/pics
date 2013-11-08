package dk.japps.pics.file;

import java.io.File;
import java.io.IOException;

import net.coobird.thumbnailator.Thumbnails;

public class ImageCopyUtil {

	private ImageCopyUtil() {
	}
	
	public static void createPictureCopy(String source, String thumb, int maxDim) {
		createPictureCopy(source, thumb, maxDim, 1.0, true);
	}
	
	public static void createPictureCopy(String source, String thumb, int maxDim, double quality) {
		createPictureCopy(source, thumb, maxDim, quality, true);
	}
	
	public static void createPictureCopy(String source, String thumb, int maxDim, double quality, boolean allowOverwrite) {
		try {
			Thumbnails.of(new File(source)).size(maxDim, maxDim).outputQuality(quality).allowOverwrite(allowOverwrite).toFile(new File(thumb));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
