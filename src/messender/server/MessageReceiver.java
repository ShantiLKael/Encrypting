package messender.server;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.stream.Collectors;

class MessageReceiver extends Thread
{
	private Session session;
	private BufferedReader in;
	private PrintWriter out;
	private Socket servSocket;

	MessageReceiver ( Session session ) 
	{
		this.session  = session;

		try
		{
			this.servSocket	= this.session.getServSocket().accept(); // waiting for client
		}
		catch ( UnknownHostException e ) { e.printStackTrace(); }
		catch ( IOException e ) { e.printStackTrace(); }

		try 
		{
			this.in  = new BufferedReader( new InputStreamReader( servSocket.getInputStream()) );
			this.out = new PrintWriter( servSocket.getOutputStream(), true );
		}
		catch ( IOException e ) { e.printStackTrace(); }
	}

	// We suppose that there isnt a recursive thread creation
	/*public void join()
	{
		int nbThread =  this.activeCount();
		Thread[] arThread = new Thread[nbThread];
		enumerate(arThread);

		for (Thread t : arThread )
			t.join();
	}*/

	@Override
	public void run() 
	{
		System.out.println("Connexion d'un client");

		try 
		{
			// FullMessage => name @ port : message
			String[] fullMessage = in.lines().collect(Collectors.joining()).split(":");
			if ( fullMessage.length == 2 )
			{
				String[] senderInfo = fullMessage[0].split("@");
				String senderName = senderInfo[0];
				int    port = Integer.parseInt(senderInfo[1]);
				String message = Client.decrypt(senderName, port, fullMessage[1]);

				this.session.addMessageHistory(senderName, message );
			}

			System.out.println(session.getHistMessages()); // TODO remove delete
			
			servSocket.close();
			in.close();
			out.close();
		}
		catch (IOException e) { e.printStackTrace(); }
	}

}
