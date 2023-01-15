package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Outils extends InstanciationDriver{
    public Outils(WebDriver driver) {
        super(driver);
    }


    public void clickOnElementBoucle(WebDriver driver, WebDriverWait wait, WebElement we) {
        for (int i = 0; i < 4; i++) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(we));
                we.click();
                break;
            } catch (ElementClickInterceptedException e) {
                LOGGER.info("Retry + " + (i + 1));
            }
        }
    }

    public boolean clickOnElementBoolean(WebDriver driver, WebDriverWait wait, String xpath) {
        try {
            WebElement we = driver.findElement(By.xpath(xpath));
            wait.until(ExpectedConditions.elementToBeClickable(we));
            we.click();
            return true;
        } catch (ElementClickInterceptedException e){
            return false;
        }
    }
}
