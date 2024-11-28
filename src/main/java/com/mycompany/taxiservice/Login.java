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
    int id;
    String usertype;
    String firstname;
    String lastname;
    String eaddress;
    String pnumber;
    String pmethod;
    String pass;
    String rating;    
    boolean status;   
     HomePage HomeMenu = new HomePage();

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
                                         Tabs.setSelectedIndex(2);
                                         getdetails();
                                                                                
                                } else {
                                    // Redirect to customer dashboard
                                    System.out.println("Redirect to Customer Dashboard");
                                         Tabs.setSelectedIndex(1);
                                         getdetails();
                                         
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
     public void getdetails(){
        String email = tpemail_address.getText().trim();
        String url = "jdbc:mysql://localhost:3306/TaxiServicedb";
        String username = "root";
        String dbPassword = "mummycome12!";
        // Determine user type based on email format (just an example, can be improved later)
    

        // Try-catch block for handling SQL and connection errors
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Load the MySQL JDBC driver

            // Establish connection to the database
            try (Connection connection = DriverManager.getConnection(url, username, dbPassword)) {
                // SQL query to fetch password based on the provided email
                String sql = "SELECT * FROM " + usertype + " WHERE email_address = ?";


                // Prepare the statement to prevent SQL injection
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, email);  // Set the email address in the query

                    // Execute the query and store the result
                    try (ResultSet rs = preparedStatement.executeQuery()){
                    System.out.println(usertype);
                    if("Customers".equals(usertype)) {
                    rs.next();
                    id=rs.getInt("customer_id");
                    firstname =rs.getString("first_name");
                    lastname=rs.getString("last_name");
                    pnumber=rs.getString("phone_number");
                    eaddress=rs.getString("email_address");
                    pmethod=rs.getString("payment_method");
                    pass=rs.getString("password");
                    Welcomefn.setText(firstname);
                    Customer loggedincustomer = new Customer(id,firstname,lastname,pnumber,eaddress,pmethod,pass);
                    displayid.setText("Customer Id: "+loggedincustomer.getCustomer_id());
                    displayfn.setText("First name: "+loggedincustomer.getFirst_Name());
                    displayln.setText("Last name: "+loggedincustomer.getLast_Name());
                    displayemail.setText("Email address: "+loggedincustomer.getEmail_address());
                    displaynumber.setText("Phone number: "+loggedincustomer.getPhone_number());
                    displaypaymentmethod.setText("Payment method: "+loggedincustomer.getPayment_method());
                    displaypassword.setText("Password: "+loggedincustomer.getPassword());
                    }else{
                         rs.next();
                    id=rs.getInt("driver_id");
                    firstname =rs.getString("first_name");
                    lastname=rs.getString("last_name");
                    pnumber=rs.getString("phone_number");
                    eaddress=rs.getString("email_address");
                    rating=rs.getString("rating");
                    pass=rs.getString("password");   
                    Driver loggedindriver = new Driver(id,firstname,lastname,pnumber,eaddress,pass,status,rating);

                    Welcomefn1.setText(firstname);                    
                    displayid1.setText("Driver Id: "+String.valueOf(id));
                    displayfn1.setText("First Name: "+loggedindriver.getfirst_name());
                    displayln1.setText("Last Name: "+loggedindriver.getLast_name());
                    displayemail1.setText("Email_Address: "+loggedindriver.getEmail_address());
                    displaynumber1.setText("Phone number: "+loggedindriver.getPhone_number());
                    displayrating.setText("Rating: "+loggedindriver.getRating());
                    displaypassword1.setText("Password: "+loggedindriver.getPassword());
                    }
                    
                    
                    
                    
                    
                    }}}}catch(Exception e){
         System.out.println(e);
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

        Tabs = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        tpemail_address = new javax.swing.JTextField();
        tppassword = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        ReturnHomePage = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Customer_Menu = new javax.swing.JPanel();
        Welcome = new javax.swing.JLabel();
        Welcomefn = new javax.swing.JLabel();
        DisplayCustomerInfo = new javax.swing.JButton();
        CustomerLogout = new javax.swing.JButton();
        Driver_Menu = new javax.swing.JPanel();
        Welcome1 = new javax.swing.JLabel();
        Welcomefn1 = new javax.swing.JLabel();
        DisplayDriverInfo = new javax.swing.JButton();
        Logout1 = new javax.swing.JButton();
        DriverDisplayInfo = new javax.swing.JPanel();
        displaypassword1 = new javax.swing.JLabel();
        displayfn1 = new javax.swing.JLabel();
        displayln1 = new javax.swing.JLabel();
        displayemail1 = new javax.swing.JLabel();
        displaynumber1 = new javax.swing.JLabel();
        displayid1 = new javax.swing.JLabel();
        displayrating = new javax.swing.JLabel();
        DriverMenuReturn = new javax.swing.JButton();
        CustomerDisplayInfo = new javax.swing.JPanel();
        displayfn = new javax.swing.JLabel();
        displayln = new javax.swing.JLabel();
        displayemail = new javax.swing.JLabel();
        displaynumber = new javax.swing.JLabel();
        displayid = new javax.swing.JLabel();
        displaypaymentmethod = new javax.swing.JLabel();
        displaypassword = new javax.swing.JLabel();
        ReturnCustomerMenu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tpemail_address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tpemail_addressActionPerformed(evt);
            }
        });

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

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Password:");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Email address:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(286, 286, 286)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(202, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tppassword, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tpemail_address, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(191, 191, 191))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(ReturnHomePage, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(222, 222, 222))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tpemail_address, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tppassword, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(ReturnHomePage, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(18, 18, 18))
        );

        Tabs.addTab("tab1", jPanel1);

        Welcome.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        Welcome.setText("Welcome");

        Welcomefn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        DisplayCustomerInfo.setText("Your Information");
        DisplayCustomerInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DisplayCustomerInfoActionPerformed(evt);
            }
        });

        CustomerLogout.setText("Logout");
        CustomerLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CustomerLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Customer_MenuLayout = new javax.swing.GroupLayout(Customer_Menu);
        Customer_Menu.setLayout(Customer_MenuLayout);
        Customer_MenuLayout.setHorizontalGroup(
            Customer_MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Customer_MenuLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(Welcome, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Welcomefn, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(402, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Customer_MenuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(Customer_MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(CustomerLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DisplayCustomerInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
                .addGap(86, 86, 86))
        );
        Customer_MenuLayout.setVerticalGroup(
            Customer_MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Customer_MenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Customer_MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Welcome, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addComponent(Welcomefn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 141, Short.MAX_VALUE)
                .addComponent(DisplayCustomerInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(CustomerLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
        );

        Tabs.addTab("tab2", Customer_Menu);

        Welcome1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        Welcome1.setText("Welcome");

        Welcomefn1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        DisplayDriverInfo.setText("Your Information");
        DisplayDriverInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DisplayDriverInfoActionPerformed(evt);
            }
        });

        Logout1.setText("Logout");
        Logout1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Logout1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Driver_MenuLayout = new javax.swing.GroupLayout(Driver_Menu);
        Driver_Menu.setLayout(Driver_MenuLayout);
        Driver_MenuLayout.setHorizontalGroup(
            Driver_MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Driver_MenuLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(Welcome1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Welcomefn1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(390, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Driver_MenuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(Driver_MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(Logout1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DisplayDriverInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))
                .addGap(69, 69, 69))
        );
        Driver_MenuLayout.setVerticalGroup(
            Driver_MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Driver_MenuLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(Driver_MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Welcome1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Welcomefn1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(112, 112, 112)
                .addComponent(DisplayDriverInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(Logout1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );

        Tabs.addTab("tab3", Driver_Menu);

        DriverMenuReturn.setText("Return to Menu");
        DriverMenuReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DriverMenuReturnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout DriverDisplayInfoLayout = new javax.swing.GroupLayout(DriverDisplayInfo);
        DriverDisplayInfo.setLayout(DriverDisplayInfoLayout);
        DriverDisplayInfoLayout.setHorizontalGroup(
            DriverDisplayInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DriverDisplayInfoLayout.createSequentialGroup()
                .addGap(235, 235, 235)
                .addGroup(DriverDisplayInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(DriverDisplayInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(displayfn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(displayln1, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                        .addComponent(displayemail1, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                        .addComponent(displaynumber1, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                        .addComponent(displayid1, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                        .addComponent(displayrating, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                        .addComponent(displaypassword1, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
                    .addComponent(DriverMenuReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(268, Short.MAX_VALUE))
        );
        DriverDisplayInfoLayout.setVerticalGroup(
            DriverDisplayInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DriverDisplayInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(displayfn1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(displayln1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(displayemail1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(displaynumber1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(displayid1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(displayrating, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(displaypassword1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(DriverMenuReturn, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        Tabs.addTab("tab5", DriverDisplayInfo);

        ReturnCustomerMenu.setText("Return to Menu");
        ReturnCustomerMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReturnCustomerMenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout CustomerDisplayInfoLayout = new javax.swing.GroupLayout(CustomerDisplayInfo);
        CustomerDisplayInfo.setLayout(CustomerDisplayInfoLayout);
        CustomerDisplayInfoLayout.setHorizontalGroup(
            CustomerDisplayInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CustomerDisplayInfoLayout.createSequentialGroup()
                .addGap(246, 246, 246)
                .addGroup(CustomerDisplayInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(displayfn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(displayln, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                    .addComponent(displayemail, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                    .addComponent(displaynumber, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                    .addComponent(displayid, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                    .addComponent(displaypaymentmethod, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                    .addComponent(displaypassword, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                    .addComponent(ReturnCustomerMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(268, Short.MAX_VALUE))
        );
        CustomerDisplayInfoLayout.setVerticalGroup(
            CustomerDisplayInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CustomerDisplayInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(displayfn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(displayln, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(displayemail, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(displaynumber, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(displayid, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(displaypaymentmethod, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(displaypassword, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ReturnCustomerMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addContainerGap())
        );

        Tabs.addTab("tab4", CustomerDisplayInfo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Tabs)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Tabs)
                .addContainerGap())
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
        HomeMenu.setVisible(true);
        dispose();
    }//GEN-LAST:event_ReturnHomePageActionPerformed

    private void DisplayDriverInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DisplayDriverInfoActionPerformed
        // TODO add your handling code here:
        Tabs.setSelectedIndex(3);
    }//GEN-LAST:event_DisplayDriverInfoActionPerformed

    private void DisplayCustomerInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DisplayCustomerInfoActionPerformed
        // TODO add your handling code here:
        Tabs.setSelectedIndex(4);
    }//GEN-LAST:event_DisplayCustomerInfoActionPerformed
  
        
    
    private void Logout1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Logout1ActionPerformed
        // TODO add your handling code here:
        HomeMenu.setVisible(true);
        dispose();

    }//GEN-LAST:event_Logout1ActionPerformed

    private void CustomerLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CustomerLogoutActionPerformed
        // TODO add your handling code here:
        
        HomeMenu.setVisible(true);
        dispose();
    }//GEN-LAST:event_CustomerLogoutActionPerformed

    private void DriverMenuReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DriverMenuReturnActionPerformed
        // TODO add your handling code here:
        Tabs.setSelectedIndex(2);
    }//GEN-LAST:event_DriverMenuReturnActionPerformed

    private void ReturnCustomerMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReturnCustomerMenuActionPerformed
        // TODO add your handling code here:
        Tabs.setSelectedIndex(1);
    }//GEN-LAST:event_ReturnCustomerMenuActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CustomerDisplayInfo;
    private javax.swing.JButton CustomerLogout;
    private javax.swing.JPanel Customer_Menu;
    private javax.swing.JButton DisplayCustomerInfo;
    private javax.swing.JButton DisplayDriverInfo;
    private javax.swing.JPanel DriverDisplayInfo;
    private javax.swing.JButton DriverMenuReturn;
    private javax.swing.JPanel Driver_Menu;
    private javax.swing.JButton Logout1;
    private javax.swing.JButton ReturnCustomerMenu;
    private javax.swing.JButton ReturnHomePage;
    private javax.swing.JTabbedPane Tabs;
    private javax.swing.JLabel Welcome;
    private javax.swing.JLabel Welcome1;
    private javax.swing.JLabel Welcomefn;
    private javax.swing.JLabel Welcomefn1;
    private javax.swing.JLabel displayemail;
    private javax.swing.JLabel displayemail1;
    private javax.swing.JLabel displayfn;
    private javax.swing.JLabel displayfn1;
    private javax.swing.JLabel displayid;
    private javax.swing.JLabel displayid1;
    private javax.swing.JLabel displayln;
    private javax.swing.JLabel displayln1;
    private javax.swing.JLabel displaynumber;
    private javax.swing.JLabel displaynumber1;
    private javax.swing.JLabel displaypassword;
    private javax.swing.JLabel displaypassword1;
    private javax.swing.JLabel displaypaymentmethod;
    private javax.swing.JLabel displayrating;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField tpemail_address;
    private javax.swing.JPasswordField tppassword;
    // End of variables declaration//GEN-END:variables
}
