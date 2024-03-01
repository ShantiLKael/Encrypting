package messender.uiTest;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.net.BindException;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import messender.server.Session;
import messender.server.Client;

public class HomePage extends JFrame
{
	static Session session;

	public HomePage()
	{
		try {
			HomePage.session = new Session("test Session", "localhost", 0);
		} catch (BindException e) { e.printStackTrace(); }
		
		// Test : List of friends
		LinkedList<Client> friends = new LinkedList<Client>();
		for ( int i=0; i < 23; i++)
			friends.add( new Client((char) (i+'a') + "fzerfezefzf", "localhost", (i+10)) );
		HomePage.session.setFriends(friends);



		this.setSize(Loading.WIDTH, Loading.HEIGHT);
		this.setLocationRelativeTo(null); // center of the screen
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);

		this.setLayout(new BorderLayout());

		JPanel panelLeft = new JPanel();
		JPanel panelFriend = new JPanel();
		panelFriend.setLayout(new GridLayout(HomePage.session.getFriends().size(),1,2,2));
		panelLeft.setLayout(new GridLayout(1,2));
		
		for ( Client friend : HomePage.session.getFriends())
			panelFriend.add(new JButton( friend.getNickname() ));

		JScrollBar scrollBar = new JScrollBar(JScrollBar.VERTICAL, HomePage.HEIGHT/10, HomePage.HEIGHT, 0, HomePage.HEIGHT);
		panelLeft.add(scrollBar);
		panelLeft.add(panelFriend);
		this.add(panelLeft, BorderLayout.WEST);
	}

	public static void main(String[] args)
	{
		new HomePage();	
	}
}
