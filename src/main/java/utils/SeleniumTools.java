package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumTools extends InstanciationDriver {
    public SeleniumTools(WebDriver driver) {
        super(driver);
    }

    public WebElement findElement(WebDriverWait wait, String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    public void clickOnElement(WebDriverWait wait, WebElement we) {
        wait.until(ExpectedConditions.elementToBeClickable(we));
        we.click();
    }

    public void scrollToElement(WebDriver driver, WebDriverWait wait, WebElement we) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", we);
        wait.until(ExpectedConditions.elementToBeClickable(we));
    }

    public void sendKey(WebDriverWait wait, WebElement we, String string) {
        wait.until(ExpectedConditions.elementToBeClickable(we));
        we.click();
        we.clear();
        we.sendKeys(string);
    }

    public void sendKeysCharOneByOne(WebDriverWait wait, WebElement we, String s) {
        wait.until(ExpectedConditions.elementToBeClickable(we));
        we.clear();
        Actions actions = new Actions(driver);

        for (int i = 0; i < s.length(); i++) {
            if (i == 1) {
                actions.sendKeys(Keys.ARROW_RIGHT).build().perform();
            }
            char c = s.charAt(i);
            String st = String.valueOf(c);
            we.sendKeys(st);
        }
    }


    public void mouseOver(WebDriverWait wait, WebElement we) {
        wait.until(ExpectedConditions.elementToBeClickable(we));
        Actions action = new Actions(driver);
        action.moveToElement(we).build().perform();
    }

    public void dragAndDrop(WebDriverWait wait, WebElement we1, WebElement we2) {
        wait.until(ExpectedConditions.elementToBeClickable(we1));
        wait.until(ExpectedConditions.elementToBeClickable(we2));
        Actions action = new Actions(driver);
        action.clickAndHold(we1).moveToElement(we2).release(we2).build().perform();
    }

    public void checkBoxCheck(WebDriverWait wait, WebElement we, boolean state) {
        wait.until(ExpectedConditions.elementToBeClickable(we));
        if (we.isSelected() && !state) {
            LOGGER.info("Décoche de la checkbox");
            clickOnElement(wait, we);
        } else if (!we.isSelected() && state) {
            LOGGER.info("Coche de la checkbox");
            clickOnElement(wait, we);
        }
    }
}
