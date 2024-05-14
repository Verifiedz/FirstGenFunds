package torontomu.ca.helper;

public class UserData {
    private String username;
    private String password;
    private AccountData accountData;

    public UserData(String username, String password, AccountData accountData) {
        this.username = username;
        this.password = password;
        this.accountData = accountData;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountData getAccountData() {
        return accountData;
    }

    public void setAccountData(AccountData accountData) {
        this.accountData = accountData;
    }
}

