package fr.eql.wowhead;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class PageObjectTest {
	private String BROWSER=System.getProperty("browser");
	private String TestBROWSER="chrome";

	public void SetupDriver() {
		ChoixDriver choixDriver = new ChoixDriver();
		
		choixDriver.choisirNavigateur(BROWSER);
		
		ChoixDriver.driver.manage().window().maximize();
		ChoixDriver.driver.get("https://fr.wowhead.com/");
	}

	public void cleanUp() {
		ChoixDriver.driver.manage().deleteAllCookies();
	}

	public void teardown() {
		ChoixDriver.driver.quit();
	}

}
