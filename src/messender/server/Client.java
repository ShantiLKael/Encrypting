package messender.server;

class Client
{
	protected String name;
	protected String nickname;
	protected String host;
	protected int port;

	Client(String name, String host, int port)
	{
		this.name = this.nickname = name;
		this.port = port;
		this.host = host;
	}
}
