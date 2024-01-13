package messender.server;

import java.io.*;
import java.net.Socket;

class ClientManager implements Runnable
{
	private String senderName;
	private Client recipient;
	private BufferedReader in;
	private PrintWriter out;
	Socket servSocket;

	public ClientManager ( String senderName, Client recipient, Socket s ) 
	{
		this.servSocket	= s;
		this.recipient  = recipient;

		try 
		{
			this.in  = new BufferedReader( new InputStreamReader( s.getInputStream() ) );
			this.out = new PrintWriter( s.getOutputStream(), true );

		} catch ( IOException e ) { e.printStackTrace(); }

		this.senderName = senderName;
	}

	@Override
    public void run() 
	{
		System.out.println("Connexion du client " + ((this.senderName != null) ? this.senderName : "")); // getHostName() ??

		do
		{
			try 
			{
				System.out.println( this.senderName + " : " + in.toString() );
				Thread.sleep(500);
			}
			//catch (IOException ie) {}
			catch (InterruptedException e) {}

		} while ( !this.out.checkError() );

		
		System.out.println("Deconnexion du client");
		try 
		{
			in.close();
			out.close();
		} catch ( IOException e ) {}

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
					System.out.println( ((this.senderName != null) ? this.senderName : "Client " + GerantDeClient.nbInstance) + " : " + m);


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
