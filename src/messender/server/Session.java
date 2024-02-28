package messender.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;

import messender.crypting.Affine;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Session extends Client
{
	private ServerSocket servSocket;

	private Affine messageEncoder;

	private List<Client> friends;
	private Map<String, List<String>> histMessages;

	// the parameters 'createServ' is here just for the tests, it will be deleted later
	private Session( String name, String host, int port, boolean createServ ) throws BindException
	{
		super(name, host, port);
		
		this.friends  = new LinkedList<Client>();
		this.histMessages = new HashMap <String, List<String>>();

		for (int i = 0; 
			 i < this.name.length() &&
			 (this.messageEncoder = Affine.createFunction( i + this.name.charAt(0), this.port )) == null;
			 i++);

		if ( createServ )
			try
			{
				this.servSocket = new ServerSocket(this.port);
			}
			catch (BindException e) { System.out.println("You cannot use this port, somebody already uses it\n\n." + e); }
			catch (IOException e) { e.printStackTrace(); }
	}

	public Session( String name, int port, boolean createServ ) throws BindException
	{
		this(name, "localhost", port, createServ);
	}

	public Session ( String name, int port ) throws BindException
	{
		this(name, "localhost", port, true);
	}

	public Session ( String name, String host, int port ) throws BindException
	{
		this(name, host, port, true);
	}

	/**
	 * Allows the clients to send messages to another.
	 * The message is composed of 3 elements : the sender's name, port and message.
	 * @param receiver
	 * @param message message
	 */
	public void sendMessage( Session receiver, String message )
	{
		try 
		{
			System.out.println(this.name + " sending message...");

			// Connecting to the server of the receiver
			Socket toServ = new Socket(receiver.host, receiver.port);

			PrintWriter out = new PrintWriter(toServ.getOutputStream(), false);
			out.println( this.name + "@" + this.port + ":" + this.messageEncoder.encrypt(message));
			this.addMessageHistory(receiver.name, message);

			out.close();
			toServ.close();
		}
		catch (ConnectException e) { System.out.println("Your reaching fella disconnected.\n" + e);}
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
		
		MessageReceiver receiving = new MessageReceiver(this);

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
		}
	}

	boolean isFriend( Client c )
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
		Session c = (Session) o;
		return this.name.equals(c.name) && this.port == c.port;
	}

	String getName() { return this.name; }
	Map<String,List<String>> getHistMessages() { return this.histMessages; }
	List<Client> getFriends() { return this.friends; }
	ServerSocket getServSocket() { return this.servSocket; }


	@Override
	public String toString()
	{
		return String.format( "%-15s", "Client " + this.name) +  "[" + this.host + "]" + 
			" : " + this.port;
	}

	public static void main(String[] args)
	{
		Session c1 = null, c2 = null, c3 = null;
		
		try
		{
			c1 = new Session("Justine", 6000);
			c3 = new Session("Ali", 9001);
			c2 = new Session("Medhi", 9000, false);
		} catch (BindException e) {	System.out.println("Clients not created"); }

		for ( int i = 0; i < 2; i++)
		{
			try
			{
				Thread.sleep(600*2);
			}
			catch (InterruptedException e) { e.printStackTrace(); }
			
			c1.sendMessage(c2, "wassup");
		}


		for ( int i = 0; i < 2; i++)
		{
			try
			{
				Thread.sleep(600*2);
			}
			catch (InterruptedException e) { e.printStackTrace(); }
			
			c3.sendMessage(c2, "hello");
		}
	}
}
