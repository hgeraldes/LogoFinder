package app;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import appGUI.ChooserListener;
import appGUI.ImageListener;
import appGUI.JControlsPanel;
import appGUI.JImagePanel;
import appGUI.JListImagesPanel;
import appGUI.JListWorkersPanel;
import appGUI.PanelEvent;
import appGUI.PanelListener;

public class MainWindow extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel label;
	private JListWorkersPanel workers;
	private JListImagesPanel listImages;
	private JImagePanel imageViewer;
	private JControlsPanel controls;
	
	private ImageIcon img;
		
	private Images imagesData;
	private File[] file;

	
	public MainWindow(String title) {
		super("LogoFinder App");
		//separar os componentes e manter uma arquitectura limpa
		Initialize();
		AddContent();
		AddListeners();
	}
	
	private void Initialize() {
		setLayout(new BorderLayout());
		// para que o botao de fechar a janela termine a aplicacao
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		label = new JLabel();
		workers = new JListWorkersPanel("Workers");
		listImages = new JListImagesPanel("Images");
		imageViewer = new JImagePanel();
		controls = new JControlsPanel();
	}
	
	private void AddContent() {
		add(label, BorderLayout.NORTH);
		add(workers, BorderLayout.WEST);
		add(listImages, BorderLayout.EAST);
		add(imageViewer, BorderLayout.CENTER);
		add(controls, BorderLayout.SOUTH);
	}
	
	private void AddListeners() {
		controls.setChooserChoice(new ChooserListener() {
			@Override
			public void chooserChoice(String text, int type) {
				if (type == 0) {
					controls.setTxtPathFolder(text);
					fillOriginalImages();
				}
				else if (type == 1) {
					controls.setTxtPathSubImageText(text);
				}
				else if (type == 2) {
					performSearch(controls.getTxtPathFolderText()+"\\"+label.getText());
				}
			}
		});
		listImages.setImageChoice(new ImageListener() {
			@Override
			public void imageChoice(ListImages2JList listImages, int type) {
				label.setText(listImages.toString());
				imageViewer.setImgIcon(new ImageIcon(controls.getTxtPathFolderText()+"\\"+listImages.toString()));
			}
		});
		controls.setPanelListener(new PanelListener() {
			@Override
			public void panelEventOccurred(PanelEvent e) {
				System.out.println("Evento disparado no painel:" + e.getText() + " : " + e.getSource().toString()); 
			}
		});
	}
	
	public void open() {
		// para que a janela se redimensione de forma a ter todo o seu conteudo visivel
		pack();
		// definir uma dimensão inicial standard
		setSize(1024, 760);
		// centrar no ecran
		setLocationRelativeTo(null);
		// para abrir a janela (torna-la visivel)
		setVisible(true);
	}	

	private void fillOriginalImages() {
		String folder = controls.getTxtPathFolderText();
		file = new File(folder).listFiles(new FileFilter() {
		     public boolean accept(File f) { 
		    	 return f.getName().endsWith("png");
		     }
		});
		if(file.length > 0) {
			imagesData = new Images(1, folder, file);
		}

		DefaultListModel<ListImages2JList> model = new DefaultListModel<ListImages2JList>();
		for(ImageFile i: imagesData.getFiles()) {
			model.addElement(new ListImages2JList(i.getId(), i.getFile().getName()));
		}
		listImages.setModelGeneric(model);
	}
	
	private void performSearch(String fileName) {
		 try {
			 System.out.println(fileName);
			 File tmp = new File(fileName);
			 //read image
			 BufferedImage image = ImageIO.read(tmp);
			 //------#######-------
			 //desenhar os rectângulos na imagem
			 Graphics2D g2d = image.createGraphics();
			 g2d.setColor(Color.RED);
			 BufferedImage subimage = ImageIO.read(new File(controls.getTxtPathSubImageText()));
			 ArrayList<Point> p = new ArrayList<Point>();
			 p = match(subimage,image);
			 float thickness = 5;
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
			 ImageIO.write(bImageFromConvert, "png", new File(controls.getTxtPathFolderText() + "/out.png"));
			 
			 img = new ImageIcon(controls.getTxtPathFolderText() + "/out.png");
			 imageViewer.setImgIcon(img);
			 label.setText(controls.getTxtPathFolderText() + "/out.png");

		} catch (IOException e1) {
			e1.printStackTrace();
		}
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
