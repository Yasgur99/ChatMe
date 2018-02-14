package ChatMe.server;

import ChatMe.server.ClientHandler;
import java.net.ServerSocket;

public class ChatMeServer {

	private int portNum;
	private ServerSocket serverSocket;
	private ClientHandler clientHandler;

	public ChatMeServer(int portNum) {
		this.portNum = portNum;
	}

	public void start() {
		// start client handler
		this.clientHandler = new ClientHandler();
		clientHandler.start;

		// handle incoming clients
		serverSocket = new ServerSocket(portNum);
		for(;;) {
			Socket newClientSocket = serverSocket.accept();
			Callable newClientThread =
				new ClientThread(newClientSocket) implements Callable {

				private Socket socket;

				public ClientThread(Socket socket) {
					this.socket = socket;
				}

				@Override
				public call() {
					for(;;;) {
						// do client stuff
					}
				}
			}

			// execute it
			clientHandler.execute(newClientThread);

		} catch {
			// stahp it

		}
	}

}
