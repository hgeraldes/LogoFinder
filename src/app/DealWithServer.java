package app;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class DealWithServer extends Thread {

	private BufferedReader in;
	private Socket socket;
	
	public DealWithServer(Socket socket, BufferedReader in) {
		super();
		this.in = in;
		this.setSocket(socket);
	}
	
	@Override
	public void run() {
		try {
			while (true) {
				String str = in.readLine();
				if (str.equals("FIM"))
					break;
				System.out.println("Veio do server: " + str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
		}
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
}
