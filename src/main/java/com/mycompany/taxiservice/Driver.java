package com.mycompany.taxiservice;

public class Driver {
    //Attributes for the driver
    private int driver_id;
    private String first_name;
    private String last_name;
    private String phone_number;  
    private String email_address;
    private String password; 
    private boolean driver_status;
    private String rating;

    //constructor
    public Driver(int driver_id, String first_name, String last_name, String phone_number, 
                  String email_address, String password, boolean driver_status, String rating) {
        this.driver_id = driver_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.email_address = email_address;
        this.password = password; 
        this.driver_status = driver_status;
        this.rating = rating;
    }

    // Getters and Setters
    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    public String getfirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void Last_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isDriver_status() {
        return driver_status;
    }

    public void setDriver_status(boolean driver_status) {
        this.driver_status = driver_status;
    }

    public String getRting() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    
    
    //COULD BE REMOVED SINCE THERE IN THE TAXISERVICE ALEREADY
    // Methods
    public void acceptTrip() {
        System.out.println("Trip accepted by driver: " + first_name + " " + last_name);
    }

    public void declineTrip() {
        System.out.println("Trip declined by driver: " + first_name + " " + last_name);
    }

    public void updateStatus(boolean status) {
        this.driver_status = status;
        System.out.println("Driver status updated to: " + (status ? "Active" : "Inactive"));
    }
}