package dk.japps.pics;

import java.io.*;
import java.util.*;

import dk.japps.pics.file.*;

public class PicsLogic {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new PicsLogic().syncPictureCopies();
		System.out.println(System.currentTimeMillis()-start + "ms");
	}
	
	public List<String> getThumbnails(String folderName) {
		List<String> thumbnails = new ArrayList<String>();
		Folder folder = getFolder(folderName);
		if (folder != null) {
			for (FileItem fileItem : folder.getFileItems()) {
				if (fileItem.isFile()) {
					thumbnails.add(fileItem.getName());
				}
			}
		}
		return thumbnails;
	}
	
	public void syncPictureCopies() {
		updatePictureCopies(Constants.THUMBNAIL_PATH, Constants.THUMBNAIL_SIZE, Constants.THUMBNAIL_QUALITY, Constants.REFRESH_THUMBS);
		updatePictureCopies(Constants.PREVIEW_PATH, Constants.PREVIEW_SIZE, Constants.PREVIEW_QUALITY, Constants.REFRESH_PREVIEWS);
	}
		
	public String getOriginalRelativePath(String fileName) {
		return getPath(fileName, Constants.PICTURE_PATH_RELATIVE);
	}
	
	public String getOriginalPath(String fileName) {
		return getPath(fileName, Constants.PICTURE_PATH);
	}

	private String getPath(String fileName, String rootPath) {
		List<FileItem> pictureFileItems = new ArrayList<FileItem>();
		Folder pictures = new PictureFileUtil().getPictures();
		pictures.setName(rootPath);
		getPictureFileItems(pictureFileItems, pictures);
		for (FileItem fileItem : pictureFileItems) {
			if (fileItem.getName().equals(fileName)) {
				return fileItem.getPath();
			}
		}
		return "";
	}
	
	public Folder getFolder(String folderName) {
		for (Folder folder : getFolders()) {
			if (folder.getName().equals(folderName)) {
				return folder;
			}
		}
		return null;
	}
	
	public List<Folder> getFolders() {
		List<Folder> folders = new ArrayList<Folder>();
		getPictureFolders(folders, new PictureFileUtil().getPictures());
		if (Constants.FOLDER_SORT_ORDER != null) {
			StringTokenizer st = new StringTokenizer(Constants.FOLDER_SORT_ORDER, ",");
			final List<String> sortOrderList = new ArrayList<String>();
			while (st.hasMoreTokens()) {
				sortOrderList.add(st.nextToken());
			}
			Collections.sort(folders, new Comparator<Folder>() {
				@Override
				public int compare(Folder folder1, Folder folder2) {
					int indexOfFolder1 = sortOrderList.indexOf(folder1.getName());
					int indexOfFolder2 = sortOrderList.indexOf(folder2.getName());
					if (indexOfFolder1 < indexOfFolder2) {
						return -1;
					} else if (indexOfFolder1 > indexOfFolder2) {
						return 1;
					}
					return 0;
				}
			});
		}
		return folders;
	}

	private void updatePictureCopies(String pictureCopyPath, int pictureCopySize, float pictureCopyQuality, boolean forceDelete) {
		deleteGhostPictureCopies(pictureCopyPath, forceDelete);
		createMissingPictureCopies(new PictureFileUtil().getPictures(), pictureCopyPath, pictureCopySize, pictureCopyQuality);
	}

	private List<String> getPictureCopies(String pictureCopyPath) {
		List<String> pictureCopies = new ArrayList<String>();
		String[] pictureCopiesArray = new File(pictureCopyPath).list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".jpg");
			}
		});
		if (pictureCopiesArray != null) {
			for (int i = 0; i < pictureCopiesArray.length; i++) {
				pictureCopies.add(pictureCopiesArray[i]);
			}
		}
		return pictureCopies;
	}

	private void createMissingPictureCopies(Folder pictures, String pictureCopyPath, int pictureCopySize, double pictureCopyQuality) {
		for (FileItem fileItem : pictures.getFileItems()) {
			if (fileItem.isFile() && !pictureCopyExists(fileItem, pictureCopyPath)) {
				ImageCopyUtil.createPictureCopy(fileItem.getPath(), pictureCopyPath + "/" + fileItem.getName(), pictureCopySize, pictureCopyQuality);
			} else if (fileItem.isFolder()) {
				createMissingPictureCopies((Folder) fileItem, pictureCopyPath, pictureCopySize, pictureCopyQuality);
			}
		}
	}

	private boolean pictureCopyExists(FileItem fileItem, String pictureCopyPath) {
		List<String> pictureCopies = getPictureCopies(pictureCopyPath);
		for (String pictureCopy : pictureCopies) {
			if (fileItem.getName().equals(pictureCopy)) {
				return true;
			}
		}
		return false;
	}

	private void deleteGhostPictureCopies(String pictureCopyPath, boolean forceDelete) {
		List<String> pictureCopies = getPictureCopies(pictureCopyPath);
		for (String pictureCopy : pictureCopies) {
			File thumbnail = new File(pictureCopyPath + "/" + pictureCopy);
			if (forceDelete || !pictureExists(thumbnail)) {
				thumbnail.delete();
			}
		}
	}

	private boolean pictureExists(File thumbnail) {
		List<FileItem> pictureFileItems = new ArrayList<FileItem>();
		getPictureFileItems(pictureFileItems, new PictureFileUtil().getPictures());
		for (FileItem fileItem : pictureFileItems) {
			if (fileItem.getName().equals(thumbnail.getName())) {
				return true;
			}
		}
		return false;
	}

	private void getPictureFileItems(List<FileItem> fileItems, Folder folder) {
		for (FileItem fileItem : folder.getFileItems()) {
			if (fileItem.isFile()) {
				fileItems.add(fileItem);
			} else if (fileItem.isFolder()) {
				getPictureFileItems(fileItems, (Folder) fileItem);
			}
		}
	}

	private void getPictureFolders(List<Folder> folders, Folder folder) {
		for (FileItem fileItem : folder.getFileItems()) {
			if (fileItem.isFolder()) {
				folders.add((Folder) fileItem);
				getPictureFolders(folders, (Folder) fileItem);
			}
		}
	}
}
