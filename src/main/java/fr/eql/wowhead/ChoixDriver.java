package fr.eql.wowhead;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChoixDriver {
	static Logger LOGGER = LoggerFactory.getLogger(ChoixDriver.class);

	
	public static WebDriver choisirNavigateur(String BROWSER) {
		switch (BROWSER) {
		case "firefox":
			System.setProperty("webdriver.gecko.driver", "src/main/resources/driver/geckodriver.exe");
			return new FirefoxDriver();
			
		case "ie":
			System.setProperty("webdriver.ie.driver", "src/main/resources/driver/IEDriverServer.exe");
			return new InternetExplorerDriver();
			

		case "chrome":
			System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver.exe");
			return new ChromeDriver();
			

		default:
			LOGGER.info("Browser était mal renseigné, Chrome se lance par default");
			System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver.exe");
			return new ChromeDriver();
		}
	}
}
