package dk.japps.pics.file;

import java.util.*;

public class Folder extends FileItem {
	private List<FileItem> fileItems;
	
	public Folder(String name, Folder parent, List<FileItem> fileItems) {
		super(name, parent);
		this.fileItems = fileItems;
	}
	public List<FileItem> getFileItems() {
		if (fileItems == null) {
			fileItems = new ArrayList<FileItem>();
		}
		return fileItems;
	}
	public void setFileItems(List<FileItem> fileItems) {
		this.fileItems = fileItems;
	}
	public void addFileItem(FileItem fileItem) {
		getFileItems().add(fileItem);
	}
}
