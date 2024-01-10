package messender.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Client
{
	private String name;
	private String host;
	private int    port;

	private ServerSocket servSocket;
	private Socket socket;

	private List<Client> friends;
	private Map<String, ArrayList <String>> histMessages;

	public Client( String name, String host, int port )
	{
		this.name = name;
		this.host = host;
		this.friends  = new LinkedList<Client>();
		this.histMessages = new HashMap <String, ArrayList <String>>();
		this.port = port;

		try
		{
			this.servSocket = new ServerSocket(this.port);
		} catch (IOException e) { e.printStackTrace(); }
	}

	public Client( String name, int port )
	{
		this(name, "localhost", port);
	}

	/**
	 * Allows the clients to send messages to another.
	 * A client can only send message to a Client if they are friends
	 * @param receiver
	 * @param m message
	 */
	public void sendMessage( String receiverHost, int receiverPort, String m )
	{
		try 
		{
			System.out.println( this.name + " sending message...");

			// Connecting to the server of the receiver
			Socket toServ = new Socket( receiverHost, receiverPort );

			PrintWriter out = new PrintWriter( toServ.getOutputStream(), true );
			out.println( this.name + " : " + m);

			out.close();
			toServ.close();
		} catch (IOException e) { e.printStackTrace(); }
	}

	/**
	 * Add a new message to the message history
	 * @param clientName interlocutor
	 * @param m message
	 */
	public void addMessageHistory( String clientName, String m ) 
	{
		if ( !this.histMessages.containsKey(clientName) )
			this.histMessages.put(clientName, new ArrayList<String>());
	
		this.histMessages.get(clientName).add(m);
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
			Socket toClient = this.servSocket.accept(); // waiting for client

			// instantiate a ClientManager to process the client's requests
			ClientManager receiving = new ClientManager(toClient);

			// putting the manager un a Thread
			Thread tgdc = new Thread(receiving);

			// launch thread that will manage client
			tgdc.start();
		}
		catch ( UnknownHostException e ) { e.printStackTrace(); }
		catch ( IOException e ) { e.printStackTrace(); }
	}

	public String getName() { return this.name; }

	private boolean isFriend ( Client f ) { return this.friends.contains(f); }
	public  void    addFriend( Client f ) 
	{
		if ( !this.isFriend(f) )
		{
			this.friends.add(f); 
			f.friends.add(this);
		}
	}

	@Override
	public boolean equals( Object o ) 
	{
		Client c = (Client) o;
		return this.name.equals(c.name);
	}

	@Override
	public String toString()
	{
		return String.format( "%-15s", "Client " + this.name) +  " [" + this.host + "]" + 
			   "  : " + this.port;
	}

	public static void main(String[] args)
	{
		Client c1 = new Client("Justine", 6000);
		c1.sendMessage("localhost", 9000, "wassup");
	}
}
