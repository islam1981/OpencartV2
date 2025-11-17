package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegisterPage extends BasePage {

	public AccountRegisterPage(WebDriver driver) {
		super(driver);
		
	}
	@FindBy(xpath = "//input[@id='input-firstname']") WebElement txtFirstName;
	@FindBy(xpath = "//input[@id='input-lastname']") WebElement txtLastName;
	@FindBy(xpath = "//input[@id='input-email']") WebElement txtEmail;
	@FindBy(xpath = "//input[@id='input-telephone']") WebElement txtTelephone;
	@FindBy(xpath = "//input[@id='input-password']") WebElement txtPassword;
	@FindBy(xpath = "//input[@id='input-confirm']") WebElement txtPasswordConfirm;
	@FindBy(xpath = "//input[@name='agree']") WebElement chkedPolicy;
	@FindBy(xpath = "//input[@value='Continue']") WebElement btnContinue;
	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']") WebElement msgConfirmation;
	
	public void setFirstName(String fname) {
		txtFirstName.sendKeys(fname);
	}
	public void setLastName(String lname) {
		txtLastName.sendKeys(lname);
	}
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
		
	}
	public void setTelephone(String telephone) {
		txtTelephone.sendKeys(telephone);
	}
	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}
	public void setPasswordConfirm(String pwd) {
		txtPasswordConfirm.sendKeys(pwd);
	}
	public void setPrivacyPolicy(){
		chkedPolicy.click();
	}
	public void clickContinue() {
		btnContinue.click();
	}
	public String getConfirmationMgs() {
		try {
			return (msgConfirmation.getText());
		} catch (Exception e) {
			return (e.getMessage());
		}
	}

}
