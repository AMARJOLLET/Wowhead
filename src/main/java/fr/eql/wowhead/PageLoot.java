package fr.eql.wowhead;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageLoot extends PageObject {
	public PageLoot(WebDriver driver) {
		super(driver);
	}
	JavascriptExecutor js = (JavascriptExecutor) driver;
	String rarete;

	@FindBy (xpath = "//div[@id='tab-drops']//a[@class='q3 listview-cleartext']")
    List<WebElement> loot_list;
	@FindBy (xpath = "//div[@id='tab-drops']//a[@class='q3 listview-cleartext']")
    List<WebElement> rarete_list;
	
	
	
	
    public PageItem clickItem(int n) {
		waitElementsXpath("//div[@id='tab-drops']//a[@class='q3 listview-cleartext']");
        WebElement clickItem = loot_list.get(n);
        js.executeScript("arguments[0].scrollIntoView();", loot_list.get(n));
        rarete = loot_list.get(n).getCssValue("color");
        clickItem.click();
        return new PageItem(driver);
    }
 }