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
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.ConnectException;
import java.util.LinkedList;
import java.util.List;

public class SendingPanel extends JPanel implements ActionListener 
{
    private Session session;
    private JTextField messageField;
    private JButton    sendMessBtn;
    private JButton    delFriendBtn;
    private JLabel     errorSendingLbl;
    private JList<String> friendList;


    public SendingPanel(Session s)
    {
        session = s;
        
        this.setBackground(FrameApp.CONTENT_BGCOLOR);
        this.setPreferredSize(new Dimension(FrameApp.WIDTH, FrameApp.HEIGHT));
        this.setRequestFocusEnabled(false);
        this.setLayout(null);

        // creating components
        JPanel messagePanel = new JPanel();
        messagePanel.setBackground(new Color(242, 193, 193));
        messagePanel.setBorder(BorderFactory.createLineBorder(new Color(229, 185, 185), 2));

        sendMessBtn = new JButton( new ImageIcon("icons/sent-minus.png") );
        sendMessBtn.setBackground(new Color(193, 187, 234));

        messageField = new JTextField("Text here...");
        messageField.setBorder(BorderFactory.createTitledBorder(""));

        GroupLayout messagePanelLayout = new GroupLayout(messagePanel);
        messagePanel.setLayout(messagePanelLayout);
        messagePanelLayout.setHorizontalGroup(
            messagePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(messagePanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(messageField, GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sendMessBtn, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        messagePanelLayout.setVerticalGroup(
            messagePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(messagePanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(messagePanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(messageField, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                    .addComponent(sendMessBtn, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(250, 207, 207));
        leftPanel.setLayout(new BorderLayout());

        friendList = new JList<String>();
        friendList.setBackground(new Color(250, 215, 215));
        friendList.setBorder(null);
        friendList.setFont(new Font("Microsoft JhengHei Light", 0, 14)); // NOI18N

        List<String> friendNickname = new LinkedList<String>();
        for ( Client c : session.getFriends() )
            friendNickname.add(c.getNickname());

        friendList.setModel(new AbstractListModel<String>()
        {
            String[] strings = friendNickname.toArray(new String[friendNickname.size()]);
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        friendList.setSelectionBackground(new Color(255, 168, 168));

        JPanel topleftPanel = new JPanel();
        topleftPanel.setBackground(new Color(250, 194, 194));

        JLabel friendListLbl = new JLabel("Friend                    ");
        friendListLbl.setFont(new Font("Nirmala UI", 1, 16)); // NOI18N
        friendListLbl.setForeground(new Color(226, 90, 90));

        delFriendBtn = new JButton(new ImageIcon("icons/bin.png"));
        delFriendBtn.setBackground(new Color(255, 102, 102));

        errorSendingLbl = new JLabel("Error");
        errorSendingLbl.setFont(new Font("Bahnschrift", 0, 12)); // NOI18N
        errorSendingLbl.setForeground(new Color(255, 102, 0));

        // placing components
        topleftPanel.add(friendListLbl);
        topleftPanel.add(delFriendBtn);
        leftPanel.add(friendList, BorderLayout.CENTER);
        leftPanel.add(topleftPanel, BorderLayout.NORTH);

        this.add(messagePanel);
        messagePanel.setBounds(210, 470, 590, 80);

        this.add(leftPanel);
        leftPanel.setBounds(0, 0, 210, 550);
        
        this.add(errorSendingLbl);
        errorSendingLbl.setBounds(220, 450, 466, 15);

        // adding listeners
        sendMessBtn.addActionListener(this);
        delFriendBtn.addActionListener(this);   
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if ( friendList.getSelectedValue() == null )
            errorSendingLbl.setText("Select a conversation first ! Don't have any friend ? Add one here : ");      

        if ( e.getSource() == sendMessBtn && friendList.getSelectedValue() != null )
        {
            // converting nicknames into Client
            Client receiver = null;
            for ( Client c : session.getFriends() ) 
                if ( c.getNickname().equals(friendList.getSelectedValue()))
                    receiver = c;

            // verify textfield and send message
            if ( validMessage() && receiver != null )
                try
                {
                    session.sendMessage(receiver, messageField.getText());
                    errorSendingLbl.setText("");
                }
                catch (ConnectException e1)
                {
                    errorSendingLbl.setText("Your friend disconnected, he won't receive that message");
                }
        }

        if ( e.getSource() == delFriendBtn && friendList.getSelectedValue() != null )
        {
            // TODO Pop Up are u sure u want to delete your friend [name]
            session.delFriend(friendList.getSelectedValue());
        }

    }

    private boolean validMessage()
    {
        String message = messageField.getText();

        if ( !message.isEmpty() )
            for(int i = 0; i < message.length(); i++)
            {
                char c = message.charAt(i);
                if ( c !=  ' ' )
                    return true;
            }
        
        return false;
    }
}
