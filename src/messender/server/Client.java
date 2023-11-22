package messender.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Client
{
	private String name;
	private String host;
	private int    servSocket;

	private List<Client> friends;
	private HashMap<String, ArrayList <String>> histMessages;

	public Client( String name, String host, int servSocket )
	{
		this.name = name;
		this.host = host;
		this.friends  = new LinkedList<Client>();
		this.histMessages = new HashMap <String, ArrayList <String>>();
		this.servSocket = servSocket;
	}

	public Client( String name, int servSocket ) { this(name, null, servSocket); }

	/**
	 * Allows the clients to send messages to another.
	 * A client can only send message to a Client if they are friends
	 * @param r receiver
	 * @param m message
	 */
	public void sendMessage( Client r, String m )
	{
		if ( this.isFriend(r) )
			try 
			{
				System.out.println( this.name + " sending message...");

				// Connecting to the server of the receiver
				System.out.println(r.host + " : " + r.servSocket);
				Socket toServ = new Socket( r.host, r.servSocket );

				// toServ.close();

				PrintWriter out = new PrintWriter( toServ.getOutputStream(), true );
				out.println( this.name + " : " + m);

				out.close();

			} catch (IOException e) { e.printStackTrace(); }
			
	}

	private boolean isFriend ( Client f ) { return this.friends.contains(f); }
	public  void    addFriend( Client f ) 
	{
		if ( !this.isFriend(f) )
		{
			this.friends.add(f); 
			f.friends.add(this);
		}
	}

	/**
	 * Add a new message to the message history
	 * @param r interlocutor
	 * @param m message
	 */
	public void addMessage( String r, String m ) 
	{
		if ( !this.histMessages.containsKey( r ) )
			this.histMessages.put( r, new ArrayList<String>());
	
		this.histMessages.get( r ).add( m );
	}

	/**
	 * This function makes the client wait for a new message.
	 * It creates a server and uses a thread to be able to receive multiple messages
	 * at once. This function needs to be used in a while loop in order to intercept 
	 * any message.
	 */
	public void receiveMessage()
	{
		try
		{
			System.out.println( this.name + " receiving message...");
			
			ServerSocket ownServ =  new ServerSocket( this.servSocket );
			Socket toClient = ownServ.accept(); // waiting for client

			// instantiate a ClientManager to process the client's requests
			ClientManager gdc = new ClientManager(toClient);

			// putting the manager un a Thread
			Thread tgdc = new Thread(gdc);

			// launch thread that will manage client
			tgdc.start();
		}
		catch ( UnknownHostException e ) { e.printStackTrace(); }
		catch ( IOException e ) { e.printStackTrace(); }
	}

	public String getName() { return this.name; }

	@Override
	public boolean equals(Object o) 
	{
		Client c = (Client) o;
		return this.name.equals( c.name );
	}

	public static void main(String[] args)
	{
		Client c1 = new Client("Justine", "di-729-09", 6000);
		Client c2 = new Client("Medhi",   "di-729-11", 9000);

		c1.addFriend(c2);

		boolean bOk = true;
		int i = 0;

		while (bOk)
		{
			if ( i == 0 ) c2.sendMessage(c1, "Bonjour mon con"); System.out.println("\t\t\t>>> C1 SENDING MESSAGE <<");
			c1.receiveMessage();
			System.out.println(i);
			i++;
		}
	}
}
