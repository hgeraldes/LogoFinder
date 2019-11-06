package appGUI;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class JImagePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel labelImg;

	public JImagePanel() {
		initialize();
		addContent();
	}

	private void initialize() {
		setLayout(new BorderLayout());
		labelImg = new JLabel("");
	}	
	
	private void addContent() {
		JScrollPane scrollPane = new JScrollPane(labelImg);
		add(scrollPane, BorderLayout.CENTER);
	}


	
	public void setImgIcon(ImageIcon img) {
		labelImg.setIcon(img);
	}
	
}
