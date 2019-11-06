package appGUI;

import java.util.EventObject;

public class PanelEvent extends EventObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String text;

	public PanelEvent(Object source) {
		super(source);
	}
	public PanelEvent(Object source, String text) {
		super(source);
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
}
