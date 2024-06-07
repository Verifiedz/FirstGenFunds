/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package torontomu.ca.cryptotransactions.helper;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import torontomu.ca.cryptotransactions.helper.FinancialData;
/**
 *
 * @author saadwasim
 */
@XmlRootElement(name = "financialdatalist")
public class FinancialDataWrapper {
    private List<FinancialData> financialdatalist;
    
    //Default Constructor 
    
    public FinancialDataWrapper(){}
    
    public FinancialDataWrapper(List<FinancialData> financialdatalist){
        this.financialdatalist = financialdatalist;
    }
    
    public List<FinancialData> getFinancialDataList(){
        return financialdatalist;
    }
    public void setFinancialDataList(List<FinancialData> financialdatalist){
        this.financialdatalist = financialdatalist;
    }
}
