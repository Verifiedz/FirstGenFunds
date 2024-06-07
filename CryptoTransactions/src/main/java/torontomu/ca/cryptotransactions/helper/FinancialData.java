/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author SaadWasim
 */
package torontomu.ca.cryptotransactions.helper;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FinancialData {
    private int dataID;
    private double volume;
    private double price;
    private String symbol;
    private boolean watchlist;
    private String dName;
    private String theType;
    
    //Default Constructor
    public FinancialData(){
        
    }

    // Constructor
    public FinancialData(int dataID, double volume, double price, String symbol, boolean watchlist, String dName, String theType) {
        this.dataID = dataID;
        this.volume = volume;
        this.price = price;
        this.symbol = symbol;
        this.watchlist = watchlist;
        this.dName = dName;
        this.theType = theType;
    }

    // Getters and setters
    public int getDataID() {
        return dataID;
    }

    public void setDataID(int dataID) {
        this.dataID = dataID;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public boolean isWatchlist() {
        return watchlist;
    }

    public void setWatchlist(boolean watchlist) {
        this.watchlist = watchlist;
    }

    public String getDName() {
        return dName;
    }

    public void setDName(String dName) {
        this.dName = dName;
    }

    public String getTheType() {
        return theType;
    }

    public void setTheType(String theType) {
        this.theType = theType;
    }

}