package Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
	JTextField jtfPathFolder;
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
		jtfPathFolder = new JTextField();
		// para que o botao de fechar a janela termine a aplicacao
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	private void AddContent() {

		frame.setLayout(new BorderLayout());
		
		JLabel label = new JLabel();
		frame.add(label, BorderLayout.NORTH);

		JList<String[]> listWest = new JList<>();
		listWest.setPreferredSize(new Dimension(150,0));
		frame.add(listWest, BorderLayout.WEST);
		
		JList<String[]> listEast = new JList<>();
		listEast.setPreferredSize(new Dimension(150,0));
		frame.add(listEast, BorderLayout.EAST);
		
		JLabel labelImg = new JLabel("");
		JScrollPane scrollPane = new JScrollPane(labelImg);
		frame.add(scrollPane, BorderLayout.CENTER);
		
		JPanel panelSouth = new JPanel();
		panelSouth.setBackground(Color.lightGray);
		
		// O south tem um BorderLayout X
		// X.South terá o botão procurar
		// X.center terá um painel P1
		// X.right terá um painel P2
		// P1 terá um gridlayout
		// P2 terá um gridlayout
		
		panelSouth.setLayout(new BorderLayout());
		JButton btnProcurar = new JButton("Procurar");
		panelSouth.add(btnProcurar, BorderLayout.SOUTH);

		JPanel panelPaths = new JPanel(new GridLayout(2,1));
		panelSouth.add(panelPaths, BorderLayout.CENTER);
		
		JPanel panelButtons = new JPanel(new GridLayout(2,1));
		panelButtons.setPreferredSize(new Dimension(150,0));
		panelSouth.add(panelButtons, BorderLayout.EAST);

		
		JTextField jtfPathSubImage = new JTextField();
		panelPaths.add(jtfPathFolder);
		panelPaths.add(jtfPathSubImage);

		JButton buttonPasta = new JButton("Pasta");
		JButton buttonSubimagem = new JButton("Subimagem");
		panelButtons.add(buttonPasta);
		panelButtons.add(buttonSubimagem);


		buttonPasta.addActionListener(new ActionFolder(jtfPathFolder));
				
		buttonSubimagem.addActionListener(new ActionFile(jtfPathSubImage, jtfPathFolder));
		
		File[] files;
		String path = "./images";
		ImageIcon img;
		int pos = 0;
		
		files = new File(path).listFiles(new FileFilter() {
		     public boolean accept(File f) { 
		    	 return f.getName().endsWith("png");
		     }
		});
		
		img = new ImageIcon(files[pos].getPath());
		labelImg.setIcon(img);
		label.setText(files[pos].getName());
		
		
		
		frame.add(panelSouth, BorderLayout.SOUTH);
		
	}
	
	 
}
