package Testscriptes;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class TC_Admin extends Testbase{
	
    private void login() throws InterruptedException {
		
		imspages.getUserName().sendKeys("R-987");
		imspages.getPassword().sendKeys("123456789h");
		imspages.getSubmitButton().click();
		Thread.sleep(2000);
		
	}
	
	@Test(priority = 0)
	public void validLocationId() throws InterruptedException  {
		login();
		
		imspages.getLocId().sendKeys("890");
		imspages.getSubmitButton().click();
		Thread.sleep(1000);
		imspages.getProductid().isDisplayed();
		
	}
	
	
	
	@Test(priority = 1)
	public void invalidLocationId() throws InterruptedException  {
		
		driver.navigate().refresh();
		imspages.getLocId().sendKeys("8900");
		imspages.getSubmitButton().click();
		Thread.sleep(1000);
		assertEquals(imspages.getMsg().getText(), "No Data Found");
		
	}
	
	@Test(priority = 2)
	public void invalidLocationIdAndInvalidMaterialID() throws InterruptedException  {
		
		driver.navigate().refresh();
		imspages.getLocId().sendKeys("8900");
		imspages.getMatId().sendKeys("googlee");
		imspages.getSubmitButton().click();
		Thread.sleep(1000);
		assertEquals(imspages.getMsg().getText(), "No Data Found");
		
	}
	
	@Test(priority = 3)
	public void validLocationIdAndMaterialId() throws InterruptedException  {
		
		driver.navigate().refresh();
		imspages.getLocId().sendKeys("890");
		imspages.getMatId().sendKeys("google");
		imspages.getSubmitButton().click();
		Thread.sleep(1000);
		imspages.getProductid().isDisplayed();
		
	}
	
	
	@Test(priority = 4)
	public void update() throws InterruptedException {
		
		driver.navigate().refresh();
		imspages.getLocId().sendKeys("890");
		imspages.getMatId().sendKeys("google");
		imspages.getSubmitButton().click();
		Thread.sleep(500);
		imspages.getOrderInput().sendKeys("100");
		imspages.getUpdateButton().click();
		Thread.sleep(500);
		assertEquals(imspages.getMsg().getText(), "Updated!!! *** google ***");
		
		
	}
	
	@Test(priority = 5)
	public void addItems() throws InterruptedException {
		
		imspages.getOrders().click();
		imspages.getLocId().sendKeys("890");
		imspages.getMatId().sendKeys("iphone_5");
		imspages.getQtyBox().sendKeys("100");
		imspages.getAddButton().click();
		Thread.sleep(500);
		assertEquals(imspages.getMsg().getText(), "Item added to the Inventory Successfully!!");
		imspages.getEditPage().click();
		imspages.getLocId().sendKeys("890");
		imspages.getMatId().sendKeys("iphone_5");
		imspages.getSubmitButton().click();
		Thread.sleep(1000);
		imspages.getProductid().isDisplayed();
		
	}
	

}
