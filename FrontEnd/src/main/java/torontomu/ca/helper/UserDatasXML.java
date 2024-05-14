package torontomu.ca.helper;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "userDatas")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserDatasXML {
    @XmlElement(name = "UserData")
    private List<UserData> userDataList;
    
    // Add a field for AccountData
    @XmlElement(name = "AccountData")
    private List<AccountData> accountDataList;

    public UserDatasXML() {
        userDataList = new ArrayList<>();
        accountDataList = new ArrayList<>();
    }

    // Getters and setters for userDataList and accountDataList
    public List<UserData> getUserDataList() {
        return userDataList;
    }

    public void setUserDataList(List<UserData> userDataList) {
        this.userDataList = userDataList;
    }

    public List<AccountData> getAccountDataList() {
        return accountDataList;
    }

    public void setAccountDataList(List<AccountData> accountDataList) {
        this.accountDataList = accountDataList;
    }
}
