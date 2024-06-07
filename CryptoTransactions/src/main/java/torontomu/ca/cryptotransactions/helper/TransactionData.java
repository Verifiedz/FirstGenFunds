/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torontomu.ca.cryptotransactions.helper;

/**
 *
 * @author student
 */
public class TransactionData {
    private double amount; 
    private String type; 
    private double price; 
    private String timeStamp;  

    public TransactionData(){
        //Default Constructor
    }
    
    public TransactionData(double amount, String type,double price,String timeStamp){
    this.amount = amount; 
    this.type = type; 
    this.timeStamp = timeStamp; 
    this.price = price; 
    }
    
    public double getAmount(){
        return amount;
    }
    
    public void setAmount(int amount){
        this.amount = amount; 
    }
    
    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type = type;
    }
    public double getPrice(){
        return price; 
    }
    public void setPrice(double price){
        this.price = price; 
    }
    public String getTimeStamp(){
        return timeStamp;
    }
}
