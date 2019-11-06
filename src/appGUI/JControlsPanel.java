package appGUI;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class JControlsPanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtPathFolder;
	private JTextField txtPathSubImage;
	private JButton btnProcurar;
	private JPanel panelTextFields;
	private JPanel panelButtons;
	private JButton btnPasta;
	private JButton btnSubimagem;
	
	private ChooserListener chooseListener;
	
	private PanelListener panelListener;
	
	public JControlsPanel() {		
		initialize();
		addContent();
		setAddActionListeners();
	}

	private void setAddActionListeners() {
		btnPasta.addActionListener(this);		
		btnSubimagem.addActionListener(this);
		btnProcurar.addActionListener(this);
	}

	private void initialize() {
		setLayout(new BorderLayout());	
		btnProcurar = new JButton("Procurar");
		panelTextFields = new JPanel(new GridLayout(2,1));
		panelButtons = new JPanel(new GridLayout(2,1));
		btnPasta = new JButton("Pasta");
		btnSubimagem = new JButton("Subimagem");
		txtPathFolder = new JTextField();
		txtPathSubImage = new JTextField();
	}	
	
	private void addContent() {
		panelButtons.setPreferredSize(new Dimension(150,0));
		add(btnProcurar, BorderLayout.SOUTH);
		add(panelTextFields, BorderLayout.CENTER);
		add(panelButtons, BorderLayout.EAST);
		panelTextFields.add(txtPathFolder);
		panelTextFields.add(txtPathSubImage);
		panelButtons.add(btnPasta);
		panelButtons.add(btnSubimagem);
	}


	
	public String getTxtPathFolderText() {
		return txtPathFolder.getText();
	}

	public void setTxtPathFolder(String text) {
		this.txtPathFolder.setText(text);
	}

	public String getTxtPathSubImageText() {
		return txtPathSubImage.getText();
	}

	public void setTxtPathSubImageText(String text) {
		this.txtPathSubImage.setText(text);
	}
	
	
	public void setChooserChoice(ChooserListener chooseListener){
		this.chooseListener = chooseListener;
	}

	public void setPanelListener(PanelListener panelListener) {
		this.panelListener = panelListener;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton)e.getSource();
		
		if(clicked == btnPasta) {
			if(chooseListener != null) {
				String selected = selectFolder();
				chooseListener.chooserChoice(selected, 0);
				
				PanelEvent ev = new PanelEvent(this, selected);
				if(panelListener != null) {
					panelListener.panelEventOccurred(ev);
				}
			}
		} else if(clicked == btnSubimagem) {
			if(chooseListener != null) {
				chooseListener.chooserChoice(selectFile(), 1);
			}
		} else if(clicked == btnProcurar) {
			if(chooseListener != null) {
				chooseListener.chooserChoice("", 2);
			}
		}
		
	}
	
	private String selectFolder() {
		JFileChooser path = new JFileChooser(".");
		path.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if(path.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			return path.getSelectedFile().getAbsolutePath();
		}
		return "";
	}
	
	private String selectFile() {
		String strPath = ".";
		if(this.txtPathFolder.getText().length() > 0)
			strPath = this.txtPathFolder.getText();
		JFileChooser path = new JFileChooser(strPath);
		path.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if(path.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			return path.getSelectedFile().getAbsolutePath();
		}
		return "";
	}
}
