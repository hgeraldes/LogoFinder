package Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ActionFolder implements ActionListener{

	private JTextField jtfGeneric;
	private File[] file;
	private ImageIcon img;
	private JLabel labelImg;
	private JLabel label;
	

	public ActionFolder(JTextField jtfPathFolder, File[] file, ImageIcon img, JLabel labelImg, JLabel label) {
		super();
		this.jtfGeneric = jtfPathFolder;
		this.file = file;
		this.img = img;
		this.labelImg = labelImg;
		this.label = label;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser path = new JFileChooser(".");
		path.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if(path.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			jtfGeneric.setText(path.getSelectedFile().getAbsolutePath());

			file = new File(jtfGeneric.getText()).listFiles(new FileFilter() {
			     public boolean accept(File f) { 
			    	 return f.getName().endsWith("png");
			     }
			});
			img = new ImageIcon(file[0].getPath());
			labelImg.setIcon(img);
			label.setText(file[0].getName());
		}
	}

}
