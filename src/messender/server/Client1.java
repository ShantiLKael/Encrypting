package messender.server;

import java.net.ServerSocket;
import java.net.Socket;

public class Client1
{

	public static void main(String[] args)
	{
		Client c2 = new Client("Medhi", 9000);
		Client c1 = new Client("Justine", 6000, false);
		Client c3 = new Client("Ali", 9001, false);

		c2.addFriend(c1);
		c2.addFriend(c3);

		
		while (true)
		{
			c2.receiveMessage(); 
		}
	}

}
