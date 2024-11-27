/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.taxiservice;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class Login extends javax.swing.JFrame {
    String usertype;

    public void choosetype(){
            String[] options = {"Customer", "Driver"};
        int choice = JOptionPane.showOptionDialog(this, "Please select your user type", "User Type",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if(choice == 0) { // Customer
            usertype = "Customers";
        } else if (choice == 1){
            // Driver
            usertype = "Drivers";
        }
    }

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        // Attach action listener to login button
        String email1 = tpemail_address.getText();
        new Driver_Menu(email1);
    
        jButton1.addActionListener(evt -> {
            choosetype();
            try {
                verifyCredentials();
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

     //Method to verify user credentials (works for both Customers and Drivers)
        private void verifyCredentials() throws SQLException {
        String email = tpemail_address.getText().trim();
        String password = new String(tppassword.getPassword());

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Email or Password cannot be empty.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Database connection details
        String url = "jdbc:mysql://localhost:3306/TaxiServicedb";
        String username = "root";
        String dbPassword = "mummycome12!";

        // Determine user type based on email format (just an example, can be improved later)
        

        // Try-catch block for handling SQL and connection errors
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection to the database
            try (Connection connection = DriverManager.getConnection(url, username, dbPassword)) {
                // SQL query to fetch password based on the provided email
                String sql = "SELECT password FROM " + usertype + " WHERE email_address = ?";


                // Prepare the statement to prevent SQL injection
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, email);  // Set the email address in the query

                    // Execute the query and store the result
                    try (ResultSet rs = preparedStatement.executeQuery()) {
                        if (rs.next()) {
                            // If email exists, retrieve the stored password
                            String storedPassword = rs.getString("password");

                            // Check if the entered password matches the stored password
                            if (storedPassword.equals(password)) {
                                JOptionPane.showMessageDialog(this, "Login successful!");
                                // Redirect based on user type
                                if (usertype.equals("Drivers")) {
                                    // Redirect to driver dashboard
                                    System.out.println("Redirect to Driver Dashboard");
                                         Driver_Menu MenuPage = new Driver_Menu();
                                         MenuPage.setVisible(true);
                                         dispose();
                                } else {
                                    // Redirect to customer dashboard
                                    System.out.println("Redirect to Customer Dashboard");
                                    Customer_Menu MenuPage = new Customer_Menu();
                                         MenuPage.setVisible(true);
                                         dispose();
                                }
                            } else {
                                // Show error if password does not match
                                JOptionPane.showMessageDialog(this, "Incorrect password!", "Login Failed", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            // If the email doesn't exist in the database
                            JOptionPane.showMessageDialog(this, "Email does not exist!", "Login Failed", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            // Handle any SQL related errors
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
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

        tpemail_address = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tppassword = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        ReturnHomePage = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tpemail_address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tpemail_addressActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Email address:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Password:");

        tppassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tppasswordActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton1.setText("Login");

        ReturnHomePage.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ReturnHomePage.setText("Return to Home Page");
        ReturnHomePage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReturnHomePageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(180, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tpemail_address, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tppassword, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(213, 213, 213))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(293, 293, 293)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(225, 225, 225)
                        .addComponent(ReturnHomePage, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tpemail_address, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tppassword, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(31, 31, 31)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(ReturnHomePage, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tpemail_addressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tpemail_addressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tpemail_addressActionPerformed

    private void tppasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tppasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tppasswordActionPerformed

    private void ReturnHomePageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReturnHomePageActionPerformed
        // TODO add your handling code here:
        HomePage HomeMenu = new HomePage();
        HomeMenu.setVisible(true);
        dispose();
    }//GEN-LAST:event_ReturnHomePageActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ReturnHomePage;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField tpemail_address;
    private javax.swing.JPasswordField tppassword;
    // End of variables declaration//GEN-END:variables
}
