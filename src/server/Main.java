package ChatMe.server;

import ChatMe.server.ChatMeServer;

public class Main {

	public static void main(String[] args) {

		ChatMeServer myserver = new ChatMeServer(1555);
		myserver.start();

	}
}
