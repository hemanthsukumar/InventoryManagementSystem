package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Imspages  {
	
	WebDriver driver;
	
	public Imspages(WebDriver rdriver) {
		this.driver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(name = "uname")
	WebElement userName;
	
	@FindBy(name = "password")
	WebElement password;
	
	@FindBy(name = "submit")
	WebElement submitButton;
	
	@FindBy(className = "logout")
	WebElement logoutButton;
	
	@FindBy(id = "msg")
	WebElement msg;

	@FindBy(name = "matID")
	WebElement matId;
	
	@FindBy(name = "locID")
	WebElement locId;
	
	@FindBy(id = "qntgoogle")
	WebElement orderInput;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div/div/div/table/tbody/tr[1]/td[2]")
	WebElement productid;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div/div/div/table/tbody/tr/td[5]/button")
	WebElement ordersButton;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div/div/table/tbody/tr/td[1]")
	WebElement ordersItems;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div/nav/div/ul/li[2]/a")
	WebElement orders;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div/div/table/tbody/tr[1]/td[8]/button")
	WebElement cancelButton;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div/div/div/table/tbody/tr[1]/td[8]/button")
	WebElement updateButton;
	
	@FindBy(xpath = "//*[@id=\"addItems\"]/button")
	WebElement addButton;
	
	@FindBy(name = "qty")
	WebElement qtyBox;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div/nav/div/ul/li[1]/a")
	WebElement EditPage;
	
	@FindBy(className = "materialName")
	WebElement orderName;
	
	
	
	
	
	public WebElement getOrderName() {
		return orderName;
	}

	public WebElement getEditPage() {
		return EditPage;
	}

	public WebElement getQtyBox() {
		return qtyBox;
	}

	public WebElement getAddButton() {
		return addButton;
	}

	public WebElement getUpdateButton() {
		return updateButton;
	}

	public WebElement getCancelButton() {
		return cancelButton;
	}

	public WebElement getOrders() {
		return orders;
	}

	public WebElement getOrdersItems() {
		return ordersItems;
	}

	public WebElement getOrdersButton() {
		return ordersButton;
	}

	public WebElement getOrderInput() {
		return orderInput;
	}

	public WebElement getProductid() {
		return productid;
	}

	public WebElement getMatId() {
		return matId;
	}

	public WebElement getLocId() {
		return locId;
	}

	public WebElement getMsg() {
		return msg;
	}

	public WebElement getLogoutButton() {
		return logoutButton;
	}

	public WebElement getSubmitButton() {
		return submitButton;
	}

	public WebElement getUserName() {
		return userName;
	}

	public WebElement getPassword() {
		return password;
	}
	
	
}
