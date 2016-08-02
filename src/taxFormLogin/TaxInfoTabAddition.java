package taxFormLogin;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TaxInfoTabAddition {
	private SimpleStringProperty filingStatus = new SimpleStringProperty("");
	private SimpleStringProperty standardDeduction = new SimpleStringProperty("");
	private SimpleStringProperty exemption = new SimpleStringProperty("") ;
	
	
	public TaxInfoTabAddition(String name, String standardD, String e){
		this.filingStatus = new SimpleStringProperty(name);
		this.standardDeduction = new SimpleStringProperty(standardD);
		this.exemption = new SimpleStringProperty(e);
	}
	//getter
	public SimpleStringProperty getFilingStatus(){
		return filingStatus;
	}
	public SimpleStringProperty getStandardDeduction(){
		return filingStatus;
	}
	public SimpleStringProperty getExemption(){
		return filingStatus;
	}
	
	//setter
	public void setFilingStatus(String a){
		this.filingStatus.set(a);
	}
	public void setStandardDeduction(String b){
		this.standardDeduction.set(b);
	}
	public void setExemption(String c){
		this.exemption.set(c);
	}
	
	//return simpleProperty
	public SimpleStringProperty filingStatusProperty(){
		return filingStatus;
	}
	public SimpleStringProperty standardDeductionProperty(){
		return standardDeduction;
	}
	public SimpleStringProperty exemptionProperty(){
		return exemption;
	}
}
