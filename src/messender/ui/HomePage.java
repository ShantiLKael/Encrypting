/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package messender.ui;

/**
 *
 * @author Ashanti
 */
public class HomePage extends javax.swing.JFrame {
    
    /**
     * Creates new form HomePage
     */
    public HomePage() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainFrame = new javax.swing.JPanel();
        UpperBar = new javax.swing.JPanel();
        sendBtn = new javax.swing.JButton();
        addFriendBtn = new javax.swing.JButton();
        themeBtn = new javax.swing.JButton();
        homeBtn = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        addFriendPanel = new javax.swing.JPanel();
        portLbl = new javax.swing.JLabel();
        portField = new javax.swing.JTextField();
        nameField = new javax.swing.JTextField();
        nameLbl = new javax.swing.JLabel();
        hostField = new javax.swing.JTextField();
        hostLbl = new javax.swing.JLabel();
        contentPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);

        mainFrame.setBackground(new java.awt.Color(255, 255, 255));
        mainFrame.setPreferredSize(new java.awt.Dimension(800, 600));
        mainFrame.setLayout(null);

        UpperBar.setBackground(new java.awt.Color(255, 204, 204));
        UpperBar.setPreferredSize(new java.awt.Dimension(800, 600));
        UpperBar.setLayout(new java.awt.GridLayout());

        sendBtn.setBackground(new java.awt.Color(255, 204, 204));
        sendBtn.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        sendBtn.setText("Sending");
        sendBtn.setMaximumSize(new java.awt.Dimension(84, 28));
        sendBtn.setMinimumSize(new java.awt.Dimension(84, 28));
        sendBtn.setPreferredSize(new java.awt.Dimension(84, 28));
        sendBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendBtnActionPerformed(evt);
            }
        });
        UpperBar.add(sendBtn);

        addFriendBtn.setBackground(new java.awt.Color(255, 204, 204));
        addFriendBtn.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        addFriendBtn.setText("Add Friend");
        addFriendBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFriendBtnActionPerformed(evt);
            }
        });
        UpperBar.add(addFriendBtn);

        themeBtn.setBackground(new java.awt.Color(255, 204, 204));
        themeBtn.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        themeBtn.setText("Theme");
        themeBtn.setMaximumSize(new java.awt.Dimension(84, 28));
        themeBtn.setMinimumSize(new java.awt.Dimension(84, 28));
        themeBtn.setPreferredSize(new java.awt.Dimension(84, 28));
        UpperBar.add(themeBtn);

        homeBtn.setBackground(new java.awt.Color(255, 234, 233));
        homeBtn.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        homeBtn.setText("Home");
        homeBtn.setMaximumSize(new java.awt.Dimension(84, 28));
        homeBtn.setMinimumSize(new java.awt.Dimension(84, 28));
        homeBtn.setPreferredSize(new java.awt.Dimension(84, 28));
        homeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeBtnActionPerformed(evt);
            }
        });
        UpperBar.add(homeBtn);
        UpperBar.add(jSeparator1);
        UpperBar.add(jSeparator2);
        UpperBar.add(jSeparator5);
        UpperBar.add(jSeparator6);
        UpperBar.add(jSeparator3);
        UpperBar.add(jSeparator4);

        mainFrame.add(UpperBar);
        UpperBar.setBounds(0, 0, 800, 50);

        addFriendPanel.setBackground(new java.awt.Color(255, 234, 233));
        addFriendPanel.setMinimumSize(new java.awt.Dimension(800, 600));
        addFriendPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        addFriendPanel.setLayout(null);

        portLbl.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        portLbl.setText("Port");
        addFriendPanel.add(portLbl);
        portLbl.setBounds(130, 140, 50, 20);

        portField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                portFieldActionPerformed(evt);
            }
        });
        addFriendPanel.add(portField);
        portField.setBounds(130, 160, 210, 30);

        nameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameFieldActionPerformed(evt);
            }
        });
        addFriendPanel.add(nameField);
        nameField.setBounds(450, 160, 210, 30);

        nameLbl.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        nameLbl.setText("Name");
        addFriendPanel.add(nameLbl);
        nameLbl.setBounds(450, 140, 50, 18);

        hostField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hostFieldActionPerformed(evt);
            }
        });
        addFriendPanel.add(hostField);
        hostField.setBounds(130, 280, 210, 30);

        hostLbl.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        hostLbl.setText("Host");
        addFriendPanel.add(hostLbl);
        hostLbl.setBounds(130, 260, 50, 18);

        mainFrame.add(addFriendPanel);
        addFriendPanel.setBounds(0, 50, 800, 550);

        contentPanel.setBackground(new java.awt.Color(255, 234, 233));
        contentPanel.setMinimumSize(new java.awt.Dimension(800, 600));
        contentPanel.setName(""); // NOI18N

        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        mainFrame.add(contentPanel);
        contentPanel.setBounds(0, 50, 800, 550);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void hostFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hostFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hostFieldActionPerformed

    private void nameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameFieldActionPerformed

    private void portFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_portFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_portFieldActionPerformed

    private void homeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_homeBtnActionPerformed

    private void addFriendBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFriendBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addFriendBtnActionPerformed

    private void sendBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sendBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel UpperBar;
    private javax.swing.JButton addFriendBtn;
    private javax.swing.JPanel addFriendPanel;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JButton homeBtn;
    private javax.swing.JTextField hostField;
    private javax.swing.JLabel hostLbl;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JPanel mainFrame;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel nameLbl;
    private javax.swing.JTextField portField;
    private javax.swing.JLabel portLbl;
    private javax.swing.JButton sendBtn;
    private javax.swing.JButton themeBtn;
    // End of variables declaration//GEN-END:variables
}
