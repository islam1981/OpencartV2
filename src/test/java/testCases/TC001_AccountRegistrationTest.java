package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegisterPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	@Test(groups = {"Regression","master"})
	public void varify_account_registration() 
	{
		logger.info("******Statting TC001_AccountRegistrationTest*******");
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicking on MyAccount Link");
		hp.clickRegister();
		logger.info("Clicking on Register Link");
		AccountRegisterPage regpage=new AccountRegisterPage(driver);
		logger.info("Providing Custome Details");
		regpage.setFirstName(randomeString().toUpperCase());
		regpage.setLastName(randomeString().toUpperCase());
		regpage.setEmail(randomeString()+"@gmail.com");
		regpage.setTelephone(randomeNumber());
		String password=randomeAlfaNumaric();
		regpage.setPassword(password);
		regpage.setPasswordConfirm(password);
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		logger.info("Validating expected Massage");
		String confirmationMSG=	regpage.getConfirmationMgs();
		Assert.assertEquals(confirmationMSG,"Your Account Has Been Created!");
		}
		catch(Exception e){
			logger.error("Test is fail");
			logger.debug("Debug logs");
			Assert.fail();
		}
		logger.info("******Finish TC001_AccountRegistrationTest*******");
		
	}
	

}
