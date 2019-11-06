package app;
import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainWindow window = new MainWindow("LogoFinder v.1");
				window.open();
//				ServerConnection con = new ServerConnection();
//				try {
//					con.runClient();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}	
			}
		});

	}

}
