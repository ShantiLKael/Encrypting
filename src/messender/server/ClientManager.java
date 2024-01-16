package messender.server;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.stream.Collectors;

class ClientManager implements Runnable
{
	private String senderName;
	private Client recipient;
	private BufferedReader in;
	private PrintWriter out;
	Socket servSocket;

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

		do
		{
			String[] longMessage = in.lines().collect(Collectors.joining()).split(":");
			if ( longMessage.length == 2 )
			{
				this.senderName = longMessage[0];
				String message  = longMessage[1];
				this.recipient.addMessageHistory(this.senderName, message );
			}

			System.out.println(recipient.getHistMessages());
			
			try 
			{
				Thread.sleep(60*10);
			}
			catch (InterruptedException e) { e.printStackTrace(); }
		} while ( !this.out.checkError() );

		
		System.out.println("Deconnexion du client");
		try 
		{
			in.close();
			out.close();
		}
		catch (IOException e) { e.printStackTrace(); }

		/*
		try
		{
			out.println("Bienvenue :3");

			do
			{
				out.println("Bienvenue :3");

				// lecture bloquante du message client
				String m = in.readLine(); 

				// Affichage du message client
				if ( m != null )
					System.out.println( ((this.sender != null) ? this.sender : "Client " + GerantDeClient.nbInstance) + " : " + m);


			} while ( !this.out.checkError() );

			System.out.println("Deconnexion du client");

			// On ferme les reader
			in.close();
			out.close();
			GerantDeClient.nbInstance--;


		} catch (IOException ioe) {}
		*/
	}

}
