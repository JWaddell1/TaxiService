package com.mycompany.taxiservice;

public class Driver {
    //Attributes for the driver
    private int driver_id;
    private String driver_first_name;
    private String driver_last_name;
    private String driver_phone_number;  
    private String driver_email_address;
    private String driver_password; 
    private boolean driver_status;
    private String driver_rating;

    //constructor
    public Driver(int driver_id, String driver_first_name, String driver_last_name, String driver_phone_number, 
                  String driver_email_address, String driver_password, boolean driver_status, String driver_rating) {
        this.driver_id = driver_id;
        this.driver_first_name = driver_first_name;
        this.driver_last_name = driver_last_name;
        this.driver_phone_number = driver_phone_number;
        this.driver_email_address = driver_email_address;
        this.driver_password = driver_password; 
        this.driver_status = driver_status;
        this.driver_rating = driver_rating;
    }

    // Getters and Setters
    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    public String getDriver_first_name() {
        return driver_first_name;
    }

    public void setDriver_first_name(String driver_first_name) {
        this.driver_first_name = driver_first_name;
    }

    public String getDriver_last_name() {
        return driver_last_name;
    }

    public void setDriver_last_name(String driver_last_name) {
        this.driver_last_name = driver_last_name;
    }

    public String getDriver_phone_number() {
        return driver_phone_number;
    }

    public void setDriver_phone_number(String driver_phone_number) {
        this.driver_phone_number = driver_phone_number;
    }

    public String getDriver_email_address() {
        return driver_email_address;
    }

    public void setDriver_email_address(String driver_email_address) {
        this.driver_email_address = driver_email_address;
    }

    public String getDriver_password() {
        return driver_password;
    }

    public void setDriver_password(String driver_password) {
        this.driver_password = driver_password;
    }

    public boolean isDriver_status() {
        return driver_status;
    }

    public void setDriver_status(boolean driver_status) {
        this.driver_status = driver_status;
    }

    public String getDriver_rating() {
        return driver_rating;
    }

    public void setDriver_rating(String driver_rating) {
        this.driver_rating = driver_rating;
    }

    
    
    //COULD BE REMOVED SINCE THERE IN THE TAXISERVICE ALEREADY
    // Methods
    public void acceptTrip() {
        System.out.println("Trip accepted by driver: " + driver_first_name + " " + driver_last_name);
    }

    public void declineTrip() {
        System.out.println("Trip declined by driver: " + driver_first_name + " " + driver_last_name);
    }

    public void updateStatus(boolean status) {
        this.driver_status = status;
        System.out.println("Driver status updated to: " + (status ? "Active" : "Inactive"));
    }
}