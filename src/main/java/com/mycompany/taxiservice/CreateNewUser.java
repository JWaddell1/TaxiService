/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.taxiservice;

/**
 *
 * @author jamwe
 */
import com.mysql.cj.protocol.Resultset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class CreateNewUser extends javax.swing.JFrame {
    boolean emailcheck;
    /**
     * Creates new form CreateNewUser
     */
    public CreateNewUser() {
        initComponents(); // Initialize the form components
        setTitle("Create New User");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        registerbutton.addActionListener(evt -> register_customer());

    }
    public void check(){
         String url = "jdbc:mysql://localhost:3306/TaxiServicedb";
        //url used to connect to the database
        String username = "root";
        String password = "mummycome12!";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,username,password);
            String SQL = ("SELECT * FROM Customers WHERE email_address=?;");    
            ResultSet rs;
            try(PreparedStatement preparedStatement=connection.prepareStatement(SQL)){
                preparedStatement.setString(1,tpemail_address.getText());
                rs=preparedStatement.executeQuery();
                if("".equals(rs.getString("email_address"))){
                emailcheck= false;
            } else {
                emailcheck=true;
                }                
            
                    }}catch(Exception e){
        System.out.println(e);
    }
    }
    public boolean checkfields(){
        if("".equals(tpfirst_name.getText())){
            JOptionPane.showMessageDialog(this,"Please enter your first name", "Field left empty", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if("".equals(tplast_name.getText())){
            JOptionPane.showMessageDialog(this,"Please enter your last name", "Field left empty", JOptionPane.ERROR_MESSAGE);  
            return false;
        }
        if("".equals(tpemail_address.getText())){
            JOptionPane.showMessageDialog(this,"Please enter your email address", "Field left empty", JOptionPane.ERROR_MESSAGE);
            return false;
        } 
        if("".equals(tpphone_number.getText())){
            JOptionPane.showMessageDialog(this,"Please enter your phone number", "Field left empty", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if("".equals(tppayment_method.getText())){
            JOptionPane.showMessageDialog(this,"Please enter your payment method", "Field left empty", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if("".equals(tppassword.getText())){
            JOptionPane.showMessageDialog(this,"Please enter your password", "Field left empty", JOptionPane.ERROR_MESSAGE);
            return false;
        } 
        return true;
    }
    public void register_customer(){
        check();
        if(checkfields()==false){
            return;
        } else {
        if(emailcheck==true){
            JOptionPane.showMessageDialog(this, "Email already in use","Register Failed", JOptionPane.ERROR_MESSAGE);
        } else {
        String url = "jdbc:mysql://localhost:3306/TaxiServicedb";
        //url used to connect to the database
        String username = "root";
        String password = "mummycome12!";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,username,password);
            String SQL = ("INSERT INTO Customers(first_name,last_name,phone_number,email_address,payment_method,password) VALUES(?,?,?,?,?,?)");
            try (PreparedStatement preparestatement = connection.prepareStatement(SQL)) {
                check();
                System.out.println("Connection found my boy");
                preparestatement.setString(1,tpfirst_name.getText());
                preparestatement.setString(2,tplast_name.getText());
                preparestatement.setString(3,tpphone_number.getText());
                preparestatement.setString(4,tpemail_address.getText());
                preparestatement.setString(5,tppayment_method.getText());
                preparestatement.setString(6,tppassword.getText());
                //each variable has its own preparestatment object
                preparestatement.executeUpdate();
        }         
        
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tpfirst_name = new javax.swing.JTextField();
        tplast_name = new javax.swing.JTextField();
        tpphone_number = new javax.swing.JTextField();
        tpemail_address = new javax.swing.JTextField();
        tppayment_method = new javax.swing.JTextField();
        tppassword = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        registerbutton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tpfirst_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tpfirst_nameActionPerformed(evt);
            }
        });

        tpphone_number.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tpphone_numberActionPerformed(evt);
            }
        });

        tpemail_address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tpemail_addressActionPerformed(evt);
            }
        });

        tppayment_method.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tppayment_methodActionPerformed(evt);
            }
        });

        tppassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tppasswordActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("First name:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("First name:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Phone number:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Email address:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Payment Method: ");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Password:");

        registerbutton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        registerbutton.setText("Register");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(183, 183, 183)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(18, 18, 18)
                            .addComponent(tplast_name, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addGap(18, 18, 18)
                            .addComponent(tppassword, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tppayment_method, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(tpemail_address, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(18, 18, 18)
                            .addComponent(tpphone_number, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(tpfirst_name, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(187, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(registerbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(237, 237, 237))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tpfirst_name, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tplast_name, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tpphone_number, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tpemail_address, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tppayment_method, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tppassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(registerbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );

        tpfirst_name.getAccessibleContext().setAccessibleName("first_name");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tpphone_numberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tpphone_numberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tpphone_numberActionPerformed

    private void tpemail_addressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tpemail_addressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tpemail_addressActionPerformed

    private void tppayment_methodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tppayment_methodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tppayment_methodActionPerformed

    private void tppasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tppasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tppasswordActionPerformed

    private void tpfirst_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tpfirst_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tpfirst_nameActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JButton registerbutton;
    private javax.swing.JTextField tpemail_address;
    private javax.swing.JTextField tpfirst_name;
    private javax.swing.JTextField tplast_name;
    private javax.swing.JTextField tppassword;
    private javax.swing.JTextField tppayment_method;
    private javax.swing.JTextField tpphone_number;
    // End of variables declaration//GEN-END:variables
}
