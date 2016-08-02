package taxFormLogin;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import taxFormLogin.Main;


public class homeController implements Initializable{
	//variables for taxInfoTable
	@FXML private TableView taxInfoTable;
	@FXML private TableColumn filingStatusColumn;
	@FXML private TableColumn standardDeductionColumn;
	@FXML private TableColumn exemptionColumn;
	
	//variables for taxBracketTable
	@FXML private TableView taxBracketTable;
	@FXML private TableColumn rateColumn;
	@FXML private TableColumn singleColumn;
	@FXML private TableColumn MJaWColumn;
	@FXML private TableColumn MSColumn;
	@FXML private TableColumn HHColumn;
	
	//variables for Basic question tab
	@FXML private ComboBox <String> martialStatus;
		  private ObservableList<String> martialStatusList = FXCollections.observableArrayList
			("Single","Married Filing Jointly","Qualify Widow(er)","Married Filing Seperate","Head of Household");
	
	@FXML private ComboBox<String> dependentsNum;
		  private ObservableList<String> numOfDep = FXCollections.observableArrayList("0","1","2","3","4","5");
	
	@FXML private TextField w2;
	@FXML private TextField wh;
	@FXML private TextField miscIncome;
	
	@FXML private TextField SEduct;
	@FXML private TextField tIncome;
	@FXML private TextField taxRate;
	@FXML private TextField SEtax;
	@FXML private TextField w2Box2;
	@FXML private TextField taxDue;
	@FXML private TextField taxRefund;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//display info for taxinfo table
		filingStatusColumn.setCellValueFactory(new PropertyValueFactory<TaxInfoTabAddition,String>("filingStatus"));
		standardDeductionColumn.setCellValueFactory(new PropertyValueFactory<TaxInfoTabAddition,String>("standardDeduction"));
		exemptionColumn.setCellValueFactory(new PropertyValueFactory<TaxInfoTabAddition,String>("exemption"));
		taxInfoTable.setItems(buildTaxInfoData());
		
		//display info for taxbracket table
		rateColumn.setCellValueFactory(new PropertyValueFactory<TaxBracketAddition,String>("rate"));
		singleColumn.setCellValueFactory(new PropertyValueFactory<TaxBracketAddition,String>("SFiler"));
		MJaWColumn.setCellValueFactory(new PropertyValueFactory<TaxBracketAddition,String>("MJaWFiler"));
		MSColumn.setCellValueFactory(new PropertyValueFactory<TaxBracketAddition,String>("MSFiler"));
		HHColumn.setCellValueFactory(new PropertyValueFactory<TaxBracketAddition,String>("HHFiler"));
		taxBracketTable.setItems(buildTaxBracketData());
		
		//display combo boxes
		martialStatus.setItems(martialStatusList);
		dependentsNum.setItems(numOfDep);
	}
	
	private ObservableList<TaxInfoTabAddition> buildTaxInfoData(){
		//method to build data taxinfo table
		ObservableList<TaxInfoTabAddition> TIdata = FXCollections.observableArrayList();
		
		TIdata.add(new TaxInfoTabAddition("Single", "$6300.00","$4000.00"));
		TIdata.add(new TaxInfoTabAddition("Married Filing Jointly", "$12600.00","$8000.00"));
		TIdata.add(new TaxInfoTabAddition("Married Filing Seperate", "$6300.00","$4000.00"));
		TIdata.add(new TaxInfoTabAddition("Head Of Household", "$9250.00","$4000.00"));
		TIdata.add(new TaxInfoTabAddition("Qualifying Widow(er)", "$12600.00","$8000.00"));
		
		return TIdata;
	}
	
	private ObservableList<TaxBracketAddition> buildTaxBracketData(){
		//method to build data in taxbracket table
		ObservableList<TaxBracketAddition> TBdata = FXCollections.observableArrayList();
		
		TBdata.add(new TaxBracketAddition("10%","$0.00 - $9275.00","$0.00 - $18550.00","$0.00 - $9275.00","$0.00 - $13250.00"));
		TBdata.add(new TaxBracketAddition("15%","$9275.00 - $37650.00","$18550.00 - $75300.00","$9276.00 - $37650.00","$13251.00 - $50400.00"));
		TBdata.add(new TaxBracketAddition("25%","$37650.00 - $91150.00","$75300.00 - $151900.00","$37651.00 - $75950.00","$50401.00 - $130150.00"));
		TBdata.add(new TaxBracketAddition("28%","$91150.00 - $190150.00","$151900.00 - $231450.00","$75951.00 - $115725.00","$130151.00 - $210800.00"));
		TBdata.add(new TaxBracketAddition("33%","$190150.00 - $413350.00","$231450.00 - $413350.00","$115726.00 - $206675.00","$210801.00 - $413350.00"));
		TBdata.add(new TaxBracketAddition("35%","$413350.00 - $415050.00","$413350 - $466950.00","$206676 - $233475.00","$413351.00 - $441000.00"));
		TBdata.add(new TaxBracketAddition("39.60%","$415050.00+","$466950.00+","$233476.00+","$$441000.00+"));
		
		return TBdata;
		
	}
	private List getDeductionAndExemption(){
		//get and calculate total deduction, total exemption and 
		//remembers the filing status selected and store them in a List
		int deduction =0;
		int sD = 6300;
		int mD = 12600;
		int FSeNum = 0;
		String MSselection = "";
		
		try{
		MSselection = martialStatus.getValue().toString();
		if (MSselection == "Single")deduction = sD; FSeNum = 1;
		if (MSselection == "Married Filing Jointly")deduction = mD; FSeNum = 2;
		if (MSselection == "Qualify Widow(er)")deduction = mD; FSeNum = 2;
		if (MSselection == "Married Filing Seperate")deduction = sD; FSeNum = 1;
		if (MSselection == "Head of Household")deduction = sD; FSeNum = 1;
		}catch(NullPointerException e){
			Main.alertWindow("Please select a value for 1 "+e.getMessage());
		}
		
		int totalExempAmount = 0;
		int exemptionNum = 0;
		
		try{
			switch(dependentsNum.getValue().toString()){
			case "0": exemptionNum = 0;break;
			case "1": exemptionNum = 1;break;
			case "2": exemptionNum = 2;break;
			case "3": exemptionNum = 3;break;
			case "4": exemptionNum = 4;break;
			case "5": exemptionNum = 5;break;
			}
		}catch(NullPointerException e){
			Main.alertWindow("please select a value for 2 "+e.getMessage());
			
		}
		totalExempAmount = (exemptionNum + FSeNum)*4000;
		List DandE = new ArrayList();
		DandE.add(0,deduction);
		DandE.add(1,totalExempAmount);
		DandE.add(2,MSselection);
		return DandE;
	}
	
	private int[] calculate(){
		//validate data from w2,wh and miscincome textfield
		//store data from those fields into an array
		int w2Income = 0;
		int misc = 0;
		int wholding = 0;
		
		TextField [] tfArray = {w2,wh,miscIncome};
		int [] incomeArray = {w2Income,wholding,misc};
	
		for (int i = 0; i <tfArray.length;i++){
			String a = tfArray[i].getText().trim().toString();
			if (a.isEmpty()){
				Main.alertWindow("you did not enter a value for "+tfArray[i]);
			}else{
				try{
				int b = Integer.parseInt(a);
				incomeArray[i]=b;
				}
				catch(Exception e){
				Main.alertWindow("you did not enter a number for "+e.getMessage());
				}
			}
		}
		//incomeArray[0] = w2 income
		//incomeArray[1] = w2 withholding
		//incomeArray[2] = misc income
		return incomeArray;
	}
	
	@FXML
	private void finalCalculation(){
		//puts everything together and calculates the result
		List placeHolder = getDeductionAndExemption();
		int [] incomeArray = calculate();
		
		int a = (int) placeHolder.get(0);//total deduction
		int b = (int) placeHolder.get(1);//total exemption
		String c = (String)placeHolder.get(2);// filing status value
		double tr = 0;
		
		//self employment tax deductible field
		double SEminus = Math.ceil(incomeArray[2]*0.9235*0.153*0.5);
		String SEm = String.valueOf(SEminus);
		SEduct.setText(SEm);
		
		//taxable income field
		double taxIncome = Math.ceil((incomeArray[0]+incomeArray[2]-SEminus)- (a+b));
		String st = String.valueOf(taxIncome);
		tIncome.setText(st);
		
		//determining what tax rate to use for selected filing status
		while(c == "Single"){
			if (taxIncome > 0 && taxIncome <= 9275)tr = 0.1;
			if (taxIncome > 9275 && taxIncome <= 37650)tr = 0.15;
			if (taxIncome > 37650 && taxIncome <= 91150)tr = 0.25;
			if (taxIncome > 91150 && taxIncome <= 190150)tr = 0.28;
			if (taxIncome > 190150 && taxIncome <= 413350)tr = 0.33;
			if (taxIncome > 413350 && taxIncome <= 415050)tr = 0.35;
			if (taxIncome > 415050)tr = 0.396;
			break;
		}
		while(c == "Married Filing Jointly" || c == "Qualify Widow(er)"){
			if (taxIncome > 0 && taxIncome <= 18550)tr = 0.1;
			if (taxIncome > 18550 && taxIncome <= 75300)tr = 0.15;
			if (taxIncome > 75300 && taxIncome <= 151900)tr = 0.25;
			if (taxIncome > 151900 && taxIncome <= 231450)tr = 0.28;
			if (taxIncome > 231450 && taxIncome <= 413350)tr = 0.33;
			if (taxIncome > 413350 && taxIncome <= 466950)tr = 0.35;
			if (taxIncome > 466950)tr = 0.396;
			break;
		}
		while(c == "Married Filing Seperate"){
			if (taxIncome > 0 && taxIncome <= 9275)tr = 0.1;
			if (taxIncome > 9275 && taxIncome <= 37650)tr = 0.15;
			if (taxIncome > 37650 && taxIncome <= 75950)tr = 0.25;
			if (taxIncome > 75950 && taxIncome <= 115725)tr = 0.28;
			if (taxIncome > 115725 && taxIncome <= 206675)tr = 0.33;
			if (taxIncome > 206675 && taxIncome <= 233475)tr = 0.35;
			if (taxIncome > 233475)tr = 0.396;
			break;
		}
		while(c == "Head of Household"){
			if (taxIncome > 0 && taxIncome <= 13250)tr = 0.1;
			if (taxIncome > 9275 && taxIncome <= 50400)tr = 0.15;
			if (taxIncome > 50400 && taxIncome <= 130150)tr = 0.25;
			if (taxIncome > 130150 && taxIncome <= 210800)tr = 0.28;
			if (taxIncome > 210800 && taxIncome <= 413350)tr = 0.33;
			if (taxIncome > 413350 && taxIncome <= 441000)tr = 0.35;
			if (taxIncome > 441000)tr = 0.396;
			break;
		}
		
		//tax rate field
		String tRate = String.valueOf(tr);
		taxRate.setText(tRate);
		
		//misc income field
		double miscIncomeCal = Math.ceil(incomeArray[2]*0.9235*0.153);
		String miscCal = String.valueOf(miscIncomeCal);
		SEtax.setText(miscCal);
		
		//withholding field
		String sWH = String.valueOf(Math.ceil(incomeArray[1]));
		w2Box2.setText(sWH);
		
		//tax refund or due field
		double result = Math.ceil((taxIncome*tr + miscIncomeCal)-incomeArray[1]);
		String doR = String.valueOf(result);
		if (result >= 0){
			taxDue.setText(doR);
		}else {
			taxRefund.setText(doR);
		}
		
	}
	
	@FXML
	private void clear(){
		TextField[] clearArray = new TextField[]{w2,wh,miscIncome,SEduct,tIncome,taxRate,
				SEtax,w2Box2,taxDue,taxRefund};
		
		martialStatus.setValue(null);
		dependentsNum.setValue(null);
		for (int i = 0; i< clearArray.length; i++){
			clearArray[i].clear();
		}
		
	}
	
}
	


	
