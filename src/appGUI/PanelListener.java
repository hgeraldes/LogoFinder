package appGUI;

import java.util.EventListener;

public interface PanelListener extends EventListener {
	public void panelEventOccurred(PanelEvent e);
}
