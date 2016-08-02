package taxFormLogin;

import javafx.beans.property.SimpleStringProperty;

public class TaxBracketAddition {
	private SimpleStringProperty rate = new SimpleStringProperty("");
	private SimpleStringProperty SFiler = new SimpleStringProperty("");
	private SimpleStringProperty MJaWFiler = new SimpleStringProperty("");
	private SimpleStringProperty MSFiler = new SimpleStringProperty("");
	private SimpleStringProperty HHFiler = new SimpleStringProperty("");
	
	public TaxBracketAddition(String rate, String sfiler, String mjaWFiler, String msFiler, String hhFiler){
		this.rate = new SimpleStringProperty(rate);
		this.SFiler = new SimpleStringProperty(sfiler);
		this.MJaWFiler = new SimpleStringProperty(mjaWFiler);
		this.MSFiler = new SimpleStringProperty(msFiler);
		this.HHFiler = new SimpleStringProperty(hhFiler);
	}
	
	public SimpleStringProperty getRate() {
		return rate;
	}
	public void setRate(SimpleStringProperty rate) {
		this.rate = rate;
	}
	public SimpleStringProperty getSFiler() {
		return SFiler;
	}
	public void setSFiler(SimpleStringProperty sFiler) {
		SFiler = sFiler;
	}
	public SimpleStringProperty getMJaWFiler() {
		return MJaWFiler;
	}
	public void setMJaWFiler(SimpleStringProperty mJaWFiler) {
		MJaWFiler = mJaWFiler;
	}
	public SimpleStringProperty getMSFiler() {
		return MSFiler;
	}
	public void setMSFiler(SimpleStringProperty mSFiler) {
		MSFiler = mSFiler;
	}
	public SimpleStringProperty getHHFiler() {
		return HHFiler;
	}
	public void setHHFiler(SimpleStringProperty hHFiler) {
		HHFiler = hHFiler;
	}
	public SimpleStringProperty rateProperty(){
		return rate;
	}
	public SimpleStringProperty SFilerProperty(){
		return SFiler;
	}
	public SimpleStringProperty MJaWFilerProperty(){
		return MJaWFiler;
	}
	public SimpleStringProperty MSFilerProperty(){
		return MSFiler;
	}
	public SimpleStringProperty HHFilerProperty(){
		return HHFiler;
	}
	
}
