package messender.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class NavBar extends JPanel implements ActionListener
{
    private JButton sendMenu;
	private JButton addMenu;
	private JButton themeMenu;
	private HomePage mainFrame;

	public NavBar(HomePage homepg)
	{
		// Creating components
		this.addMenu = new JButton("Add");
		this.sendMenu = new JButton("Send");
		this.themeMenu = new JButton("Theme");
		this.mainFrame = homepg;

		// Adding components to menubar
		this.add(this.addMenu);
		this.add(this.sendMenu);
		this.add(this.themeMenu);
		this.setBackground(Color.PINK);

		// Adding listeners
		this.addMenu.addActionListener(this);
		this.sendMenu.addActionListener(this);
		this.themeMenu.addActionListener(this);
	}

	@Override
	public void actionPerformed( ActionEvent e )
	{
		if ( e.getSource() == this.addMenu	) System.out.println("new AddPanel");
			// TODO this.mainFrame.put( new AddPanel() );

		if ( e.getSource() == this.sendMenu	) System.out.println("new SendMenu");
			// TODO this.mainFrame.put( new SendPanel() );

		if ( e.getSource() == this.themeMenu ) System.out.println("new ThemePanel");
			// TODO this.mainFrame.put( new ThemePanel() );
	}
}
