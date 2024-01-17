package messender.server;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.stream.Collectors;

class ClientManager implements Runnable
{
	private Client recipient;
	private BufferedReader in;
	private PrintWriter out;
	private Socket servSocket;

	ClientManager ( Client recipient ) 
	{
		this.recipient  = recipient;

		try
		{
			this.servSocket	= this.recipient.getServSocket().accept(); // waiting for client
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
				Client sender = new Client(senderInfo[0], Integer.parseInt(senderInfo[1]), false);

				String message = sender.decrypt(fullMessage[1]);
				this.recipient.addMessageHistory(sender.getName(), message );
			}

			System.out.println(recipient.getHistMessages());
			
		
			servSocket.close();
			in.close();
			out.close();
		}
		catch (IOException e) { e.printStackTrace(); }
	}

}
