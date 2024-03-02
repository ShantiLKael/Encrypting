package messender.ui;

/**
 * @author Ashanti NJANJA - BUT Info 2
 * Date of creation : 01/03/24
 * 
 * Panel for sending messages
 */

import javax.swing.*;

import messender.server.Client;
import messender.server.Session;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class AddFriendPanel extends JPanel implements ActionListener 
{
    private Session session;
    private JTextField portField;
    private JTextField nicknameField;
    private JTextField nameField;
    private JTextField hostField;

    private JButton    addFriendBtn;
    private JButton    acceptFriendBtn;
    private JLabel     errorAddFriendLbl;
    private JList<String> friendRequestList;


    public AddFriendPanel(Session s)
    {
        session = s;

        this.setBackground(new Color(255, 234, 233));
        this.setMinimumSize(new Dimension(800, 600));
        this.setLayout(null);

        // creating components
        JLabel portLbl = new JLabel("Port");
        portField = new JTextField("");
        portLbl.setFont(new Font("Bahnschrift", 1, 14));
        portLbl.setForeground(new Color(204, 105, 119));
        portField.setBorder(BorderFactory.createTitledBorder(""));
        portField.setSelectedTextColor(new Color(193, 187, 234));
        portField.setSelectionColor(new Color(193, 187, 234));

        JLabel nameLbl = new JLabel("Name");
        nameField = new JTextField("");
        nameLbl.setFont(new Font("Bahnschrift", 1, 14));
        nameLbl.setForeground(new Color(204, 105, 119));
        nameField.setBorder(BorderFactory.createTitledBorder(""));
        nameField.setSelectedTextColor(new Color(193, 187, 234));
        nameField.setSelectionColor(new Color(193, 187, 234));
        
        JLabel hostLbl = new JLabel("Host");
        hostField = new JTextField("");
        hostLbl.setFont(new Font("Bahnschrift", 1, 14));
        hostLbl.setForeground(new Color(204, 105, 119));
        hostField.setBorder(BorderFactory.createTitledBorder(""));
        hostField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        hostField.setSelectedTextColor(new Color(193, 187, 234));
        hostField.setSelectionColor(new Color(193, 187, 234));
        
        JLabel nicknameLbl = new JLabel("Nickname (Optional)");
        nicknameField = new JTextField("");
        nicknameLbl.setFont(new Font("Bahnschrift", 1, 14));
        nicknameLbl.setForeground(new Color(204, 105, 119));
        nicknameField.setBorder(BorderFactory.createTitledBorder(""));
        nicknameField.setSelectedTextColor(new Color(193, 187, 234));
        nicknameField.setSelectionColor(new Color(193, 187, 234));
        
        addFriendBtn = new JButton(new ImageIcon("images/icons/plus.png"));
        addFriendBtn.setBackground(new Color(193, 187, 234));
        addFriendBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        errorAddFriendLbl = new JLabel("Error");
        errorAddFriendLbl.setFont(new Font("Bahnschrift", 0, 12));
        errorAddFriendLbl.setForeground(new Color(255, 102, 0));

        JLabel friendRequestListLbl = new JLabel("Friend Request List");
        friendRequestListLbl.setFont(new Font("Nirmala UI", 1, 16));
        friendRequestListLbl.setForeground(new Color(226, 90, 90));
        friendRequestListLbl.getAccessibleContext().setAccessibleName("Friend Request");

        friendRequestList = new JList<String>();
        friendRequestList.setBackground(new Color(250, 215, 215));
        friendRequestList.setBorder(null);
        friendRequestList.setFont(new Font("Microsoft JhengHei Light", 0, 14));


        List<String> friendRequestInfo = new LinkedList<String>();
        for ( Client c : session.getFriendRequest() )
            friendRequestInfo.add(c.getName() + " " + c.getHost());
    
        friendRequestList.setModel(new AbstractListModel<String>() {
            String[] strings = friendRequestInfo.toArray(new String[friendRequestInfo.size()]);
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        friendRequestList.setSelectionBackground(new Color(255, 168, 168));

        JPanel leftPanel = new JPanel();
        JPanel topLeftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        topLeftPanel.setBackground(new Color(250, 194, 194));

        acceptFriendBtn = new JButton(new ImageIcon("images/icons/plus-minus.png"));
        acceptFriendBtn.setBackground(new Color(255, 102, 102));

        topLeftPanel.add(friendRequestListLbl);
        topLeftPanel.add(acceptFriendBtn);
        leftPanel.add(friendRequestList, BorderLayout.CENTER);
        leftPanel.add(topLeftPanel, BorderLayout.NORTH);

        // placing components
        this.add(portLbl);
        this.add(portField);
        portField.setBounds(260, 100, 210, 30);
        portLbl.setBounds(260, 80, 50, 20);

        this.add(nameLbl);
        this.add(nameField);
        nameLbl.setBounds(540, 80, 50, 18);
        nameField.setBounds(540, 100, 210, 30);

        this.add(hostLbl);
        this.add(hostField);
        hostLbl.setBounds(260, 200, 50, 18);
        hostField.setBounds(260, 220, 210, 30);

        this.add(nicknameLbl);
        this.add(nicknameField);
        nicknameLbl.setBounds(540, 200, 150, 18);
        nicknameField.setBounds(540, 220, 210, 30);
        
        this.add(addFriendBtn);
        this.add(errorAddFriendLbl);
        addFriendBtn.setBounds(700, 270, 50, 40);        
        errorAddFriendLbl.setBounds(260, 280, 360, 15);

        this.add(leftPanel);
        leftPanel.setBounds(0, 0, 210, 550);

        // adding listeners
        acceptFriendBtn.addActionListener(this);
        addFriendBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

    }

    private boolean validTextfields()
    {
        return true;
    }
}
