package messender.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.BindException;
import javax.swing.*;

import messender.icons.*;
import messender.server.Session;

/**
 * @author Ashanti NJANJA - BUT Info 2
 * Date of creation : 01/03/24
 * 
 * Main app frame
 */

public class FrameApp extends JFrame implements ActionListener
{
    public static int WIDTH = 800;
    public static int HEIGHT = 600;
    public static Font HEADER_FONT = new Font("Nirmala UI", 1, 14);
    public static Color HEADER_COLOR = new Color(255, 132, 149);
    public static Color CONTENT_BGCOLOR = new Color(255, 234, 233);
    
    private static Session USER;

    private JPanel fullFrame;
    private JPanel contentPage; // the panel changing its content according to the header buttons
    
    private SendingPanel sendingP;     // showed after sendMenu button
    private AddFriendPanel addFriendP; // showed after addFriendMenu button

    private JPanel upperBar; // header of the fraim
    private JButton addFriendMenu;
    private JButton homeMenu;
    private JButton sendMenu;
    private JButton themeMenu;
    
    public FrameApp()
    {

        try
        {
            FrameApp.USER = new Session("test", 0);
        } catch (BindException e) {} // TODO remove delete

        // Creating buttons
        Dimension btnHeaderBtn = new Dimension(84, 28);

        sendMenu = new JButton();
        sendMenu.setBackground(HEADER_COLOR);
        sendMenu.setFont(HEADER_FONT);
        sendMenu.setIcon(new ImageIcon(getClass().getResource("/icons/sent.png")));
        sendMenu.setBorder(BorderFactory.createLineBorder(new Color(251, 163, 163)));
        this.setAllDimensions(sendMenu, btnHeaderBtn);

        addFriendMenu = new JButton();
        addFriendMenu.setBackground(HEADER_COLOR);
        addFriendMenu.setFont(HEADER_FONT);
        addFriendMenu.setIcon(new ImageIcon(getClass().getResource("/icons/friend.png")));
        addFriendMenu.setBorder(BorderFactory.createLineBorder(new Color(251, 163, 163)));
        this.setAllDimensions(addFriendMenu, btnHeaderBtn);

        themeMenu = new JButton();
        themeMenu.setBackground(HEADER_COLOR);
        themeMenu.setFont(HEADER_FONT);
        themeMenu.setIcon(new ImageIcon(getClass().getResource("/icons/theme.png")));
        themeMenu.setBorder(BorderFactory.createLineBorder(new Color(251, 163, 163)));
        this.setAllDimensions(themeMenu, btnHeaderBtn);

        homeMenu = new JButton();
        homeMenu.setBackground(HEADER_COLOR);
        homeMenu.setFont(HEADER_FONT);
        homeMenu.setIcon(new ImageIcon(getClass().getResource("/icons/home.png")));
        homeMenu.setBorder(BorderFactory.createLineBorder(new Color(251, 163, 163)));
        this.setAllDimensions(homeMenu, btnHeaderBtn);
        
        upperBar = new JPanel();
        upperBar.setBackground(HEADER_COLOR);
        upperBar.setPreferredSize(new Dimension(400, 500));
        upperBar.setLayout(new GridLayout(1, 0));
        upperBar.setBounds(0, 0, 800, 50);

        // Adding buttons to the header
        upperBar.add(sendMenu);
        upperBar.add(addFriendMenu);
        upperBar.add(themeMenu);
        upperBar.add(homeMenu);
        for ( int i = 0; i < 4; i++ )
            upperBar.add(new JLabel());

        // Creating content panels
        contentPage = new JPanel();
        contentPage.setBackground(new Color(255, 234, 233));
        contentPage.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        contentPage.setRequestFocusEnabled(false);
        contentPage.setLayout(null);
        contentPage.setBounds(0, 50, 800, 550);
        sendingP = new SendingPanel(USER);
        addFriendP = new AddFriendPanel(USER);
        
        fullFrame = new JPanel();
        fullFrame.setBackground(new Color(255, 255, 255));
        fullFrame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        fullFrame.setLayout(null);
        fullFrame.add(upperBar);
        fullFrame.add(contentPage);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(fullFrame, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(fullFrame, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );

        // Adding listeners to buttons
        addFriendMenu.addActionListener(this);
        homeMenu.addActionListener(this);
        sendMenu.addActionListener(this);
        themeMenu.addActionListener(this);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }

    private void setAllDimensions(JButton btn, Dimension d)
    {
        btn.setMaximumSize(d);
        btn.setMinimumSize(d);
        btn.setPreferredSize(d);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == addFriendMenu)
        {
            contentPage.removeAll();
            contentPage.add(addFriendP);
            addFriendP.setBounds(0, 0, 800, 550);
            this.revalidate();
            this.repaint();
        }

        if (e.getSource() == sendMenu)
        {
            contentPage.removeAll();
            contentPage.add(sendingP);
            sendingP.setBounds(0, 0, 800, 550);
            this.revalidate();
            this.repaint();
        }
            
        //if ( e.getSource() == themeMenu )
        //if ( e.getSource() == homeMenu )
    }

}
