package messender.server;

import messender.crypting.Affine;

public class Client
{
	protected String name;
	protected String nickname;
	protected String host;
	protected int    port;
	protected boolean online;
	protected Affine messageEncoder;


	public Client(String name, String host, int port)
	{
		this.name = this.nickname = name;
		this.port = port;
		this.host = host;


		for (int i = 0; 
			 i < this.name.length() &&
			 (this.messageEncoder = Affine.createFunction( i + this.name.charAt(0), this.port )) == null;
			 i++);
	}

	static String decrypt( String name, int port, String message )
	{
		Affine messageEncoder = null;

		for (int i = 0; 
			 i < name.length() &&
			 (messageEncoder = Affine.createFunction( i + name.charAt(0), port )) == null;
			 i++);

		return messageEncoder.decrypt(message);
	}

	public String getName    () { return name; }
	public String getHost    () { return host; }
	public int    getPort    () { return port; }
	public String getNickname() { return nickname; }
}
