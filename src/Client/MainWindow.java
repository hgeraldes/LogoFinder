package Client;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class MainWindow {
	private JLabel label;
	
	private JTextField jtfPathFolder;
	private JTextField jtfPathSubImage;
	
	private JList<String[]> listWest;
	private JList<String[]> listEast;

	private JLabel labelImg;
	
	private String title;
	private JFrame frame;
	private ImageIcon img;
	
	private File[] file;

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
		listWest = new JList<>();
		listEast = new JList<>();
		labelImg = new JLabel("");
		jtfPathSubImage = new JTextField();
		// para que o botao de fechar a janela termine a aplicacao
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	private void AddContent() {
		frame.setLayout(new BorderLayout());
		
		label = new JLabel();
		frame.add(label, BorderLayout.NORTH);

		listWest.setPreferredSize(new Dimension(150,0));
		frame.add(listWest, BorderLayout.WEST);
		
		listEast.setPreferredSize(new Dimension(150,0));
		frame.add(listEast, BorderLayout.EAST);
		
		JScrollPane scrollPane = new JScrollPane(labelImg);
		frame.add(scrollPane, BorderLayout.CENTER);
		
		//********************
		JPanel panelSouth = new JPanel();
		panelSouth.setBackground(Color.lightGray);
		
		panelSouth.setLayout(new BorderLayout());
		JButton btnProcurar = new JButton("Procurar");
		panelSouth.add(btnProcurar, BorderLayout.SOUTH);

		JPanel panelPaths = new JPanel(new GridLayout(2,1));
		panelSouth.add(panelPaths, BorderLayout.CENTER);
		
		JPanel panelButtons = new JPanel(new GridLayout(2,1));
		panelButtons.setPreferredSize(new Dimension(150,0));
		panelSouth.add(panelButtons, BorderLayout.EAST);

		//********************
		
		panelPaths.add(jtfPathFolder);
		panelPaths.add(jtfPathSubImage);

		JButton buttonPasta = new JButton("Pasta");
		JButton buttonSubimagem = new JButton("Subimagem");
		
		panelButtons.add(buttonPasta);
		panelButtons.add(buttonSubimagem);

		//********************
		buttonPasta.addActionListener(new ActionFolder(jtfPathFolder, file, img, labelImg, label));		
		buttonSubimagem.addActionListener(new ActionFile(jtfPathSubImage, jtfPathFolder));
		btnProcurar.addActionListener(new ActionSearch(img, jtfPathSubImage, jtfPathFolder, labelImg, label, file));
		
		
		frame.add(panelSouth, BorderLayout.SOUTH);
		
	}
	

}
