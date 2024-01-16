package messender.ui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

public class Loading extends JFrame implements ActionListener
{
	private JButton btnStart;
	private JLabel bgImage;
	private JLabel lblLoad;

	public Loading()
	{
		/* Dimension */
		int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2);
		int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 1.5 - 20);

		this.setSize(width, height);
		this.setLocationRelativeTo(null); // center of the screen
		this.setLayout(new BorderLayout());

		/* Components creation */		
		this.bgImage = new JLabel(new ImageIcon("lib/test-img.jpg"));
		this.bgImage.setSize(width, height);

		this.btnStart = new JButton("Start");
		this.btnStart.setFocusable(false);

		this.btnStart.setBorder(new RoundButton(50));
		this.btnStart.setBackground(Color.PINK);
		this.btnStart.setCursor(new Cursor(Cursor.HAND_CURSOR));

		this.btnStart.setSize(200, 65);
		this.btnStart.setBounds(width / 2 - this.btnStart.getWidth () / 2, (int) (height / 1.5), this.btnStart.getWidth(), this.btnStart.getHeight());

		this.lblLoad = new JLabel("HEY");
		this.lblLoad.setVisible(false);
		this.lblLoad.setBounds(width / 2, (int) (height / 1.5), this.btnStart.getWidth(), this.btnStart.getHeight());

		/* Adding components */
		this.bgImage.add(this.btnStart);
		this.bgImage.add(this.lblLoad);
		this.add(this.bgImage, BorderLayout.CENTER);

		this.btnStart.addActionListener(this);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}

	@Override
	public void paintComponents( Graphics g )
	{
		super.paintComponents(g);
	}

	@Override
	public void actionPerformed( ActionEvent e )
	{

		if ( e.getSource() == this.btnStart )
		{
			System.out.println("Start");
			this.btnStart.setVisible(false);
			// this.createClient();

			this.lblLoad.setVisible(true);
		}
	}

	public static void main( String[] args )
	{
		new Loading();
	}
}