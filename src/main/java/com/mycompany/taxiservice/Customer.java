//CUSTOMER CLASS

package com.mycompany.taxiservice;

//Public Customer Class responsible for handling customer related tasks

public class Customer {
    //Private Customer Attributes
    private int customer_id = 0;
    private String first_name = " ";
    private String last_name = " ";
    private String phone_number = " ";
    private String email_address = " ";
    private String payment_method = " ";

    //Constructor to initialize customer with given values
    public Customer(int customer_id, String first_name, String last_name, 
                    String phone_number, String email_address, String payment_method) {
        this.customer_id = customer_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.email_address = email_address;
        this.payment_method = payment_method;
    }

    //Getter and Setter methods for the customer information class

    //Getters and Setters for first_name
    public String getFirst_Name() {
        return first_name;
    }

    public void setFirst_Name(String first_name) {
        this.first_name = first_name;
    }

    //Getters and Setters for last_name
    public String getLast_Name() {
        return last_name;
    }

    public void setLast_Name(String last_name) {
        this.last_name = last_name;
    }

    //Getters and Setters for customer_id
    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    //Getters and Setters for phone_number
    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    //Getters and Setters for email_address
    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    //Getters and Setters for payment_method
    public String getPayment_method() {
        return payment_method;
    }
    
    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }
    
    //Probably would change
    //Public Method to Validate Customer 
    public boolean validate_customer() {
        //Probably will change
        return customer_id > 0;
    }
}