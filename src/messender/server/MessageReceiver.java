package messender.server;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.stream.Collectors;

class MessageReceiver implements Runnable
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
			String[] fullMessage = in.lines().collect(Collectors.joining()).split(":");
			if ( fullMessage.length == 2 )
			{
				String[] senderInfo = fullMessage[0].split("@");
				Session sender = new Session(senderInfo[0], Integer.parseInt(senderInfo[1]), false);

				String message = sender.decrypt(fullMessage[1]);
				this.session.addMessageHistory(sender.getName(), message );
			}

			System.out.println(session.getHistMessages());
			
		
			servSocket.close();
			in.close();
			out.close();
		}
		catch (IOException e) { e.printStackTrace(); }
	}

}
