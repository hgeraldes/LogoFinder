package app;

public class ListImages2JList{
	int id;
	String name;
	public ListImages2JList(int id, String name) {
		this.id = id;
		this.name = name;
	}
	@Override
	public String toString() {
		return name;
	}
	
}
