package torontomu.ca.stocktransactions.helper;

/**
 * AccountData class to represent and manipulate account data.
 */
public class AccountData {
    private int accountID;
    private int accountPortfolio;
    private String portfolioType;
    private String primaryUser;
    private double availableFunds;
    private double totalBalance;
    private double allTimeReturn;

    public AccountData(int accountID, int accountPortfolio, String portfolioType, String primaryUser, double availableFunds, double totalBalance, double allTimeReturn) {
        this.accountID = accountID;
        this.accountPortfolio = accountPortfolio;
        this.portfolioType = portfolioType;
        this.primaryUser = primaryUser;
        this.availableFunds = availableFunds;
        this.totalBalance = totalBalance;
        this.allTimeReturn = allTimeReturn;
    }

    // Getters and Setters
    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getAccountPortfolio() {
        return accountPortfolio;
    }

    public void setAccountPortfolio(int accountPortfolio) {
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

    public double getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(double totalBalance) {
        this.totalBalance = totalBalance;
    }

    public double getAllTimeReturn() {
        return allTimeReturn;
    }

    public void setAllTimeReturn(double allTimeReturn) {
        this.allTimeReturn = allTimeReturn;
    }
}
