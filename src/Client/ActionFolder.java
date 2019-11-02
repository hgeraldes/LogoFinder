package Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JTextField;

public class ActionFolder implements ActionListener{

	private JTextField jtfGeneric;
	

	public ActionFolder(JTextField jtfPathFolder) {
		super();
		this.jtfGeneric = jtfPathFolder;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser path = new JFileChooser(".");
		path.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if(path.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			jtfGeneric.setText(path.getSelectedFile().getAbsolutePath());
		}
	}

}
