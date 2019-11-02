package Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JTextField;

public class ActionFile implements ActionListener {
	private JTextField jtfPath;
	private JTextField jtfGeneric;
	

	public ActionFile(JTextField jtfPathFolder, JTextField path) {
		super();
		this.jtfGeneric = jtfPathFolder;
		this.jtfPath = path;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String strPath = ".";
		if(this.jtfPath.getText() != "")
			strPath = this.jtfPath.getText();
		JFileChooser path = new JFileChooser(strPath);
		path.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if(path.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			this.jtfGeneric.setText(path.getSelectedFile().getAbsolutePath());
		}
	}

}
