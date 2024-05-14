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
public class AccountData {
    private String accountPortfolio;
    private String portfolioType;
    private String primaryUser;
    private double availableFunds;
    private double totalBalance;

    public AccountData(String accountPortfolio, String portfolioType, String primaryUser, double availableFunds, double totalBalance) {
        this.accountPortfolio = accountPortfolio;
        this.portfolioType = portfolioType;
        this.primaryUser = primaryUser;
        this.availableFunds = availableFunds;
        this.totalBalance = totalBalance;
    }

    public String getAccountPortfolio() {
        return accountPortfolio;
    }

    public void setAccountPortfolio(String accountPortfolio) {
        this.accountPortfolio = accountPortfolio;
    }

    public String getPortfolioType() {
        return portfolioType;
    }

    public void setPortfolioType(String portfolioType) {
        this.portfolioType = portfolioType;
    }

    public String getPrimaryUser() {
        return primaryUser;
    }

    public void setPrimaryUser(String primaryUser) {
        this.primaryUser = primaryUser;
    }

    public double getAvailableFunds() {
        return availableFunds;
    }

    public void setAvailableFunds(double availableFunds) {
        this.availableFunds = availableFunds;
    }
    
    public double getTotalBalance(){
        return totalBalance;
    }
    
    public void setTotalBalance(){
        this.totalBalance = totalBalance;
    }
}

