package ChatMe.server;

import ChatMe.server.ClientHandler;
import java.net.ServerSocket;

public class ChatMeServer {

	private int portNum;
	private ServerSocket serverSocket;
	private ClientHandler clientHandler

	public ChatMeServer(int portNum) {
		this.portNum = portNum;
	}

	public void start() {
		// start client handler
		clientHandler = new ClientHandler();
		clientHandler.start;

		// handle incoming clients
		serverSocket = new ServerSocket(portNum);
		for(;;) {
			Socket newClientSocket = serverSocket.accept();
			Thread clientThread = new ClientThread(newClientSocket) {

				private Socket socket;

				public ClientThread(Socket socket) {
					this.socket = socket;
				}

				@Override
				public void Run


			}

			// execute it

		} catch {
			// stahp it

		}
	}

}
