package torontomu.ca.cryptotransactions.business;
import torontomu.ca.cryptotransactions.persistence.Transaction_CRUD;
import torontomu.ca.cryptotransactions.persistence.FinancialData_CRUD;
import torontomu.ca.cryptotransactions.persistence.Personal_Accounts_CRUD;
import torontomu.ca.cryptotransactions.helper.FinancialData;
import torontomu.ca.cryptotransactions.helper.AccountData;
import torontomu.ca.cryptotransactions.helper.FinancialDataWrapper;
import torontomu.ca.cryptotransactions.helper.UserNotFoundException;
import java.util.List;

public class CryptoBusiness {
    
    private FinancialData_CRUD financialData_CRUD;
    private Personal_Accounts_CRUD accountdata;
    private Transaction_CRUD transactiondata;
    
    public CryptoBusiness() {
        this.financialData_CRUD = new FinancialData_CRUD();
        this.accountdata = new Personal_Accounts_CRUD();
        this.transactiondata = new Transaction_CRUD();
    }
    
    public FinancialDataWrapper getAllFinancialData() {
        List<FinancialData> financialData = financialData_CRUD.getAllFinancialData();
        return new FinancialDataWrapper(financialData);
    }
    
    public boolean processTrade(String PrimaryUser,int dataId, double volume, double price,double availablefunds ){
        //verfies if it can receive data based on PrimaryUser name
        boolean valid = false;
        AccountData accountdata = Personal_Accounts_CRUD.getAccountData(PrimaryUser);
        if(accountdata == null){
            System.out.println("accountdata is Null cant processTrade");
            return valid;
        }
        accountdata.setAvailableFunds(accountdata.getAvailableFunds()+ availablefunds);
        Personal_Accounts_CRUD.UpdatePersonalAccountData(accountdata);
        
        double totalCost = volume * price;
              
        // now to actually make calculation for the cost
        //lets verfiy if the purchase can be make
        if(accountdata.getAvailableFunds()< totalCost){
            System.out.println("Sorry but purchase was unable to be made due in insufficient funds");
            return false;
        }
        double result = accountdata.getAvailableFunds() - totalCost;
        accountdata.setAvailableFunds(result);
        Personal_Accounts_CRUD.UpdatePersonalAccountData(accountdata);
        Transaction_CRUD.createTransaction( dataId, volume, price, "crypto");
        valid = true;
        return valid;

    }
    public double getAvailableFunds(String primaryUser) throws UserNotFoundException {
    AccountData accountdata = Personal_Accounts_CRUD.getAccountData(primaryUser);
    if (accountdata == null) {
        throw new UserNotFoundException("User " + primaryUser + " does not exist.");
    }
    System.out.println("New AvailableFunds: " + accountdata.getAvailableFunds());
    return accountdata.getAvailableFunds();  
}

}
