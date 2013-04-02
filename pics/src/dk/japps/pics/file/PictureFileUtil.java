package dk.japps.pics.file;

import java.io.*;
import java.util.*;

import com.drew.imaging.*;
import com.drew.metadata.*;

import dk.japps.pics.*;

public class PictureFileUtil {
	private static List<String> excludeTags = new ArrayList<String>();
	private static List<String> includeTags = new ArrayList<String>();
	
	static {
		excludeTags.add("Firmware Revision");
		excludeTags.add("Unknown tag");
		excludeTags.add("Related Image Width");
		excludeTags.add("Date Stamp Mode");
		excludeTags.add("Control Mode");
		excludeTags.add("Face Detect Array");
		excludeTags.add("Unknown Camera Setting");
		excludeTags.add("AF Info Array");
		excludeTags.add("AF Point Selected");
		excludeTags.add("Blue Colorant");
		excludeTags.add("Blue TRC");
		excludeTags.add("CMM Type");
		excludeTags.add("Class");
		excludeTags.add("Color space");
		excludeTags.add("Component");
		excludeTags.add("Copyright");
		excludeTags.add("Custom Rendered");
		excludeTags.add("Date/Time Original");
		excludeTags.add("Date/Time Digitized");
		excludeTags.add("Device Mfg Description");
		excludeTags.add("Device manufacturer");
		excludeTags.add("Device model");
		excludeTags.add("File Source");
		excludeTags.add("Green Colorant");
		excludeTags.add("Gain Control");
		excludeTags.add("Green TRC");
		excludeTags.add("Exif Image Height");
		excludeTags.add("Exif Image Width");
		excludeTags.add("Device Model Description");
		excludeTags.add("Lens Information");
		excludeTags.add("Luminance");
		excludeTags.add("Measurement");
		excludeTags.add("Media Black Point");
		excludeTags.add("Media White Point");
		excludeTags.add("Primary Platform");
		excludeTags.add("Profile");
		excludeTags.add("Red Colorant");
		excludeTags.add("Red TRC");
		excludeTags.add("Resolution Unit");
		excludeTags.add("Sensing Method");
		excludeTags.add("Serial Number");
		excludeTags.add("Signature");
		excludeTags.add("Sub-Sec");
		excludeTags.add("Tag Count");
		excludeTags.add("Technology");
		excludeTags.add("Version");
		excludeTags.add("Viewing Conditions");
		excludeTags.add("XYZ values");
		excludeTags.add("White Balance");
		excludeTags.add("Camera Info Array");
		excludeTags.add("Camera Temperature");
		excludeTags.add("Camera Type");
		excludeTags.add("Canon Model ID");
		excludeTags.add("Categories");
		excludeTags.add("F Number");
		excludeTags.add("Flash Guide Number");
		excludeTags.add("Flash Output");
		excludeTags.add("Focal Plane");
		excludeTags.add("Focal Units per mm");
		excludeTags.add("Focus Distance");
		excludeTags.add("Image Number");
		excludeTags.add("Image Unique ID");
		excludeTags.add("Interoperability Index");
		excludeTags.add("Long Focal Length");
		excludeTags.add("Measured EV 2");
		excludeTags.add("My Colors");
		excludeTags.add("Optical Zoom Code");
		excludeTags.add("Orientation");
		excludeTags.add("Related Image Length");
		excludeTags.add("Self Timer 2");
		excludeTags.add("Sequence Number");
		excludeTags.add("Short Focal Length");
		excludeTags.add("Target Aperture");
		excludeTags.add("Target Exposure Time");
		excludeTags.add("Thumbnail");
		excludeTags.add("VRD Offset");
		
		includeTags.add("White Balance Mode");
	}

	public static void main(String[] args) {
		// Folder pictures = new PictureFileUtil().getPictures();
//		Map<String, String> result = new PictureFileUtil().getExifInfo("/Users/jon/workspace_private/pics/web/picture/toby.jpg");
		Map<String, String> result = new PictureFileUtil().getExifInfo("/Users/jon/workspace_private/pics/web/picture/2012-09-15_12-21-04.jpg");
		List<String> keys = new ArrayList<String>();
		keys.addAll(result.keySet());
		Collections.sort(keys);
		for (String key : keys) {
			System.out.println(key + ": " + result.get(key));
		}
		System.out.println("done");
	}

	public Map<String, String> getExifInfo(String filename) {
		try {
			Metadata metadata = ImageMetadataReader.readMetadata(new File(filename));
			Map<String, String> result = new HashMap<String, String>();
			for (Directory directory : metadata.getDirectories()) {
				for (Tag tag : directory.getTags()) {
					if (includeTag(tag.getTagName())) {
						result.put(tag.getTagName(), tag.getDescription());
					} 
//					else {
//						result.put(" - " + tag.getTagName(), tag.getDescription());
//					}
				}
			}
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private boolean includeTag(String tagName) {
		if (!includeTags.contains(tagName)) {
			for (String excludeTag : excludeTags) {
				if (tagName.contains(excludeTag)) {
					return false;
				}
			}
		}
		return true;
	}

	public Folder getPictures() {
		Folder root = new Folder(Constants.PICTURE_PATH, null, null);
		addChildren(root);
		return root;
	}

	private void addChildren(Folder folder) {
		try {
			File dir = new File(folder.getPath());
			String[] files = dir.list();
			for (int i = 0; i < files.length; i++) {
				File file = new File(folder.getPath() + "/" + files[i]);
				if (file.isDirectory()) {
					Folder childFolder = new Folder(file.getName(), folder, null);
					folder.addFileItem(childFolder);
					addChildren(childFolder);
				} else if (file.getName().toLowerCase().endsWith(".jpg")) {
					FileItem picture = new FileItem(file.getName(), folder);
					folder.addFileItem(picture);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
