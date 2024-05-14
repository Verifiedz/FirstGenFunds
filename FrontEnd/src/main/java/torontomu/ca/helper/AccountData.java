package torontomu.ca.helper;

public class AccountData {
    private String email;
    private String phoneNumber;

    public AccountData() {
        // Default constructor required for JAXB
    }

    public AccountData(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
