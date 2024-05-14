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
public class WatchlistData {
    private int portfolioID;
    private double price;
    private String name;

    
    public WatchlistData(double price,String name) {
        this.price = price;
        this.name = name;
    }

    
    public int getPortfolioID() {
        return portfolioID;
    }

    public void setPortfolioID(int portfolioID) {
        this.portfolioID = portfolioID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }    
}

