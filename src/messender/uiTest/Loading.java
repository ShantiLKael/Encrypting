package messender.uiTest;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import messender.server.Session;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.BindException;

public class Loading extends JFrame implements ActionListener
{

	static final int WIDTH  = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth () / 2);
	static final int HEIGHT = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2);

	static final String WRONG_PORT = "Your port is incorrect";
	static final String WRONG_HOSTNAME = "Your hostname is incorrect";
	static final String NEW_SESSION = "New session created";

	static Session session;

	private JButton btnStart;
	private JLabel bgImage;

	private JTextField txtName;
	private JTextField txtPort;

	private JPanel panelBottom;

	private JCheckBox additionalSettings;

	public Loading()
	{
		this.setSize(Loading.WIDTH, Loading.HEIGHT);
		this.setLocationRelativeTo(null); // center of the screen
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);

		this.setLayout(new BorderLayout());

		/* Components creation */
		this.txtName = new JTextField("localhost");
		this.txtPort = new JTextField();

		this.additionalSettings = new JCheckBox(" | Additional settings");

		this.bgImage = new JLabel(new ImageIcon("icons/test-img.jpg"));
		this.bgImage.setSize(Loading.WIDTH, Loading.HEIGHT);

		this.btnStart = new JButton("Start");
		this.btnStart.setFocusable(false);
		this.btnStart.setBackground(Color.PINK);
		this.btnStart.setCursor(new Cursor(Cursor.HAND_CURSOR));

		this.btnStart.setSize(200, 65);
		this.btnStart.setBounds( (int) (Loading.WIDTH / 2.5 - this.btnStart.getWidth () / 2.5), Loading.HEIGHT / 2, this.btnStart.getWidth(), this.btnStart.getHeight());

		/* Adding components */
		this.bgImage.add(this.btnStart);
		this.bgImage.add(this.additionalSettings);

		JPanel panelName = new JPanel();
		panelName.add(new JLabel("Nom : "));
		panelName.add(this.txtName);

		JPanel panelPort = new JPanel();
		panelPort.add(new JLabel("Port : "));
		panelPort.add(this.txtPort);

		this.panelBottom = new JPanel();		
		this.panelBottom.add(panelName);
		this.panelBottom.add(panelPort);
		
		this.panelBottom.setVisible(false);

		JPanel panelAll = new JPanel();
		panelAll.setLayout(new GridLayout(2,1));
		panelAll.add(this.panelBottom);
		panelAll.add(this.additionalSettings);

		this.add(this.bgImage, BorderLayout.CENTER);
		this.add(panelAll, BorderLayout.SOUTH);

		this.btnStart.addActionListener(this);
		this.additionalSettings.addActionListener(this);
	}

	@Override
	public void actionPerformed( ActionEvent event )
	{
		// TODO : try to use DocumentListener for port entry
		
		if ( event.getSource() == this.btnStart )
		{
			this.btnStart.setVisible(false);

			if ( this.additionalSettings.isSelected() )
			{
				if ( validEntry() )
				{
					String clientName = this.txtName.getText().isEmpty() ? "user" : this.txtName.getText();
					int port = Integer.parseInt(this.txtPort.getText());

					this.session = createClient(clientName, "localhost", port);
					openApp();
				}
				else
					JOptionPane.showMessageDialog( this, Loading.WRONG_PORT, "Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				this.session = createClient( "sessionTest", "localhost", 0);
				openApp();
			}		
		}

		if ( event.getSource() == this.additionalSettings )
		{
			if ( this.additionalSettings.isSelected())
			{
				this.setSize(Loading.WIDTH, Loading.HEIGHT + Loading.HEIGHT /10 );
				// TODO Here I need to repaint the screen
				this.setLocationRelativeTo(null);
				this.panelBottom.setVisible(true);
			}
			else
			{
				this.setSize(Loading.WIDTH, Loading.HEIGHT );
				this.setLocationRelativeTo(null);
				this.panelBottom.setVisible(false);
			}
		}	
	}

	/**
	 * Return false if the name is empty and null if the port isn't convertible in int
	 */
	private boolean validEntry()
	{
		try
		{
			Integer.parseInt(this.txtPort.getText());
			return (!this.txtName.getText().isEmpty());
		}
		catch (Exception e) { return false; }
	}

	private Session createClient( String name, String host, int port )
	{
		try 
		{
			return new Session(name, host, port);
		}
		catch (BindException e)
		{
			int userChoice = JOptionPane.showConfirmDialog(this,
			Loading.WRONG_PORT + "\nContinue ? ", 
			"Confirm",
			JOptionPane.YES_NO_OPTION);

			if ( userChoice == JOptionPane.YES_OPTION )
				return createClient(name, host, port +1);
			else
				return null;
		}
	}

	private void openApp()
	{
		JOptionPane.showMessageDialog(
			this, Loading.NEW_SESSION, "", JOptionPane.INFORMATION_MESSAGE
		);
	}

	public static void main( String[] args )
	{
		Loading hp = new Loading();
	}
}