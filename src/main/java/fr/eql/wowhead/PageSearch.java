package fr.eql.wowhead;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageSearch extends PageObject {
	public PageSearch(WebDriver driver) {
		super(driver);
	}
	String boss;
	
	
	public PageLoot clickBoss(String boss) {
		waitElementsXpath("//td[@class='icon-boss-padded']/a[contains(@href,'"+boss+"')]");
		WebElement clickBoss = driver.findElement(By.xpath("//td[@class='icon-boss-padded']/a[contains(@href,'"+boss+"')]"));
		clickBoss.click();
		return new PageLoot(driver);
	} 
}
