package Testscriptes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import Pages.Imspages;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Testbase {

	WebDriver driver;
	
	Imspages imspages;
	
	@Parameters({ "Browser", "Url" })
	@BeforeClass
	public void setup(String Browser, String Url) {
		if (Browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}else if(Browser.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.get(Url);
		imspages = new Imspages(driver);
	}

	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}	
