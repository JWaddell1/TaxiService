/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.taxiservice;

/**
 *
 * @author jamwe
 */
public class TaxiService {
    public class Driver {
        private int driver_id;
        private String first_name;
        private String last_name;
        private int phone_number;
        private String email;
        public double rating; 
        public boolean status;
        public boolean x, x1, y1, y2;
        public Driver(int driver_id, String first_name, String last_name, int phone_number, String email, double rating, boolean status){
            this.driver_id = driver_id;
            this.first_name = first_name;
            this.last_name = last_name;
            this.phone_number = phone_number;
            this.email = email;
            this.rating = rating;
            this.status = status;
        }
        public String getFirstName(){
            return first_name;
            }
        public String getLastName(){
            return first_name;
            }
        public int getPhoneNumber(){
            return phone_number;
            }
        public boolean getStatus(){
            return status;
        }
        public void acceptTrip(){
            
        }
        public void declineTrip(){
            
        }
        public void UpdateStatus(){
            
        }
        }

  
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
