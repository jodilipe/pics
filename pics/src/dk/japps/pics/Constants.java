package dk.japps.pics;


public class Constants {
	public static final String PICTURE_PATH_RELATIVE = PicsProperties.getProperty("picture_path_relative");
	public static final String PICTURE_PATH = PicsProperties.getProperty("picture_path");
	public static final String THUMBNAIL_PATH = PicsProperties.getProperty("thumbnail_path");
	public static final String PREVIEW_PATH = PicsProperties.getProperty("preview_path");
	
	public static final float THUMBNAIL_QUALITY = new Float(PicsProperties.getProperty("thumbnail_quality"));
	public static final int THUMBNAIL_SIZE = new Integer(PicsProperties.getProperty("thumbnail_size"));

	public static final float PREVIEW_QUALITY = new Float(PicsProperties.getProperty("preview_quality"));
	public static final int PREVIEW_SIZE = new Integer(PicsProperties.getProperty("preview_size"));
}
