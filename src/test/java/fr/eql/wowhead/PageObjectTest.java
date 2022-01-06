package fr.eql.wowhead;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class PageObjectTest {
	private String BROWSER="chrome";
			//System.getProperty("browser");
	WebDriver driver;


	public void SetupDriver() {
		driver = ChoixDriver.choisirNavigateur(BROWSER);
		driver.manage().window().maximize();
		driver.get("https://fr.wowhead.com/");
	}

	public void cleanUp() {
		driver.manage().deleteAllCookies();
	}

	public void teardown() {
		driver.quit();
	}

}
