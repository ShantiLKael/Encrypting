package messender.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;

import messender.crypting.Affine;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Client
{
	private String name;
	private String host;
	private int    port;
	private ServerSocket servSocket;

	private Affine messageEncoder;

	private List<Client> friends;
	private Map<String, List<String>> histMessages;

	Client( String name, String host, int port, boolean createServ )
	{
		this.name = name;
		this.host = host;
		this.port = port;
		
		this.friends  = new LinkedList<Client>();
		this.histMessages = new HashMap <String, List<String>>();

		for (int i = 0; 
			 i < this.name.length() && (this.messageEncoder = Affine.createFunction( i + this.name.charAt(0), this.port )) == null ;
			 i++);

		if ( createServ )
			try
			{
				this.servSocket = new ServerSocket(this.port);
			}
			catch (java.net.BindException e) { System.out.println("You cannot use this port, somebody already uses it\n\n." + e); }
			catch (IOException e) { e.printStackTrace(); }
	}

	public Client( String name, int port, boolean createServ )
	{
		this(name, "localhost", port, createServ);
	}

	public Client ( String name, int port )
	{
		this(name, "localhost", port, true);
	}

	public Client ( String name, String host, int port )
	{
		this(name, host, port, true);
	}

	public Client ( Client c )
	{
		this(c.name, c.host, c.port, false);
	}

	/**
	 * Allows the clients to send messages to another.
	 * A client can only send message to a Client if they are friends
	 * @param receiver
	 * @param message message
	 */
	public void sendMessage( String receiverHost, int receiverPort, String message )
	{
		try 
		{
			System.out.println(this.name + " sending message...");

			// Connecting to the server of the receiver
			Socket toServ = new Socket(receiverHost, receiverPort);

			PrintWriter out = new PrintWriter(toServ.getOutputStream(), false);
			out.println( this.name + "@" + this.port + ":" + this.messageEncoder.encrypt(message));

			out.close();
			toServ.close();
		}
		catch (java.net.ConnectException e) { System.out.println("Your reaching fella disconnected.\n" + e);}
		catch (IOException e) { e.printStackTrace(); }
	}

	/**
	 * This function makes the client wait for a new message.
	 * It creates a server and uses a thread to be able to receive multiple messages
	 * at once. This function needs to be used in a while loop in order to intercept 
	 * messages at any time.
	 */
	public void receiveMessage()
	{
	
		System.out.println(this.name + " receiving message...");
		
		// instantiate a ClientManager to process the client's requests
		ClientManager receiving = new ClientManager(this);

		// putting the manager un a Thread
		Thread tgdc = new Thread(receiving);

		// launch thread that will manage client
		tgdc.start();
	}

	/**
	 * Add a new message to the message history
	 * @param clientName interlocutor
	 * @param message
	 */
	void addMessageHistory( String senderName, String m ) 
	{
		if ( !this.histMessages.containsKey(senderName) )
			this.histMessages.put(senderName, new ArrayList<String>());
	
		this.histMessages.get(senderName).add(m);
	}

	public void addFriend( Client c ) 
	{
		if ( !this.isFriend(c) )
		{
			this.friends.add(c); 
			c.friends.add(this);
		}
	}

	boolean isFriend ( Client c )
	{
		return this.friends.contains(c);
	}

	String decrypt( String message )
	{
		return this.messageEncoder.decrypt(message);
	}

	@Override
	public boolean equals( Object o ) 
	{
		Client c = (Client) o;
		return this.name.equals(c.name) || this.port == c.port;
	}

	String getName() { return this.name; }
	Map<String,List<String>> getHistMessages() { return this.histMessages; }
	List<Client> getFriends() { return this.friends; }
	ServerSocket getServSocket() { return this.servSocket; }


	@Override
	public String toString()
	{
		return String.format( "%-15s", "Client " + this.name) +  " [" + this.host + "]" + 
			"  : " + this.port;
	}

	public static void main(String[] args)
	{
		Client c1 = new Client("Justine", 6000);
		Client c3 = new Client("Ali", 9001);

		for ( int i = 0; i < 1; i++)
		{
			try
			{
				Thread.sleep(600*2);
			}
			catch (InterruptedException e) { e.printStackTrace(); }
			
			c1.sendMessage("localhost", 9000, "wassup");
		}


		for ( int i = 0; i < 1; i++)
		{
			try
			{
				Thread.sleep(600*2);
			}
			catch (InterruptedException e) { e.printStackTrace(); }
			
			c3.sendMessage("localhost", 9000, "wassup");
		}
	}
}
