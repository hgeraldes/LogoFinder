package app;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class Images implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String folder;
	private ArrayList<ImageFile> files;
	
	public Images(int id, String folder, File[] files) {
		super();
		this.id = id;
		this.folder = folder;
		this.files = new ArrayList<ImageFile>();
		for(File f: files)
			this.files.add(new ImageFile(this.files.size(), f, null));
	}
	
	public void addImage(int id, String folder, File file) {
		this.files.add(new ImageFile(id, file, null));
	}

	public String getFolder() {
		return folder;
	}

	public int getId() {
		return id;
	}

	public ArrayList<ImageFile> getFiles() {
		return files;
	}
}

