package fr.eql.wowhead;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageAccueil extends PageObject {
	public PageAccueil(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//button[@id='onetrust-accept-btn-handler']")
	WebElement acceptCookie;
	@FindBy(xpath = "//button[contains(@class,'notifications-dialog-buttons-decline')]")
	WebElement acceptNotification;
	@FindBy(xpath = "//form[@action='/search']/input")
	WebElement searchfield;
	@FindBy(xpath = "//a[contains(@class,'header-search-button')]")
	WebElement searchInput;

	public void clickAcceptCookie() {
		waitElementsXpath("//button[@id='onetrust-accept-btn-handler']");
		acceptCookie.click();
	}
	public void clickAcceptNotification() {
		waitElementsXpath("//button[contains(@class,'notifications-dialog-buttons-decline')]");
		acceptNotification.click();
	}
	
	public PageSearch Search(String SearchString) {
		waitElementsXpath("//form[@action='/search']/input");
		searchfield.click();
		searchfield.clear();
		searchfield.sendKeys(SearchString);
		searchInput.click();
		return new PageSearch(driver);
	}


}
