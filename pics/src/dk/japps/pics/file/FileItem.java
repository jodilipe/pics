package dk.japps.pics.file;

public class FileItem {
	private String name;
	private Folder parent;

	public FileItem(String name, Folder parent) {
		this.name = name;
		this.parent = parent;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Folder getParent() {
		return parent;
	}
	public void setParent(Folder parent) {
		this.parent = parent;
	}
	public String getPath() {
		if (parent != null) {
			return parent.getPath() + "/" + name;
		}
		return name;
	}
	@Override
	public String toString() {
		return getPath();
	}
	public boolean isFolder() {
		return this instanceof Folder;
	}
	public boolean isFile() {
		return !isFolder();
	}
}
