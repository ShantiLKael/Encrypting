package messender.server;

import java.net.BindException;

public class TestSession
{

	public static void main(String[] args)
	{
		Session c1 = null, c2 = null, c3 = null;
		try
		{
			c2 = new Session("Medhi", 9000);
			c1 = new Session("Justine", 6000, false);
			c3 = new Session("Ali", 9001, false);
		} catch (BindException e) { System.out.println("Clients not created"); }


		c2.addFriend(c1);
		c2.addFriend(c3);

		
		while (true)
		{
			c2.receiveMessage(); 
		}
	}

}
