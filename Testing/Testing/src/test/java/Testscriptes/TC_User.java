package Testscriptes;

import static org.testng.Assert.assertEquals;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class TC_User extends Testbase {

	public void login() throws InterruptedException {
		
		imspages.getUserName().sendKeys("R-123");
		imspages.getPassword().sendKeys("123456789i");
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
	public void order() throws InterruptedException {
		
		driver.navigate().refresh();
		imspages.getLocId().sendKeys("890");
		imspages.getMatId().sendKeys("google");
		imspages.getSubmitButton().click();
		Thread.sleep(500);
		imspages.getOrderInput().sendKeys("1");
		imspages.getOrdersButton().click();
		Thread.sleep(5000);
		assertEquals(imspages.getOrderName().getText(), "google");
		imspages.getOrders().click();
		Thread.sleep(1000);
		imspages.getOrdersItems().isDisplayed();
		
	}
	
	@Test(priority = 5)
	public void cancel() throws InterruptedException {
		
		String[] array = {"No Orders", "Order is Cancelled!!"};
		imspages.getOrders().click();
		Thread.sleep(1000);
		imspages.getCancelButton().click();
		Thread.sleep(5000);
		String expectedValue = imspages.getMsg().getText();
		AssertJUnit.assertTrue("Expected value is not present in the array", containsValue(array, expectedValue));
	}
	
	private boolean containsValue(String[] array, String value) {
        // Use a loop to check if the value is present in the array
        for (String element : array) {
            if (element.equals(value)) {
                return true;
            }
        }
        return false;
    }
	
}
