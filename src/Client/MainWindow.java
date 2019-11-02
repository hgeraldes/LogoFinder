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
	JTextField jtfPathFolder;
	private String title;
	private JFrame frame;
	private ImageIcon img;

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
		int pos = 0;
		
		files = new File(path).listFiles(new FileFilter() {
		     public boolean accept(File f) { 
		    	 return f.getName().endsWith("png");
		     }
		});
		
		img = new ImageIcon(files[pos].getPath());
		labelImg.setIcon(img);
		label.setText(files[pos].getName());
		
		
		btnProcurar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 try {
					 //read image
					 BufferedImage image = ImageIO.read(new File(files[0].getPath()));
					 System.out.println("Image " + image.getWidth() + " x "
					 + image.getHeight());
					 
					 //------#######-------
					 //desenhar rectângulo sobre imagem
					 Graphics2D g2d = image.createGraphics();
					 g2d.setColor(Color.RED);
					 BufferedImage subimage = ImageIO.read(new File(jtfPathSubImage.getText()));
					 ArrayList<Point> p = new ArrayList<Point>();
					 p = match(subimage,image);
					 float thickness = 7;
					 for (Point t : p) {
						 g2d.setStroke(new BasicStroke(thickness));
						 g2d.drawRect(t.x, t.y, subimage.getWidth(), subimage.getHeight());
					 }
					 g2d.dispose();
					 
					 //------#######-------
					 // To serialize
					 // converter BufferedImage para byte array
					 ByteArrayOutputStream baos = new ByteArrayOutputStream();
					 ImageIO.write(image, "png", baos);
					 baos.flush();
					 byte[] imgTmp = baos.toByteArray();
					 baos.close();

					 // converte byte array para BufferedImage
					 InputStream in = new ByteArrayInputStream(imgTmp);
					 BufferedImage bImageFromConvert = ImageIO.read(in);
					 ImageIO.write(bImageFromConvert, "png", new File(jtfPathFolder.getText() + "/out.png"));
					 
					 img = new ImageIcon(jtfPathFolder.getText() + "/out.png");
					 labelImg.setIcon(img);
					 label.setText(jtfPathFolder.getText() + "/out.png");

				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		frame.add(panelSouth, BorderLayout.SOUTH);
		
	}
	
	public static ArrayList<Point> match(BufferedImage subimage, BufferedImage image) {
		ArrayList<Point> p = new ArrayList<Point>();
        for (int i = 0; i <= image.getWidth() - subimage.getWidth(); i++) {
            check_subimage:
            for (int j = 0; j <= image.getHeight() - subimage.getHeight(); j++) {
                for (int ii = 0; ii < subimage.getWidth(); ii++) {
                    for (int jj = 0; jj < subimage.getHeight(); jj++) {
                        if (subimage.getRGB(ii, jj) != image.getRGB(i + ii, j + jj)) {
                            continue check_subimage;
                        }
                    }
                }
                p.add(new Point(i, j));
            }
        }
        return p;
    }
}
