package messender.server;

import java.net.ServerSocket;
import java.net.Socket;

public class Client2
{

	public static void main(String[] args)
	{
		Client c2 = new Client("Medhi", 9000);

		c2.receiveMessage();
	}

}
