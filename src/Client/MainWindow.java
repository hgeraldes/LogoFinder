package Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class MainWindow {
	private String title;
	private JFrame frame;

	public MainWindow(String title) {
		super();
		this.title = title;
		Initialize();
		AddContent();
	}
	
	public void open() {
		// para que a janela se redimensione de forma a ter todo o seu conteudo visivel
		frame.pack();
		// definir uma dimensão inicial standard
		frame.setSize(1024, 760);
		// centrar no ecran
		frame.setLocationRelativeTo(null);
		// para abrir a janela (torna-la visivel)
		frame.setVisible(true);
	}
	
	private void Initialize() {
		frame = new JFrame(this.title);
		// para que o botao de fechar a janela termine a aplicacao
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	private void AddContent() {

		frame.setLayout(new BorderLayout());
		
		JLabel label = new JLabel("imageTitle");
		frame.add(label, BorderLayout.NORTH);

		JList<String[]> listaEsquerda = new JList<>();
		listaEsquerda.setPreferredSize(new Dimension(200,100));
		frame.add(listaEsquerda, BorderLayout.WEST);
		
		JList<String[]> listaDireita = new JList<>();
		listaDireita.setPreferredSize(new Dimension(200,100));
		frame.add(listaDireita, BorderLayout.EAST);
		
		JLabel labelImg = new JLabel("");
		JScrollPane pane = new JScrollPane(labelImg);
		frame.add(pane, BorderLayout.CENTER);
		
		JPanel panelSouth = new JPanel();
		panelSouth.setBackground(Color.lightGray);
		
		// O south tem um BorderLayout X
		// X.South terá o botão procurar
		// X.center terá um painel P1
		// X.right terá um painel P2
		// P1 terá um gridlayout
		// P2 terá um gridlayout
		
		panelSouth.setLayout(new BorderLayout());
		JButton procurar = new JButton("Procurar");
		panelSouth.add(procurar, BorderLayout.SOUTH);
		
		JPanel panelText = new JPanel(new GridLayout(2,1));
		panelSouth.add(panelText, BorderLayout.CENTER);
		JPanel panelButtons = new JPanel(new GridLayout(2,1));
		panelSouth.add(panelButtons, BorderLayout.EAST);
		
		JTextField pathFolder = new JTextField();
		JTextField pathSubImg = new JTextField();
		panelText.add(pathFolder);
		panelText.add(pathSubImg);
		
		JButton btnFolder = new JButton("Pasta");
		JButton btnSubImg = new JButton("Logo");
		panelButtons.add(btnFolder);
		panelButtons.add(btnSubImg);
	 

		btnFolder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser path = new JFileChooser(".");
				path.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				if(path.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					pathFolder.setText(path.getSelectedFile().getAbsolutePath());
				}
			}
		});
		btnSubImg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser path = new JFileChooser(pathFolder.getText());
				path.setFileSelectionMode(JFileChooser.FILES_ONLY);
				
				if(path.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					pathSubImg.setText(path.getSelectedFile().getName());
				}
			}
		});
		
		File[] files;
		String path = "C:\\Temp";
		ImageIcon img;
		int pos = 0;
		
		files = new File(path).listFiles(new FileFilter() {
		     public boolean accept(File f) { 
		    	 return f.getName().endsWith("jpg");
		     }
		});
		
		img = new ImageIcon(files[pos].getPath());
		labelImg.setIcon(img);
		label.setText(files[pos].getName());
		
		
		
		frame.add(panelSouth, BorderLayout.SOUTH);
		
	}
	
	 
}
