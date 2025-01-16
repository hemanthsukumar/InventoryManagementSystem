package Testscriptes;

import org.testng.annotations.Test;



public class TC_Login extends Testbase {

	
	@Test(priority = 0)
	public void validUserNameAndPassword() throws InterruptedException {
		imspages.getUserName().sendKeys("R-123");
		imspages.getPassword().sendKeys("123456789i");
		imspages.getSubmitButton().click();
		Thread.sleep(2000);
		imspages.getLogoutButton().isDisplayed();
		imspages.getLogoutButton().click();
	}
	
	
	@Test(priority = 1)
	public void invalidUserNameAndPassword() throws InterruptedException {
		driver.navigate().refresh();
		imspages.getUserName().sendKeys("R-327");
		imspages.getPassword().sendKeys("12345678");
		imspages.getSubmitButton().click();
		Thread.sleep(2000);
		imspages.getMsg().isDisplayed();
		
		imspages.getUserName().clear();
		imspages.getPassword().clear();
	}
	
	@Test(priority = 2)
	public void validUserNameAndInvalidPassword() throws InterruptedException {
		driver.navigate().refresh();
		imspages.getUserName().sendKeys("R-123");
		imspages.getPassword().sendKeys("12");
		imspages.getSubmitButton().click();
		Thread.sleep(2000);
		imspages.getMsg().isDisplayed();
		
		imspages.getUserName().clear();
		imspages.getPassword().clear();
	}
	
	@Test(priority = 3)
	public void invalidUserNameAndValidPassword() throws InterruptedException {
		imspages.getUserName().sendKeys("R-129");
		imspages.getPassword().sendKeys("123456789i");
		imspages.getSubmitButton().click();
		Thread.sleep(2000);
		imspages.getMsg().isDisplayed();
		
		imspages.getUserName().clear();
		imspages.getPassword().clear();
		
	}
	
}
