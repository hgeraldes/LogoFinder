package Client;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
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
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ActionSearch implements ActionListener {
	private ImageIcon img;
	private JTextField jtfPathFolder;
	private JTextField jtfPathSubImage;
	private JLabel labelImg;
	private JLabel label;
	private File[] file;
	
	public ActionSearch(ImageIcon img, JTextField jtfPathFolder, JTextField jtfPathSubImage, JLabel labelImg, JLabel label, File[] file) {
		super();
		this.img = img;
		this.jtfPathFolder = jtfPathFolder;
		this.jtfPathSubImage = jtfPathSubImage;
		this.file = new File(jtfPathFolder.getText()).listFiles(new FileFilter() {
		     public boolean accept(File f) { 
		    	 return f.getName().endsWith("png");
		     }
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 try {
			 //read image
			 BufferedImage image = ImageIO.read(file[0]);
//			 System.out.println("Image " + image.getWidth() + " x "
//			 + image.getHeight());
		 
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
