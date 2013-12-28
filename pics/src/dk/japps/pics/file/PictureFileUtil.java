package dk.japps.pics.file;

import java.io.*;
import java.util.*;

import com.drew.imaging.*;
import com.drew.metadata.*;

import dk.japps.pics.*;

public class PictureFileUtil {
	private static List<String> includeTags = new ArrayList<String>();
	
	static {
		includeTags.add("Date/Time Original");
		includeTags.add("Image Height");
		includeTags.add("Image Width");
		includeTags.add("F-Number");
		includeTags.add("Exposure Time");
		includeTags.add("ISO Speed Ratings");
		includeTags.add("White Balance Mode");
		includeTags.add("White Balance");

		includeTags.add("Lens");
//		includeTags.add("Make");
		includeTags.add("Model");
		
		includeTags.add("Flash");
		includeTags.add("Focal Length 35");
		includeTags.add("Exposure Bias Value");
		includeTags.add("Exposure Mode");
		includeTags.add("Exposure Program");

		includeTags.add("Color Space");
		includeTags.add("Data Precision");

		includeTags.add("Rating");
		includeTags.add("Software");
		includeTags.add("Artist");
		includeTags.add("Copyright Notice");
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
	
	public String getPrev(String file) {
		List<String> files = getFiles(getPictures(), file);
		int indexOfFile = files.indexOf(file);
		if (indexOfFile == 0) {
			return files.get(files.size()-1);
		}
		return files.get(indexOfFile - 1);
	}

	public String getNext(String file) {
		List<String> files = getFiles(getPictures(), file);
		int indexOfFile = files.indexOf(file);
		if (indexOfFile == files.size()-1) {
			return files.get(0);
		}
		return files.get(indexOfFile + 1);
	}
	
	private List<String> getFiles(Folder folder, String file) {
		List<String> files = new ArrayList<String>();
		getFiles(files, folder, file);
		return files;
	}
	
	private void getFiles(List<String> files, Folder folder, String file) {
		if (inFolder(folder, file)) {
			collectFiles(files, folder);
		} else {
			for(FileItem fileItem : folder.getFileItems()) {
				if (fileItem.isFolder()) {
					getFiles(files, (Folder) fileItem, file);
				}
			}
		}
	}
	
	private void collectFiles(List<String> files, Folder folder) {
		for (FileItem fileItem : folder.getFileItems()) {
			if (fileItem.isFile()) {
				files.add(fileItem.getName());
			}
		}
	}

	private boolean inFolder(Folder folder, String file) {
		for(FileItem fileItem : folder.getFileItems()) {
			if (fileItem.isFile() && fileItem.getName().equals(file)) {
				return true;
			}
		}
		return false;
	}
	
	public Map<String, String> getExifInfo(String filename) {
		Map<String, String> result = new LinkedHashMap<String, String>();
		Map<String, String> imageMetaData = getImageMetaData(filename);
		for (String includeTag : includeTags) {
			if (imageMetaData.get(includeTag) != null) {
				result.put(includeTag, imageMetaData.get(includeTag));
			}
		}
		return result;
	}
	
	private Map<String, String> getImageMetaData(String filename) {
		Map<String, String> imageMetaData = new HashMap<String, String>();
		try {
 		Metadata metadata = ImageMetadataReader.readMetadata(new File(filename));
		for (Directory directory : metadata.getDirectories()) {
			for (Tag tag : directory.getTags()) {
				imageMetaData.put(tag.getTagName(), tag.getDescription());
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return imageMetaData;
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
