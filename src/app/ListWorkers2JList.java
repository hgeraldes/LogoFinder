package app;

public class ListWorkers2JList{
	int id;
	String name;
	public ListWorkers2JList(int id, String name) {
		this.id = id;
		this.name = name;
	}
	@Override
	public String toString() {
		return name;
	}
	
}
