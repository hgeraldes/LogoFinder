package app;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;

public class ImageFile {

	private int id;
	private File file;
	private ArrayList<Point> points;
	
	public ImageFile(int id, File file, ArrayList<Point> points) {
		super();
		this.id = id;
		this.file = file;
		this.points = points;
	}
	
	public int getId() {
		return id;
	}
	
	public ArrayList<Point> getPoints() {
		return points;
	}
	
	public int getTotalPoints() {
		if (points != null)
			return points.size();
		else
			return 0;
	}

	public File getFile() {
		return file;
	}

	@Override
	public String toString() {
		return "id=" + id + ", file=" + file.getName() + ", points=" + getTotalPoints();
	}
	
	
}
