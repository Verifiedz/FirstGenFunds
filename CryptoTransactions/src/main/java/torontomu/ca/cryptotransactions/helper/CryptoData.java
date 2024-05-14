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
public class CryptoData {
    private double price;
    private String name;
    private String symbol;
    private String type;
            
    public CryptoData(String name, double price, String symbol, String type) {
        this.name = name;
        this.price = price;
        this.symbol = symbol;
        this.type = type;
    }
    public double getPrice(){
        return price;
    }
    public void setPrice(double price){
        this.price = price;
    }
    public String getSymbol(){
        return symbol;
    }
    public void setSymbol(String symbol){
        this.symbol = symbol;
    }
    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type = type;
    }
}