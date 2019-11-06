package app;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;


public class ServerConnection {
	public static final int PORTO = 8080;
	private BufferedReader in;
	private PrintWriter out;
	private Socket socket;
	
	public void runClient() throws IOException {
		// Utiliza��o de um bloco try-finally para ter a
		// certeza de que o socket � fechado:
		try {
			connectToServer();
			DealWithServer t = new DealWithServer(socket, in);
			t.start();
			sendMessages();
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} finally {
			System.out.println("a fechar...");
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void connectToServer() throws IOException {
		// A passagem de null para getByName() produz o
		// endere�o IP do localhost que permite testar
		// uma aplica��o distribu�da numa �nica m�quina
		InetAddress endereco = InetAddress.getByName(null);
		System.out.println("Endere�o = " + endereco);
		socket = new Socket(endereco, PORTO);

		System.out.println("Socket = " + socket);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		// O output � autom�ticamente enviado para PrintWriter:
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
				socket.getOutputStream())), true);
	}

	private void sendMessages() throws IOException {
		for (int i = 0; i < 10; i++) {
			out.println("Ol� " + i);
//			String str = in.readLine();
			System.out.println("Mandei: " + "Ol� " + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		out.println("FIM");
	}
}
